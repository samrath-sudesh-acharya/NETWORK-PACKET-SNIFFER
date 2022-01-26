import java.util.ArrayList;
import java.util.List;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class PackageCapture {
    public static void main(String[] args) {
        // List of interface
        List<PcapIf> allDevices = new ArrayList<>();

        // Error String Builder
        StringBuilder errorBuffer = new StringBuilder();

        // Checking whether received the list of interface
        int r = Pcap.findAllDevs(allDevices, errorBuffer);
        if (r != Pcap.OK || allDevices.isEmpty()) {
            System.err.printf("Can't read list of devices, error is %s",
                    errorBuffer);
            return;
        }

        // Calling the Main Page GUI
        Main obj1 = new Main();

        // Sending the error and list of interface to the SpoofPage(2nd page)
        new SpoofPage(errorBuffer);
        obj1.getInterface(allDevices);
    }
}

