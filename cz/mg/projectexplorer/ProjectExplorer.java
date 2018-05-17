package cz.mg.projectexplorer;

import cz.mg.projectexplorer.utilities.NavigationListener;
import cz.mg.projectexplorer.maps.Views;
import cz.mg.projectexplorer.components.wrappers.NavigationPanel;
import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.Compiler;
import cz.mg.projectexplorer.components.wrappers.WrapperComponent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ProjectExplorer extends javax.swing.JFrame implements NavigationListener {
	private final NavigationPanel navigation;
	private WrapperComponent view = null;
	
	public ProjectExplorer() {
		initComponents();
        
        Compiler compiler = new Compiler("project.mg");
        compiler.run();
		
		navigation = new NavigationPanel(jPanelNavigation, compiler);
		navigation.setNavigationListener(this);
        
        navigate(compiler);
        addKeyboardListener();
        addMouseListener();
	};
    
    private void addKeyboardListener(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                navigation.onKeyEvent(e);
                view.onKeyEvent(e);
                return false;
            }
        });
    }
    
    private void addMouseListener(){
        jPanelContent.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.onMouseEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                view.onMouseEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                view.onMouseEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                view.onMouseEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                view.onMouseEvent(e);
            }
        });
    }

	@Override
	public final void navigate(TreeNode node) {
        view = Views.getView(jPanelContent, node);
        view.setNavigationListener(this);
		navigation.setObject(node);
        updateStatus(node);
	}
    
    private void updateStatus(TreeNode node){
        jLabelStatus.setText(node.getClass().getSimpleName() + ": " + node.toString());
    }

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelNavigation = new javax.swing.JPanel();
        jButtonDummy = new javax.swing.JButton();
        jPanelContent = new javax.swing.JPanel();
        jPanelStatusBar = new javax.swing.JPanel();
        jLabelStatus = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Project Explorer");
        setPreferredSize(new java.awt.Dimension(1024, 768));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWeights = new double[] {1.0};
        layout.rowWeights = new double[] {0.0, 1.0, 0.0};
        getContentPane().setLayout(layout);

        jPanelNavigation.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonDummy.setText("...");
        jPanelNavigation.add(jButtonDummy);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanelNavigation, gridBagConstraints);

        jPanelContent.setBackground(new java.awt.Color(255, 255, 255));
        jPanelContent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelContent.setLayout(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jPanelContent, gridBagConstraints);

        java.awt.GridBagLayout jPanelStatusBarLayout = new java.awt.GridBagLayout();
        jPanelStatusBarLayout.columnWeights = new double[] {1.0};
        jPanelStatusBarLayout.rowWeights = new double[] {1.0};
        jPanelStatusBar.setLayout(jPanelStatusBarLayout);

        jLabelStatus.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelStatusBar.add(jLabelStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanelStatusBar, gridBagConstraints);

        jMenuFile.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Exit");
        jMenuFile.add(jMenuItem1);

        jMenuBar.add(jMenuFile);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ProjectExplorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ProjectExplorer().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDummy;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelNavigation;
    private javax.swing.JPanel jPanelStatusBar;
    // End of variables declaration//GEN-END:variables
}
