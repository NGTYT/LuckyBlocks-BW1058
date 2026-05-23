package ir.pmzhero.luckyblocks.drops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Drop {
  private static final Random random = new Random();
  
  private static final List<Drop> drops = new ArrayList<>();
  
  public static Drop randomDrop() {
    return drops.get(random.nextInt(drops.size()));
  }
  
  public static void registerDrop(Drop drop) {
    drops.add(drop);
  }
  
  public abstract void drop(Location paramLocation, Player paramPlayer);
  
  public Random getRandom() {
    return random;
  }
}
