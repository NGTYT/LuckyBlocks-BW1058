package ir.pmzhero.luckyblocks.data;

import lombok.Generated;

public class CachedConfig {
  private final int generatorLimit;
  
  private final boolean doNotSpawnIfTeamDead;
  
  @Generated
  public CachedConfig(int generatorLimit, boolean doNotSpawnIfTeamDead) {
    this.generatorLimit = generatorLimit;
    this.doNotSpawnIfTeamDead = doNotSpawnIfTeamDead;
  }
  
  @Generated
  public int getGeneratorLimit() {
    return this.generatorLimit;
  }
  
  @Generated
  public boolean isDoNotSpawnIfTeamDead() {
    return this.doNotSpawnIfTeamDead;
  }
}
