// 
// Decompiled by Procyon v0.5.36
// 

package Benz.mod;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ExternalGUI
{
    private JFrame frame;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final ExternalGUI window = new ExternalGUI();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ExternalGUI() {
        this.initialize();
    }
    
    private void initialize() {
        (this.frame = new JFrame()).setBounds(100, 100, 450, 300);
        this.frame.setDefaultCloseOperation(3);
    }
}
