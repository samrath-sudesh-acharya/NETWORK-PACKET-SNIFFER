import javax.swing.*;

public class MyFrame extends JFrame {
    MyFrame(){
        JFrame frame = new JFrame();
        this.setTitle("Network Packet Sniffer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);
        this.setResizable(false);
        this.setVisible(true);
    }
}
