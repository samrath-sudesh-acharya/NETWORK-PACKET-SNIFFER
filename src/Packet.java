import org.jnetpcap.Pcap;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JRegistry;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;

import javax.swing.*;
import java.util.Date;


public class Packet extends Thread{
    private static PcapIf device; // interface
    private static StringBuilder errorBuffer; // error string
    public JTextArea textArea; // screen
    private volatile boolean running;
    Packet(PcapIf d,StringBuilder e,JTextArea t){
        device = d;
        errorBuffer = e;
        textArea = t;
    }

    public void terminate()  {
        running = false;
    }

    public void run() {
        while (!running) {
            try {
                String name = (device.getDescription().isEmpty()) ? device.getName()
                        : device.getDescription();
                textArea.setText("\nChoosing "+name+"on your behalf:\n");

            int snapLength = 64 * 1024; // Capture all packets, no truncation
            int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
            int timeout = 10 * 1000; // 10 seconds in millis
            Pcap pcap = Pcap.openLive(device.getName(), snapLength, flags, timeout, errorBuffer);
            if (pcap == null) {
                textArea.setText("Error while opening device for capture: "
                        + errorBuffer);
            } else {
                PcapPacketHandler<String> packetHandler = (packet, user) -> {
                    Ip4 ip4 = new Ip4();
                    Tcp tcp = new Tcp();
                    Payload payload = new Payload();
                    PcapHeader header = new PcapHeader(JMemory.POINTER);
                    Ethernet eth = new Ethernet();
                    Arp arp = new Arp();
                    Http http = new Http();
                    Ip6 ip6 = new Ip6();

                    JBuffer buf = new JBuffer(JMemory.POINTER);
                    int id = JRegistry.mapDLTToId(pcap.datalink());

                    while (pcap.nextEx(header, buf) == Pcap.NEXT_EX_OK) {
                        textArea.append("\n------------------------------------------------------------------------------------------[#"+packet.getFrameNumber()+"]---------------------------------------------------------------------------------------------------\n");
                        packet.scan(id);
                        textArea.append("\n"+new Date(packet.getCaptureHeader().timestampInMillis()));
                        if (packet.hasHeader(eth)) {
                            textArea.append(("\nMAC : "+FormatUtils.mac(eth.source())+"\n"));
                            textArea.append(("\nMAC DESTINATION: "+FormatUtils.mac(eth.destination())+"\n"));
                        }
                        if (packet.hasHeader(ip4)) {
                            textArea.append(("\nIP4 : "+FormatUtils.ip(ip4.source())+"\n"));
                            textArea.append(("\nIP4 DESTINATION: "+FormatUtils.ip(ip4.destination())+"\n"));
                        }

                        if (packet.hasHeader(ip6)) {
                            textArea.append(("\nIP6: "+FormatUtils.ip(ip6.source())+"\n"));
                            textArea.append(("\nIP6 DESTINATION: "+FormatUtils.ip(ip6.destination())+"\n"));
                        }

                        if (packet.hasHeader(tcp)) {
                            textArea.append(("\nTCP PORT: "+tcp.source()+"\n"));
                            textArea.append(("\nPORT: "+tcp.destination()+"\n"));
                            textArea.append(("\nTCP FLAG: "+tcp.flags()+"\n"));
                            textArea.append(("\nTCP HASH CODE: "+tcp.hashCode()+"\n"));
                        }

                        if (packet.hasHeader(payload)) {
                            textArea.append(("\nPAYLOAD LOAD: "+payload.getLength()+"\n"));
                            textArea.append((payload.toHexdump()));
                        }

                        if (packet.hasHeader(arp)) {
                            textArea.append("\nHardware type" + arp.hardwareType());
                            textArea.append("\nProtocol type" + arp.protocolType());
                            textArea.append("\nPacket:" + arp.getPacket());
                            textArea.append(("\n"));
                        }

                        if (packet.hasHeader(http)) {
                            textArea.append(("\nHTTP DESCRIPTION" + http.getDescription()));
                            textArea.append(("\nHTTP NAME" + http.getName()));
                            textArea.append(("\nHTTP HEADER" + http.header()));
                            textArea.append((http.toHexdump()));
                            textArea.append(("\n"));
                        }
                        textArea.append("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }


                };
                pcap.loop(1, packetHandler, "jNetPcap");
                pcap.close();
            }

        }catch (Exception e){
                System.out.println("Thread interrupted");
            }
        }
        System.out.println("Thread terminated");
    }
}

