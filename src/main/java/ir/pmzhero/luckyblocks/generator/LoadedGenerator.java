package ir.pmzhero.luckyblocks.generator;

import lombok.Generated;

public class LoadedGenerator {
  private final String arenaName;
  
  private final String team;
  
  private final int id;
  
  private final double x;
  
  private final double y;
  
  private final double z;
  
  private final int cooldown;
  
  @Generated
  public LoadedGenerator(String arenaName, String team, int id, double x, double y, double z, int cooldown) {
    this.arenaName = arenaName;
    this.team = team;
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
    this.cooldown = cooldown;
  }
  
  @Generated
  public String getArenaName() {
    return this.arenaName;
  }
  
  @Generated
  public String getTeam() {
    return this.team;
  }
  
  @Generated
  public int getId() {
    return this.id;
  }
  
  @Generated
  public double getX() {
    return this.x;
  }
  
  @Generated
  public double getY() {
    return this.y;
  }
  
  @Generated
  public double getZ() {
    return this.z;
  }
  
  @Generated
  public int getCooldown() {
    return this.cooldown;
  }
}
