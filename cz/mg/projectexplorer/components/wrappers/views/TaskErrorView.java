package cz.mg.projectexplorer.components.wrappers.views;

import cz.mg.compiler.tasks.TaskError;
import cz.mg.projectexplorer.components.extensions.TaskErrorPanel;
import cz.mg.projectexplorer.components.wrappers.WrapperComponent;
import cz.mg.projectexplorer.utilities.NavigationListener;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public class TaskErrorView extends WrapperComponent<TaskError> {
    public TaskErrorView(JPanel panel, TaskError object) {
        super(panel, object);
    }
    
    @Override
    protected void createComponents(JPanel panel) {
        panel.setLayout(new GridLayout(1, 1));
        panel.add(new TaskErrorPanel(getObject()));
    }

    @Override
    public void onKeyEvent(KeyEvent e) {
    }

    @Override
    public void onMouseEvent(MouseEvent e) {
    }
    
    @Override
    public void setNavigationListener(NavigationListener navigationListener) {
    }
}
