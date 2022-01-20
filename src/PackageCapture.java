import java.util.ArrayList;
import java.util.List;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class PackageCapture {
    public static void main(String[] args) {
        List<PcapIf> allDevices = new ArrayList<>();
        StringBuilder errorBuffer = new StringBuilder();
        int r = Pcap.findAllDevs(allDevices, errorBuffer);
        if (r != Pcap.OK || allDevices.isEmpty()) {
            System.err.printf("Can't read list of devices, error is %s",
                    errorBuffer);
            return;
        }
        Main obj1 = new Main();
        new SpoofPage(errorBuffer);
        obj1.getInterface(allDevices);
    }
}