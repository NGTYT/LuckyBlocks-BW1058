package ir.pmzhero.luckyblocks.utils;

import ir.pmzhero.luckyblocks.arena.LoadedArena;
import ir.pmzhero.luckyblocks.generator.LoadedGenerator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SetupUtils {
  public static String addGenerator(FileConfiguration data, String arenaName, Location location, String team, String cooldown) {
    String id = null;
    if (data.contains(arenaName)) {
      LoadedArena arena = LoadedArena.byName(arenaName);
      if (arena != null) {
        List<LoadedGenerator> generators = arena.getGenerators();
        id = String.valueOf(((LoadedGenerator)generators.get(generators.size() - 1)).getId() + 1);
        data.set(String.valueOf(arenaName) + "." + id, String.valueOf(locationToString(location)) + "," + cooldown + "," + team);
        arena.addGenerator(id, team, location.getX(), location.getY(), location.getZ(), cooldown);
      } 
    } else {
      id = String.valueOf(0);
      data.set(String.valueOf(arenaName) + "." + id, String.valueOf(locationToString(location)) + "," + cooldown + "," + team);
      LoadedArena arena = new LoadedArena(arenaName);
      arena.addGenerator(id, team, location.getX(), location.getY(), location.getZ(), cooldown);
    } 
    return id;
  }
  
  public static String locationToString(Location location) {
    return String.valueOf(location.getX()) + "," + location.getY() + "," + location.getZ();
  }
  
  public static Location stringToLocation(String world, String string) {
    String[] split = string.split(",");
    return new Location(Bukkit.getWorld(world), Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
  }
}
