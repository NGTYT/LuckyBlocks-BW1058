package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import ir.pmzhero.luckyblocks.utils.NBTUtils;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropPlayerLauncher extends Drop {
  private static final Set<String> launchers = new HashSet<>();
  
  private final ItemStack playerLauncher;
  
  private final Language language;
  
  public DropPlayerLauncher(Language language) {
    ItemStack stack = new ItemStack(Material.PAPER);
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(language.getPlayerLauncherName());
    meta.setLore(language.getPlayerLauncherLore());
    stack.setItemMeta(meta);
    this.playerLauncher = NBTUtils.applyNBTTags(stack, "playerLauncher");
    this.language = language;
  }
  
  public static void addLauncher(Player launcher) {
    launchers.add(launcher.getName());
  }
  
  public static void removeLauncher(Player launcher) {
    launchers.remove(launcher.getName());
  }
  
  public static boolean isLauncher(Player player) {
    return launchers.contains(player.getName());
  }
  
  public void drop(Location location, Player player) {
    location.getWorld().dropItem(location, this.playerLauncher);
    this.language.sendReceivedItemMessage(player, this.language.getPlayerLauncherName(), 1);
  }
}
