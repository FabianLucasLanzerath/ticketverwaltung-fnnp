package de.bkbw.fnnp.model;

import java.util.ArrayList;
import java.util.UUID;

public class Team {
	private final UUID uuid;
	private String name;
	private ArrayList<User> teamMembers;

    public Team(UUID uuid, String name, ArrayList<User> teamMembers) {
        this.uuid = uuid;
        this.name = name;
        this.teamMembers = teamMembers;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}