package ir.pmzhero.luckyblocks.listeners;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.arena.Arena;
import ir.pmzhero.luckyblocks.arena.LuckyBlockArena;
import ir.pmzhero.luckyblocks.generator.LuckyBlockGenerator;
import ir.pmzhero.luckyblocks.luckyblock.texture.LuckyBlockTexture;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemPickupListener implements Listener {
  @EventHandler
  public void onItemPickup(PlayerPickupItemEvent event) {
    Item item = event.getItem();
    ItemStack stack = item.getItemStack();
    Location location = event.getItem().getLocation();
    Player player = event.getPlayer();
    int pickedUp = stack.getAmount();
    World world = location.getWorld();
    double x = location.getX();
    double y = location.getY();
    double z = location.getZ();
    IArena arena = Arena.getArenaByPlayer(player);
    if (arena == null)
      return; 
    if (!item.getName().equals("custom"))
      return; 
    item.setCustomName(null);
    LuckyBlockArena luckyBlockArena = LuckyBlockArena.byArenaName(arena.getArenaName());
    if (luckyBlockArena == null)
      return; 
    Set<LuckyBlockGenerator> generators = luckyBlockArena.getGenerators();
    for (LuckyBlockGenerator generator : generators) {
      if (world == generator.getBukkitWorld() && 
        x == generator.getX() && 
        y == generator.getY() && 
        z == generator.getZ() && 
        LuckyBlockTexture.isLuckyBlockItem(stack)) {
        generator.decreaseCount(pickedUp);
        break;
      } 
    } 
  }
}
