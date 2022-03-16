package de.bkbw.fnnp.model;

import java.util.UUID;

public class Device {
    private final UUID uuid;
    private String model;
    private String ip;
    private String mc;

    public Device(UUID uuid, String model, String ip, String mc) {
        this.uuid = uuid;
        this.model = model;
        this.ip = ip;
        this.mc = mc;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}