package cz.mg.compilerexplorer.gui;

import cz.mg.compiler.Compiler;
import cz.mg.compilerexplorer.core.Explorer;
import cz.mg.compilerexplorer.core.Node;
import cz.mg.compilerexplorer.core.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringJoiner;


public class MainWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDING = 8;
    public static final String TITLE = "Compiler Explorer";

    private final TaskExplorer taskExplorer;
    private final GridBagConstraintFactory constraintFactory = new GridBagConstraintFactory();

    private final KeyAdapter globalKeyAdapter = new KeyAdapter() {
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

    private final WindowAdapter windowAdapter = new WindowAdapter() {
        @Override
        public void windowOpened(WindowEvent e) {
            listOfParts.requestFocus();
        }
    };

    public MainWindow(Compiler compiler) {
        this.explorer = new Explorer(compiler);

        setupComponent();

        this.path = new JTextField();
        this.path.setEditable(false);
        getContentPane().add(path, constraintFactory.create(0, 0, true, false, PADDING));

        this.taskExplorer = new TaskExplorer();
        getContentPane().add(taskExplorer, constraintFactory.create(0, 1, true, true, 0));

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
        addWindowListener(windowAdapter);
    }

    private void addGlobalListeners(Component component){
        component.addKeyListener(globalKeyAdapter);
        if(component instanceof Container){
            for(Component child : ((Container)component).getComponents()) {
                addGlobalListeners(child);
            }
        }
    }



    private void updatePath(){
        StringJoiner names = new StringJoiner("/");
        for(Node node : explorer.getHistory().getPath()){
            names.add(node.getName());
            if(node == explorer.getHistory().get()) break;
        }
        path.setText("/" + names.toString());
    }

    private void open(Node node){
        explorer.open(node);
        update();
    }

    private void back(){
        explorer.back();
        update();
    }

    private void forward(){
        explorer.forward();
        update();
    }

    private void cacheSelectedItem(int delta){
        explorer.getHistory().get().setSelectedChildIndex(listOfParts.getSelectedIndex() + delta);
    }
}
