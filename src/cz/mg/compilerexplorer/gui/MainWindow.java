package cz.mg.compilerexplorer.gui;

import cz.mg.compiler.Compiler;
import cz.mg.compilerexplorer.core.CompilerExplorer;
import cz.mg.compilerexplorer.core.Node;
import cz.mg.compilerexplorer.core.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.StringJoiner;


public class MainWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDING = 8;
    public static final String TITLE = "Compiler Explorer";

    private final CompilerExplorer compilerExplorer;
    private final NodeList listOfParts;
    private final NodeList listOfInfos;
    private final NodeList listOfLinks;
    private final MainPanel mainPanel;
    private final JTextField path;
    private final GridBagConstraintFactory constraintFactory = new GridBagConstraintFactory();

    private final KeyAdapter windowKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                back();
            }

            if(e.getKeyCode() == KeyEvent.VK_LEFT && e.isAltDown()){
                back();
            }

            if(e.getKeyCode() == KeyEvent.VK_RIGHT && e.isAltDown()){
                forward();
            }
        }
    };

    private final KeyAdapter listKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                NodeList list = (NodeList) e.getComponent();
                if(list.getSelectedValue() != null){
                    open(list.getSelectedValue());
                }
            }

            if(e.getKeyCode() == KeyEvent.VK_UP){
                cacheSelectedItem(-1);
            }

            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                cacheSelectedItem(+1);
            }
        }
    };

    public MainWindow(Compiler compiler) {
        this.compilerExplorer = new CompilerExplorer(compiler);

        setupComponent();

        this.path = new JTextField();
        this.path.setEditable(false);
        getContentPane().add(path, constraintFactory.create(0, 0, true, false, PADDING));

        this.mainPanel = new MainPanel(
                this.listOfParts = new NodeList(),
                this.listOfInfos = new NodeList(),
                this.listOfLinks = new NodeList()
        );
        getContentPane().add(mainPanel, constraintFactory.create(0, 1, true, true, 0));

        addListeners();
        update();
    }

    private void setupComponent(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridBagLayout());
    }

    private void addListeners(){
        listOfParts.addKeyListener(listKeyAdapter);
        addGlobalListeners(getContentPane());
    }

    private void addGlobalListeners(Component component){
        component.addKeyListener(windowKeyAdapter);
        if(component instanceof Container){
            for(Component child : ((Container)component).getComponents()) {
                addGlobalListeners(child);
            }
        }
    }

    private void update(){
        State state = compilerExplorer.getState();
        listOfParts.updateState(state.getParts());
        listOfInfos.updateState(state.getInfos());
        listOfLinks.updateState(state.getLinks());
        listOfParts.setSelectedIndex(compilerExplorer.getHistory().get().getSelectedChildIndex());
        updatePath();
    }

    private void updatePath(){
        StringJoiner names = new StringJoiner("/");
        for(Node node : compilerExplorer.getHistory().getPath()){
            names.add(node.getName());
            if(node == compilerExplorer.getHistory().get()) break;
        }
        path.setText("/" + names.toString());
    }

    private void open(Node node){
        compilerExplorer.open(node);
        update();
    }

    private void back(){
        compilerExplorer.back();
        update();
    }

    private void forward(){
        compilerExplorer.forward();
        update();
    }

    private void cacheSelectedItem(int delta){
        compilerExplorer.getHistory().get().setSelectedChildIndex(listOfParts.getSelectedIndex() + delta);
    }
}
