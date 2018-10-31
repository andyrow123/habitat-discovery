package my.habitat.discovery.model;

import java.util.Arrays;
import java.util.List;

public class Sonoff extends Manufacturer {

    @Override
    public List<String> macAddresses() {
        return Arrays.asList(
                "18-FE-34",
                "24-0A-C4",
                "24-B2-DE",
                "2C-3A-E8",
                "30-AE-A4",
                "54-5A-A6",
                "5C-CF-7F",
                "60-01-94",
                "68-C6-3A",
                "84-0D-8E",
                "84-F3-EB",
                "90-97-D5",
                "A0-20-A6",
                "A4-7B-9D",
                "AC-D0-74",
                "B4-E6-2D",
                "BC-DD-C2",
                "CC-50-E3",
                "D8-A0-1D",
                "DC-4F-22",
                "EC-FA-BC");
    }

    @Override
    public void createClient(String brokerAddress, String clientId) {

    }
}
