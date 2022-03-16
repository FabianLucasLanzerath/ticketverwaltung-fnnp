package de.bkbw.fnnp.model;

import java.time.LocalDateTime;

public class Session  {
    private User user;
    private LocalDateTime ttl;

    public Session(User user, LocalDateTime ttl) {
        this.user = user;
        this.ttl = ttl;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTtl() {
        return ttl;
    }
}