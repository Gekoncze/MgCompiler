package cz.mg.compilerexplorer.gui;

import javax.swing.*;
import java.awt.*;


public class NodeExplorer extends JPanel {
    public static final int PADDING = 8;

    private final GridBagConstraintFactory constraintFactory = new GridBagConstraintFactory();
    private final NodeList listOfParts = new NodeList();
    private final NodeList listOfInfos = new NodeList();
    private final NodeList listOfLinks = new NodeList();

    public NodeExplorer() {
        setLayout(new GridBagLayout());

        JSplitPane horizontalSplitPane = new JSplitPane();
        horizontalSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        horizontalSplitPane.setResizeWeight(0.5);

        JSplitPane verticalSplitPane = new JSplitPane();
        verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        verticalSplitPane.setResizeWeight(0.5);

        add(horizontalSplitPane, constraintFactory.create(0, 0, true, true, PADDING));
        horizontalSplitPane.setRightComponent(verticalSplitPane);

        horizontalSplitPane.setLeftComponent(listOfParts);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.add(new JLabel("Properties"), constraintFactory.create(0, 0, true, false, PADDING));
        topPanel.add(listOfInfos, constraintFactory.create(0, 1, true, true, PADDING));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.add(new JLabel("Links"), constraintFactory.create(0, 0, true, false, PADDING));
        bottomPanel.add(listOfLinks, constraintFactory.create(0, 1, true, true, PADDING));

        verticalSplitPane.setLeftComponent(topPanel);
        verticalSplitPane.setRightComponent(bottomPanel);
    }

    public NodeList getListOfParts() {
        return listOfParts;
    }

    public NodeList getListOfInfos() {
        return listOfInfos;
    }

    public NodeList getListOfLinks() {
        return listOfLinks;
    }
}
