package my.habitat.discovery.model;

import java.util.List;

public abstract class Manufacturer {
    public abstract List<String> macAddresses();

    public abstract void search(String brokerAddress, String clientId);
}
