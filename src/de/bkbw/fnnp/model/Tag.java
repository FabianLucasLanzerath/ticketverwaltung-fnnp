package de.bkbw.fnnp.model;

import java.util.ArrayList;
import java.util.UUID;

public class Tag {
	private final UUID uuid;
	private String name;
	private ArrayList<Ticket> tickets;
	
	public Tag(UUID uuid, String name, ArrayList<Ticket> tickets) {
		this.uuid = uuid;
		this.name = name;
		this.tickets = tickets;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
}