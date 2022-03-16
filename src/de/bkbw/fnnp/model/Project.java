package de.bkbw.fnnp.model;

import java.util.ArrayList;
import java.util.UUID;

public class Project {
  private final UUID uuid;
  private String name;
  private ArrayList<Tag> tags;

  public Project(UUID uuid, String name, ArrayList<Tag> tags) {
      this.uuid = uuid;
      this.name = name;
      this.tags = tags;
  }

  public UUID getUUID() {
      return this.uuid;
  }
}