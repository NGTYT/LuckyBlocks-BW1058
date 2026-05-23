package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import lombok.Generated;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropFireball extends Drop {
  private final ItemStack drop;
  
  private final Language language;
  
  @Generated
  public DropFireball(Language language) {
    this.drop = new ItemStack(Material.FIREBALL);
    this.language = language;
  }
  
  public void drop(Location location, Player player) {
    ItemStack clone = this.drop.clone();
    int amount = getRandom().nextInt(9) + 1;
    clone.setAmount(amount);
    location.getWorld().dropItemNaturally(location, clone);
    this.language.sendReceivedItemMessage(player, "Fireball", amount);
  }
}
