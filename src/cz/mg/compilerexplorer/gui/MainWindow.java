package cz.mg.compilerexplorer.gui;

import cz.mg.compiler.Compiler;
import cz.mg.compilerexplorer.core.CompilerExplorer;
import cz.mg.compilerexplorer.core.Node;
import cz.mg.compilerexplorer.core.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Compiler Explorer";

    private final CompilerExplorer compilerExplorer;
    private final NodeList listOfParts;
    private final NodeList listOfInfos;
    private final NodeList listOfLinks;
    private final MainPanel mainPanel;
    private final GridBagConstraintFactory constraintFactory = new GridBagConstraintFactory(8);

    private final KeyAdapter listKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                NodeList list = (NodeList) e.getComponent();
                if(list.getSelectedValue() != null){
                    open(list.getSelectedValue());
                }
            }

            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                back();
            }
        }
    };

    public MainWindow(Compiler compiler) {
        this.compilerExplorer = new CompilerExplorer(compiler);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        this.listOfParts = new NodeList();
        this.listOfInfos = new NodeList();
        this.listOfLinks = new NodeList();

        this.mainPanel = new MainPanel(listOfParts, listOfInfos, listOfLinks);
        getContentPane().add(mainPanel, constraintFactory.create());

        listOfParts.addKeyListener(listKeyAdapter);

        update();
    }

    private void update(){
        State state = compilerExplorer.getState();
        listOfParts.updateState(state.getParts());
        listOfInfos.updateState(state.getInfos());
        listOfLinks.updateState(state.getLinks());
    }

    private void open(Node node){
        compilerExplorer.open(node);
        update();
    }

    private void back(){
        compilerExplorer.back();
        update();
    }
}
