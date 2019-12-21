package cz.mg.compilerexplorer.gui;

import cz.mg.compilerexplorer.core.Explorer;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class TaskExplorer extends NodeExplorer {
    private final Explorer explorer;
    private final JTextField path;


    private final KeyAdapter keyAdapter = new KeyAdapter() {
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

    public TaskExplorer() {
        getListOfParts().addKeyListener(keyAdapter);
    }

    private void update(){
        State state = explorer.getState();
        listOfParts.updateState(state.getParts());
        listOfInfos.updateState(state.getInfos());
        listOfLinks.updateState(state.getLinks());
        listOfParts.setSelectedIndex(explorer.getHistory().get().getSelectedChildIndex());
        updatePath();
    }
}
