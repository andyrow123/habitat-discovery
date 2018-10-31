package my.habitat.discovery.model;

import java.util.List;

public abstract class Manufacturer {
    public abstract List<String> macAddresses();

    public abstract void createClient(String brokerAddress, String clientId);
}
