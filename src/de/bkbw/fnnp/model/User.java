package de.bkbw.fnnp.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private final UUID uuid;
    private String email;
    private String username;
    private UserRole userRole;
    private ArrayList<Device> devices;
    
    public User(UUID uuid, String email, String username, UserRole userRole, ArrayList<Device> devices) {
        this.uuid = uuid;
        this.email = email;
        this.username = username;
        this.userRole = userRole;
        this.devices = devices;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}