package cz.mg.projectexplorer.components.wrappers;

import cz.mg.projectexplorer.components.extensions.NavigationButton;
import cz.mg.collections.chainlist.ChainList;
import cz.mg.collections.node.TreeNode;
import cz.mg.projectexplorer.utilities.NavigationListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public class NavigationPanel extends WrapperComponent<TreeNode> {
	private NavigationListener listener = null;

	public NavigationPanel(JPanel panel, TreeNode node) {
        super(panel, node);
    }
    
    @Override
    protected void createComponents(JPanel panel) {
        ChainList<TreeNode> path = new ChainList<>();
        TreeNode current = getObject();
        while(current != null){
            path.addFirst(current);
            current = current.getParent();
        }
        
		for(TreeNode object : path){
			NavigationButton button = new NavigationButton(object);
            if(object == path.getLast()) button.setSelected(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(listener != null) listener.navigate(button.getNode());
				}
			});
			panel.add(button);
		}
    }
	
    @Override
	public void setNavigationListener(NavigationListener listener){
		this.listener = listener;
	}
    
    @Override
    public void onKeyEvent(KeyEvent e){
        if(e.getID() == KeyEvent.KEY_PRESSED){
            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                navigateBack();
            }
        }
    }
    
    @Override
    public void onMouseEvent(MouseEvent e) {
    }
    
    private void navigateBack(){
        JPanel panel = getPanel();
        if(panel.getComponentCount() < 2) return;
        NavigationButton button = (NavigationButton) panel.getComponent(panel.getComponentCount() - 2);
        button.doClick();
    }
}
