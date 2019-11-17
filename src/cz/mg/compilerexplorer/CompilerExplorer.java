package cz.mg.compilerexplorer;

import cz.mg.compiler.Compiler;

import javax.swing.*;
import java.awt.*;


public class CompilerExplorer extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Compiler Explorer";

    private final Compiler compiler;

    public CompilerExplorer(Compiler compiler) {
        this.compiler = compiler;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(8, 8, 8, 8);
        constraints.fill = GridBagConstraints.BOTH;

        JList list = new JList(new String[]{ "AAA", "BBB", "CCC" });
        list.setLayoutOrientation(JList.VERTICAL);
        getContentPane().add(list, constraints);
    }
}
