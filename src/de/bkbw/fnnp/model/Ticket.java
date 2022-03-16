package de.bkbw.fnnp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Ticket {
  public final UUID uuid;
  public String title;
  public String content;
  public User creator;
  public LocalDateTime creationDate;
  public User editor;
  public LocalDateTime timeToFinish;
  public ArrayList<Comment> comments;

  public Ticket(UUID uuid, String title, String content, User creator, LocalDateTime creationDate, User editor, LocalDateTime timeToFinish,  ArrayList<Comment> comments) {
      this.uuid = uuid;
      this.title = title;
      this.content = content;
      this.creator = creator;
      this.creationDate = creationDate;
      this.editor = editor;
      this.timeToFinish = timeToFinish;
      this.comments = comments;
  }

  public UUID getUUID() {
      return this.uuid;
  }
}