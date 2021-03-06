package de.bkbw.fnnp.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final UUID uuid;
    private User user;
    private LocalDateTime creationDate;

    public Comment(UUID uuid, User user, LocalDateTime creationDate) {
		this.uuid = uuid;
		this.user = user;
		this.creationDate = creationDate;
    }	

	public UUID getUUID() {
		return this.uuid;
	}
}