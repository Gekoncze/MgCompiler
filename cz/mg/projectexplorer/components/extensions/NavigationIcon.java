package cz.mg.projectexplorer.components.extensions;

import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.tasks.Task;
import cz.mg.projectexplorer.maps.Icons;
import cz.mg.projectexplorer.maps.Names;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;


public class NavigationIcon extends JLabel {
	private static final Color COLOR_NOT_SELECTED = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	private static final Color COLOR_SELECTED = new Color(0.8f, 0.9f, 1.0f, 1.0f);
	private boolean selected = false;
	private final TreeNode node;
    private final boolean hasErrors;
	
	public NavigationIcon(TreeNode node) {
		super(Names.getName(node), Icons.getIcon(node), JLabel.CENTER);
		this.node = node;
		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		setOpaque(true);
        if(node instanceof Task){
            hasErrors = ((Task) node).hasErrors();
        } else {
            hasErrors = false;
        }
	}

    public TreeNode getNode() {
        return node;
    }
	
	public void setSelected(boolean value){
		selected = value;
		if(selected){
			setBackground(COLOR_SELECTED);
		} else {
			setBackground(COLOR_NOT_SELECTED);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        int x = getWidth() / 2 + 4;
        int y = 0;
		if(hasErrors) g.drawImage(((BufferedImageIcon)Icons.getErrorIcon()).getImage(), x, y, 32, 32, this);
	}
}
