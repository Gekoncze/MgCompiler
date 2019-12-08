package cz.mg.compilerexplorer.gui;

import cz.mg.collections.Collection;
import cz.mg.compiler.Compiler;
import cz.mg.compilerexplorer.Core;
import cz.mg.compilerexplorer.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Compiler Explorer";

    private final Core core;
    private final JList<Node> list;

    private final KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
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
        this.core = new Core(compiler);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(8, 8, 8, 8);
        constraints.fill = GridBagConstraints.BOTH;

        this.list = new JList<>();
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new NodeListRenderer());
        getContentPane().add(list, constraints);

        addKeyListener(keyAdapter);
        list.addKeyListener(keyAdapter);

        update();
    }

    private void update(){
        Collection<Node> state = core.getState();
        list.setModel(new NodeListModel(state));
        if(state.count() > 0) list.setSelectedIndex(0);
    }

    private void open(Node node){
        core.open(node);
        update();
    }

    private void back(){
        core.back();
        update();
    }
}
