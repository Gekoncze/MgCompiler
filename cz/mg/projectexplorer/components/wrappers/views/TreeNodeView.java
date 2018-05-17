package cz.mg.projectexplorer.components.wrappers.views;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.collections.chainlist.ChainListItem;
import cz.mg.collections.node.TreeNode;
import cz.mg.projectexplorer.utilities.ClickTracker;
import cz.mg.projectexplorer.components.extensions.NavigationIcon;
import cz.mg.projectexplorer.components.wrappers.WrapperComponent;
import cz.mg.projectexplorer.utilities.NavigationListener;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public class TreeNodeView extends WrapperComponent<TreeNode> {
	private ChainListItem<NavigationIcon> selectedIconItem = null;
	private NavigationListener navigationListener = null;
	private ClickTracker clickTracker;
    private ChainList<NavigationIcon> icons;
	
	public TreeNodeView(JPanel panel, TreeNode node) {
		super(panel, node);
	}
    
    @Override
    protected void createComponents(JPanel panel) {
        panel.setLayout(new FlowLayout(java.awt.FlowLayout.LEFT, 8, 8));
        
        icons = new ChainList<>();
        clickTracker = new ClickTracker();
        
        selectedIconItem = null;
		
        ChainList<TreeNode> nodes = getObject().getChildren();
		for(TreeNode currentNode : nodes){
			NavigationIcon icon = new NavigationIcon(currentNode);
            icons.addLast(icon);
            final ChainListItem<NavigationIcon> iconItem = icons.getLastItem();
			icon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(clickTracker.click() == 1){
						selectedIconItem = iconItem;
						refreshContentSelection();
					} else {
						clickTracker.reset();
						if(navigationListener != null) navigationListener.navigate(currentNode);
					}
				}
			});
			panel.add(icon);
		}
		refreshContentSelection();
    }
	
	private void refreshContentSelection(){
        JPanel panel = getPanel();
		for(int i = 0; i < panel.getComponentCount(); i++){
			Component component = panel.getComponent(i);
			if(component instanceof NavigationIcon){
				NavigationIcon icon = (NavigationIcon) component;
                boolean selected = false;
                if(selectedIconItem != null) selected = icon == selectedIconItem.getData();
				icon.setSelected(selected);
			}
		}
	}

    @Override
	public void setNavigationListener(NavigationListener navigationListener) {
		this.navigationListener = navigationListener;
	}
    
    private void selectLeft(){
        if(selectedIconItem == null) selectedIconItem = icons.getLastItem();
        else selectedIconItem = selectedIconItem.getPreviousItem();
        refreshContentSelection();
    }
    
    private void selectRight(){
        if(selectedIconItem == null) selectedIconItem = icons.getFirstItem();
        else selectedIconItem = selectedIconItem.getNextItem();
        refreshContentSelection();
    }
    
    private void enter(){
        if(selectedIconItem == null) return;
        if(navigationListener != null) navigationListener.navigate(selectedIconItem.getData().getNode());
    }

    @Override
    public void onKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED){
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                selectLeft();
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                selectRight();
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                enter();
            }
        }
    }

    @Override
    public void onMouseEvent(MouseEvent e) {
        if(e.getID() == MouseEvent.MOUSE_CLICKED){
            selectedIconItem = null;
            refreshContentSelection();
        }
    }
}
