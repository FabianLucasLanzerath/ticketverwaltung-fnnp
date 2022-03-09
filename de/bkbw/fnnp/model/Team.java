package de.bkbw.fnnp.model;

import java.util.ArrayList;
import java.util.UUID;

public class Team {
  public final UUID uuid;
  public String name;
  public ArrayList<User> teamMembers;

  public Team(UUID uuid, String name, ArrayList<User> teamMembers) {
      this.uuid = uuid;
      this.name = name;
      this.teamMembers = teamMembers;
  }

  public UUID getUUID() {
      return this.uuid;
  }
}