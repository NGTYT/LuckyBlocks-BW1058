package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import lombok.Generated;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropIceBridge extends Drop {
  private final ItemStack drop;
  
  private final Language language;
  
  @Generated
  public DropIceBridge(ItemStack drop, Language language) {
    this.drop = drop;
    this.language = language;
  }
  
  public DropIceBridge(Language language) {
    ItemStack stack = new ItemStack(Material.ICE);
    ItemMeta meta = stack.getItemMeta();
    if (meta != null) {
      meta.setDisplayName(ChatColor.GREEN + "Ice Bridge");
      stack.setItemMeta(meta);
    } 
    this.drop = stack;
    this.language = language;
  }
  
  public void drop(Location location, Player player) {
    ItemStack clone = this.drop.clone();
    clone.setAmount(1);
    location.getWorld().dropItem(location, clone);
    this.language.sendReceivedItemMessage(
        player, 
        ChatColor.GREEN + "Ice Bridge", 
        1);
  }
}
