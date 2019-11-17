package cz.mg.compiler.cli.consoles;

import cz.mg.compiler.cli.Console;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class WindowConsole implements Console {
    private CliWindow window = null;
    private LinkedList<String> commands = new LinkedList<>();

    public WindowConsole() {
        run();
    }

    @Override
    public void out(String s) {
        write(s);
    }

    @Override
    public String in() {
        while(!hasCommands()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String command = removeCommand();
        write(">>> " + command + "\n");
        return command;
    }

    private boolean hasCommands(){
        synchronized (commands){
            return commands.size() != 0;
        }
    }

    private String removeCommand(){
        synchronized (commands){
            return commands.removeLast();
        }
    }

    private void addCommand(String command){
        synchronized (commands){
            commands.addLast(command);
        }
    }

    private void write(String s){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window.write(s);
            }
        });
    }

    private void run() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CliWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new CliWindow();
                window.setVisible(true);
            }
        });
    }

    public class CliWindow extends javax.swing.JFrame {
        private Font font = new Font("Ubuntu Mono", Font.PLAIN, 20);

        public CliWindow() {
            initComponents();
            setSize(1024, 768);
            setLocationRelativeTo(null);
            jTextFieldInput.requestFocus();
            jTextAreaOutput.setFont(font);
            jTextFieldInput.setFont(font);
        }

        public void write(String s){
            jTextAreaOutput.append(s);
        }

        private void initComponents() {
            java.awt.GridBagConstraints gridBagConstraints;

            jScrollPane1 = new javax.swing.JScrollPane();
            jTextAreaOutput = new javax.swing.JTextArea();
            jTextFieldInput = new javax.swing.JTextField();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("Compilation explorer");
            java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
            layout.columnWeights = new double[] {1.0};
            layout.rowWeights = new double[] {1.0};
            getContentPane().setLayout(layout);

            jTextAreaOutput.setEditable(false);
            jTextAreaOutput.setColumns(20);
            jTextAreaOutput.setRows(5);
            jScrollPane1.setViewportView(jTextAreaOutput);

            jTextFieldInput.addActionListener(this::jTextFieldInputActionPerformed);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
            getContentPane().add(jScrollPane1, gridBagConstraints);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 4);
            getContentPane().add(jTextFieldInput, gridBagConstraints);

            pack();
        }

        private void jTextFieldInputActionPerformed(java.awt.event.ActionEvent evt) {
            addCommand(jTextFieldInput.getText());
            jTextFieldInput.setText("");
        }

        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea jTextAreaOutput;
        private javax.swing.JTextField jTextFieldInput;
    }
}

