package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import ir.pmzhero.luckyblocks.utils.NBTUtils;
import lombok.Generated;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropInventoryCopier extends Drop {
  private final ItemStack inventoryCopier;
  
  private final Language language;
  
  @Generated
  public DropInventoryCopier(ItemStack inventoryCopier, Language language) {
    this.inventoryCopier = inventoryCopier;
    this.language = language;
  }
  
  public DropInventoryCopier(Language language) {
    ItemStack stack = new ItemStack(Material.PAPER);
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(language.getInventoryCopierName());
    meta.setLore(language.getInventoryCopierLore());
    stack.setItemMeta(meta);
    this.inventoryCopier = NBTUtils.applyNBTTags(stack, "inventoryCopier");
    this.language = language;
  }
  
  public void drop(Location location, Player player) {
    location.getWorld().dropItem(location, this.inventoryCopier);
    this.language.sendReceivedItemMessage(player, this.language.getInventoryCopierName(), 1);
  }
}
