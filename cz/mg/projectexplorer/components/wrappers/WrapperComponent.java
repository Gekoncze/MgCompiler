package cz.mg.projectexplorer.components.wrappers;

import cz.mg.projectexplorer.utilities.NavigationListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public abstract class WrapperComponent<T> {
    private final JPanel panel;
    private T object;

    public WrapperComponent(JPanel panel, T object) {
        if(panel == null) throw new NullPointerException();
        this.panel = panel;
        this.object = object;
        rebuild();
    }

    public JPanel getPanel() {
        return panel;
    }
    
    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
        rebuild();
    }
    
    public final void rebuild(){
        panel.removeAll();
        createComponents(panel);
        panel.revalidate();
		panel.repaint();
    }
    
    protected abstract void createComponents(JPanel panel);
    public abstract void onKeyEvent(KeyEvent e);
    public abstract void onMouseEvent(MouseEvent e);
    public abstract void setNavigationListener(NavigationListener navigationListener);
}
