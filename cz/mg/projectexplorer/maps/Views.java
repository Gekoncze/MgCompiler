package cz.mg.projectexplorer.maps;

import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.tasks.TaskError;
import cz.mg.projectexplorer.components.wrappers.WrapperComponent;
import cz.mg.projectexplorer.components.wrappers.views.TaskErrorView;
import cz.mg.projectexplorer.components.wrappers.views.TreeNodeView;
import javax.swing.JPanel;


public class Views {
    public static WrapperComponent getView(JPanel parentPanel, TreeNode node){
        if(node instanceof TaskError) return new TaskErrorView(parentPanel, (TaskError) node);
        return new TreeNodeView(parentPanel, node);
    }
}
