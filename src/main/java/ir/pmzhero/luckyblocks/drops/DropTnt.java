package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import lombok.Generated;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropTnt extends Drop {
  private final ItemStack drop;
  
  private final Language language;
  
  @Generated
  public DropTnt(Language language) {
    this.drop = new ItemStack(Material.TNT);
    this.language = language;
  }
  
  public void drop(Location location, Player player) {
    ItemStack clone = this.drop.clone();
    int amount = getRandom().nextInt(9) + 1;
    clone.setAmount(amount);
    location.getWorld().dropItem(location, clone);
    this.language.sendReceivedItemMessage(player, ChatColor.RED + "TNT", amount);
  }
}
