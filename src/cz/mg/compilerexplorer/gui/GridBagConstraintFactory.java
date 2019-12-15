package cz.mg.compilerexplorer.gui;

import java.awt.*;


public class GridBagConstraintFactory {
    private final int[] padding = new int[4];

    public GridBagConstraintFactory(int padding) {
        this.padding[0] = padding;
        this.padding[1] = padding;
        this.padding[2] = padding;
        this.padding[3] = padding;
    }

    public GridBagConstraints create(){
        return create(0, 0, true, true);
    }

    public GridBagConstraints create(int x, int y, boolean horizontalFill, boolean verticalFill){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = horizontalFill ? 1 : 0;
        constraints.weighty = verticalFill ? 1 : 0;
        constraints.insets = new Insets(padding[0], padding[1], padding[2], padding[3]);
        if(!horizontalFill && !verticalFill) constraints.fill = GridBagConstraints.NONE;
        if(horizontalFill && verticalFill) constraints.fill = GridBagConstraints.BOTH;
        if(horizontalFill && !verticalFill) constraints.fill = GridBagConstraints.HORIZONTAL;
        if(!horizontalFill && verticalFill) constraints.fill = GridBagConstraints.VERTICAL;
        return constraints;
    }
}
