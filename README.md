# NETWORK-PACKET-SNIFFER

This tool is designed to capture packets sent to and from your system by sniffing the network traffic passing through a selected interface. It uses the jnetpcap library to capture the specific details of each packet, including the protocol it belongs to and any binary data stored within it.

## HOW IT WORKS 

The tool works by opening a network stream and listening to all ports on the selected interface. When a packet is detected, it is captured and its contents are read using the jnetpcap library. The tool then analyzes the packet to determine its protocol and extract any relevant data stored within it.

Captured packets are displayed on the screen in real-time as they are received, allowing the user to monitor network traffic in real-time. The tool can also send captured packet data to another system for analysis or storage.

## ARCHITECTURE

The packet sniffing tool is built using Java and the jnetpcap library. The main class creates a packet capture instance and sets it to capture packets on the selected interface. A packet handler is registered with the capture instance to process each captured packet.

The packet handler uses the jnetpcap library to extract information from each packet, including its protocol, source and destination IP addresses, and any data stored within the packet. This information is then displayed on the screen in real-time.

## USAGE

To use the packet sniffing tool, simply run the Java application and select the interface you wish to monitor. The tool will begin capturing packets on that interface and displaying their contents on the screen.

<b>Results on a Windows Virtual Machine</b>

<img width=800 height=500 src="https://user-images.githubusercontent.com/76547134/175362277-a9a182a8-1bf9-4e4e-9ec1-6244c01a2512.png"/>
<img width=800 height=500 src="https://user-images.githubusercontent.com/76547134/175362271-832b2c6a-f9d8-449d-9b7d-240e2c3bfcb0.png"/>

## INSTALLATION

Clone the project from Github

```
git clone https://github.com/samrath-sudesh-acharya/NETWORK-PACKET-SNIFFER.git
```

Move to the source folder

```
cd src/
```

Run Main.java file

```
javac Main.java
java Main
```

---
The packet sniffing tool is a powerful and versatile tool for monitoring network traffic and analyzing packet data. It is easy to use and highly configurable, making it a great choice for both novice and experienced users alike. If you have any questions or comments about the tool, please feel free to reach out to the developer or community for support.
