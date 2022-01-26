import org.jnetpcap.PcapIf;
import javax.swing.*;
import java.awt.*;


public  class Main  {
    void getInterface(java.util.List<PcapIf> allDevices) {
        // Frame
        JFrame frame = new JFrame();
        frame.setTitle("Network Packet Sniffer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);
        frame.setLayout(new

                BorderLayout());
        frame.setBackground(Color.decode("#F5F5F5"));
        frame.setVisible(true);

        // Choose your interface
        JLabel label1 = new JLabel();
        label1.setText("Choose your interface");
        label1.setHorizontalAlignment(JLabel.LEFT);
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setFont(new

                Font("MV Bali", Font.BOLD, 25));

        // @Copyrights Network Packet Sniffer
        JLabel label2 = new JLabel();
        label2.setText("@Copyrights Network Packet Sniffer");
        label2.setHorizontalAlignment(JLabel.LEFT);
        label2.setVerticalAlignment(JLabel.TOP);
        label2.setFont(new

                Font("MV Bali", Font.PLAIN, 18));

        // Capture the network traffic
        JLabel label3 = new JLabel("Capture the network traffic");

        label3.setHorizontalAlignment(JLabel.LEFT);
        label3.setVerticalAlignment(JLabel.TOP);
        label3.setFont(new

                Font("MV Bali", Font.PLAIN, 20));

        // Panel 1
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new

                Dimension(80, 50));
        panel1.setBackground(Color.decode("#F05454"));
        panel1.add(label1);

        // Panel2
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.decode("#F05454"));
        panel2.setPreferredSize(new

                Dimension(10, 50));
        panel2.add(panel1);

        // panel3
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new

                Dimension(300, 140));
        panel3.setBackground(Color.decode("#F5F5F5"));
        panel3.add(label3);

        // panel4
        JPanel panel4 = new JPanel();
        panel4.setPreferredSize(new

                Dimension(80, 40));
        panel4.setBackground(Color.decode("#121212"));
        panel4.add(label2);



        // List of interface displayed in the GUI
        int j =0;
        for (PcapIf device : allDevices) {

            String description = (device.getDescription() != null) ? device
                    .getDescription() : device.getName();
            // Button
            JButton button = new JButton(description);
            button.setBounds(50, 100, 95, 30);
            button.setBorder(new RoundedBorder(30));
            button.setBackground(Color.decode("#F05454"));
            button.setForeground(Color.decode("#FFFFFF"));

            // button border
            button.setBorder(new RoundedBorder(10));


            panel2.add(button);
            button.setFocusable(false);
            int finalJ = j;

            //button functionality
            button.addActionListener(e -> {frame.dispose();new SpoofPage(allDevices.get(finalJ));
            });
            j++;
            panel2.setLayout(new GridLayout(j+1, 0, 0, 0));
        }

        // Adding panels in the frame with their position in the frame
        frame.add(panel3,BorderLayout.LINE_START);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel4,BorderLayout.SOUTH);
    }
}
