package cz.mg.projectexplorer.components.extensions;

import cz.mg.projectexplorer.graphics.Highlight;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;


public class CodeArea extends JPanel {
    private static final int VERTICAL_SPACING = 0;
    private static final int TAB_SIZE = 4;
    private static final Font FONT = new Font("monospaced", Font.PLAIN, 14);
    
    private final String[] lines;
    private final int[] indentations;
    private final Highlight[] highlights;
    private int fontWidth;
    private int fontHeight;
    private CodeAreaContent content;
    private CodeAreaLineNumberBar lineNumberBar;

    public CodeArea(String[] lines, Highlight[] highlights) {
        this.lines = new String[lines.length];
        this.indentations = new int[lines.length];
        for(int i = 0; i < lines.length; i++){
            int indentation = 0;
            for(int j = 0; j < lines[i].length(); j++){
                if(lines[i].charAt(indentation) == '\t') indentation++;
                else break;
            }
            this.lines[i] = lines[i].substring(indentation);
            this.indentations[i] = indentation;
        }
        this.highlights = highlights;
    }
    
    private void init(){
        FontMetrics metrics = getGraphics().getFontMetrics(FONT);
        fontHeight = metrics.getHeight();
        fontWidth = metrics.stringWidth("M");
        content = new CodeAreaContent(this);
        lineNumberBar = new CodeAreaLineNumberBar(this);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(content == null) init();
        
        ((Graphics2D)g).setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
        
        g.setFont(FONT);
        
        AffineTransform old = ((Graphics2D)g).getTransform();
        lineNumberBar.draw(g);
        ((Graphics2D)g).setTransform(old);
        
        g.translate(lineNumberBar.getWidth(), 0);
        
        old = ((Graphics2D)g).getTransform();
        content.draw(g);
        ((Graphics2D)g).setTransform(old);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    
    
    private static class CodeAreaContent {
        private final CodeArea codeArea;

        public CodeAreaContent(CodeArea codeArea) {
            this.codeArea = codeArea;
        }
        
        private int getWidth(){
            int maxLength = 0;
            for(int i = 0; i < codeArea.lines.length; i++){
                int length = codeArea.lines[i].length() + getTabWidth(i);
                if(length > maxLength) maxLength = length;
            }
            return maxLength * codeArea.fontWidth;
        }
        
        private int getHeight(){
            return (codeArea.fontHeight + VERTICAL_SPACING) * codeArea.lines.length;
        }
        
        private void draw(Graphics g){
            drawBackground(g);
            drawHighlights(g);
            drawText(g);
        }

        private void drawBackground(Graphics g){
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        private void drawHighlights(Graphics g){
            for(Highlight highlight : codeArea.highlights){
                g.setColor(highlight.getColor());
                int x = getTabWidth(highlight.getRow() - 1) + highlight.getColumnBegin() * codeArea.fontWidth;
                int y = (highlight.getRow() - 1) * codeArea.fontHeight;
                int w = (highlight.getColumnEnd() - highlight.getColumnBegin()) * codeArea.fontWidth;
                int h = codeArea.fontHeight;
                if(w == 0) w = codeArea.fontWidth;
                g.fillRect(x, y, w, h);
            }
        }

        private void drawText(Graphics g){
            g.setColor(Color.BLACK);
            for(int i = 0; i < codeArea.lines.length; i++){
                g.translate(0, codeArea.fontHeight + VERTICAL_SPACING);
                g.drawString(codeArea.lines[i], getTabWidth(i), 0);
            }
        }
        
        private int getTabWidth(int i){
            if(i < 0 || i >= codeArea.indentations.length) return 0;
            return codeArea.fontWidth * TAB_SIZE * codeArea.indentations[i];
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    
    
    private static class CodeAreaLineNumberBar {
        private final CodeArea codeArea;

        public CodeAreaLineNumberBar(CodeArea codeArea) {
            this.codeArea = codeArea;
        }

        public int getWidth() {
            return codeArea.fontWidth * ((codeArea.lines.length + "").length() + 2);
        }

        public int getHeight() {
            return (codeArea.fontHeight + VERTICAL_SPACING) * codeArea.lines.length;
        }
        
        private void draw(Graphics g){
            drawBackground(g);
            drawText(g);
        }

        private void drawBackground(Graphics g){
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        private void drawText(Graphics g){
            g.setColor(Color.darkGray);
            for(int i = 0; i < codeArea.lines.length; i++){
                g.translate(0, codeArea.fontHeight + VERTICAL_SPACING);
                g.drawString(" " + (i+1), 0, 0);
            }
        }
    }
}
