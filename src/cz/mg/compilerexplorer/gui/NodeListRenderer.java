package cz.mg.compilerexplorer.gui;

import cz.mg.compilerexplorer.core.Node;
import javax.swing.*;
import java.awt.*;


public class NodeListRenderer extends JLabel implements ListCellRenderer<Node> {
    public NodeListRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Node> list, Node node, int i, boolean isSelected, boolean cellHasFocus) {
        if (isSelected && list.hasFocus()) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setText(nodeToString(node));
        return this;
    }

    private static String nodeToString(Node node) {
        return node.getElement().getClass().getSimpleName() + " " + node.getName();
    }

    @Deprecated
    private static String sanitize(String s){
        if(s.contains("\n")) s = s.substring(0, s.indexOf("\n"));
        if(s.length() > 100) s = s.substring(0, 100);
        return s;
    }
}