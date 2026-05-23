package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import lombok.Generated;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropBridgeEgg extends Drop {
  private final ItemStack drop;
  
  private final Language language;
  
  @Generated
  public DropBridgeEgg(ItemStack drop, Language language) {
    this.drop = drop;
    this.language = language;
  }
  
  public DropBridgeEgg(Language language) {
    ItemStack stack = new ItemStack(Material.EGG);
    ItemMeta meta = stack.getItemMeta();
    if (meta != null) {
      meta.setDisplayName(ChatColor.AQUA + "Bridge Egg");
      stack.setItemMeta(meta);
    } 
    this.drop = stack;
    this.language = language;
  }
  
  public void drop(Location location, Player player) {
    ItemStack clone = this.drop.clone();
    int amount = getRandom().nextInt(4) + 2;
    clone.setAmount(amount);
    location.getWorld().dropItem(location, clone);
    this.language.sendReceivedItemMessage(
        player, 
        ChatColor.AQUA + "Bridge Egg", 
        amount);
  }
}
