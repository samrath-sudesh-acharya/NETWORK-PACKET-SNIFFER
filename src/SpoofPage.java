import org.jnetpcap.PcapIf;
import javax.swing.*;
import java.awt.*;

public class SpoofPage  {
    JFrame frame = new JFrame();
    JButton myButton1 = new JButton("Run");
    JButton myButton2 = new JButton("Stop");
    PcapIf device; // interface
    StringBuilder errorBuffer = new StringBuilder();
    public JTextArea textArea = new JTextArea (); // Screen which display packet info

    SpoofPage(PcapIf finalJ){
        // make the screen scrollable
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().setLayout(new FlowLayout());

        // Getting the device from the Main page through the constructor
        this.device = finalJ;
        frame.setTitle("Network Packet Sniffer");
        myButton1.setBounds(100,160,200,40);
        myButton1.setFocusable(false);
        myButton2.setBounds(100,160,200,40);
        myButton2.setFocusable(false);
        myButton2.setVisible(false);

        Packet p = new Packet(device,errorBuffer,textArea);

        // Run button working condition
        myButton1.addActionListener(e -> {myButton1.setVisible(false);
            myButton2.setVisible(true);if(!p.isAlive()){p.start();}});

        // Stop button working condition
        myButton2.addActionListener(e -> {myButton1.setVisible(true);
            myButton2.setVisible(false);
            try {
                p.sleep(5000); //sleep the sniffing thread for 5s
                p.terminate(); // terminating the thread
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Panel1
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setLayout(new FlowLayout());
        frame.getContentPane().add(scrollableTextArea,BorderLayout.CENTER);
        panel1.add(myButton1);
        panel1.add(myButton2);

        //Add panel to frame
        frame.add(panel1, BorderLayout.SOUTH);
    }

    SpoofPage(StringBuilder error){
        errorBuffer.append(error);
    }

}