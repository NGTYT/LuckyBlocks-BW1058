package ir.pmzhero.luckyblocks.arena;

import ir.pmzhero.luckyblocks.generator.LoadedGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Generated;

public class LoadedArena {
  @Generated
  public String getArenaName() {
    return this.arenaName;
  }
  
  @Generated
  public List<LoadedGenerator> getGenerators() {
    return this.generators;
  }
  
  @Generated
  public static Set<LoadedArena> getArenas() {
    return arenas;
  }
  
  private static final Set<LoadedArena> arenas = new HashSet<>();
  
  private final String arenaName;
  
  private final List<LoadedGenerator> generators = new ArrayList<>();
  
  public LoadedArena(String arenaName) {
    this.arenaName = arenaName;
    arenas.add(this);
  }
  
  public static LoadedArena byName(String name) {
    for (LoadedArena arena : arenas) {
      if (arena.getArenaName().equals(name))
        return arena; 
    } 
    return null;
  }
  
  public void addGenerator(String id, String team, String x, String y, String z, String cooldown) {
    this.generators.add(new LoadedGenerator(
          this.arenaName, 
          team, 
          Integer.parseInt(id), 
          Double.parseDouble(x), 
          Double.parseDouble(y), 
          Double.parseDouble(z), 
          Integer.parseInt(cooldown)));
  }
  
  public void addGenerator(String id, String team, double x, double y, double z, String cooldown) {
    this.generators.add(new LoadedGenerator(
          this.arenaName, team, 
          Integer.parseInt(id), x, y, z, Integer.parseInt(cooldown)));
  }
  
  public void removeGenerator(LoadedGenerator generator) {
    this.generators.remove(generator);
  }
  
  public LoadedGenerator getGeneratorById(int id) {
    for (LoadedGenerator generator : this.generators) {
      if (generator.getId() == id)
        return generator; 
    } 
    return null;
  }
}
