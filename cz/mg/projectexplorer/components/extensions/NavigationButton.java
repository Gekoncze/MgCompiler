package cz.mg.projectexplorer.components.extensions;

import cz.mg.collections.node.TreeNode;
import cz.mg.projectexplorer.maps.Names;
import javax.swing.JToggleButton;


public class NavigationButton extends JToggleButton {
	private final TreeNode node;

	public NavigationButton(TreeNode node) {
		super(Names.getName(node));
		this.node = node;
	}

    public TreeNode getNode() {
        return node;
    }
}
