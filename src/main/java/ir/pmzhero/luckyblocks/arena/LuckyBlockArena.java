package ir.pmzhero.luckyblocks.arena;

import com.andrei1058.bedwars.api.arena.IArena;
import ir.pmzhero.luckyblocks.generator.LoadedGenerator;
import ir.pmzhero.luckyblocks.generator.LuckyBlockGenerator;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public class LuckyBlockArena {
  @Generated
  public Set<LuckyBlockGenerator> getGenerators() {
    return this.generators;
  }
  
  @Generated
  public String getArenaName() {
    return this.arenaName;
  }
  
  private static final Set<LuckyBlockArena> arenas = new HashSet<>();
  
  private final Set<LuckyBlockGenerator> generators = new HashSet<>();
  
  private String arenaName;
  
  public LuckyBlockArena(LoadedArena loadedArena, IArena arena) {
    this.arenaName = loadedArena.getArenaName();
    for (LoadedGenerator generator : loadedArena.getGenerators())
      this.generators.add(new LuckyBlockGenerator(generator, arena, this)); 
  }
  
  public static void start(LoadedArena loadedArena, IArena arena) {
    arenas.add(new LuckyBlockArena(loadedArena, arena));
  }
  
  public static void stop(String arenaName) {
    LuckyBlockArena arena = byArenaName(arenaName);
    if (arena != null) {
      arena.destroy();
      arenas.remove(arena);
    } 
  }
  
  public static LuckyBlockArena byArenaName(String name) {
    for (LuckyBlockArena arena : arenas) {
      if (arena.getArenaName().equals(name))
        return arena; 
    } 
    return null;
  }
  
  public void removeGenerator(LuckyBlockGenerator generator) {
    this.generators.remove(generator);
  }
  
  public void destroy() {
    this.arenaName = null;
    for (LuckyBlockGenerator generator : this.generators)
      generator.destroy(); 
    this.generators.clear();
  }
}
