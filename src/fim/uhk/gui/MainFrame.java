package fim.uhk.gui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        initFrame() ;
        initGui();
    }

    public void initFrame() {
        setTitle("PRO2 - Shopping cart");
        setSize(800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void initGui() {

    }
}
