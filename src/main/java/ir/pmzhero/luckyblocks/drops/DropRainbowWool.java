package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import ir.pmzhero.luckyblocks.utils.NBTUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.Generated;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropRainbowWool extends Drop {
  @Generated
  public DropRainbowWool(ItemStack rainbowWool, Language language) {
    this.rainbowWool = rainbowWool;
    this.language = language;
  }
  
  private static final List<Integer> colors = Arrays.asList(new Integer[] { Integer.valueOf(5), Integer.valueOf(9), Integer.valueOf(13), Integer.valueOf(7), Integer.valueOf(6), Integer.valueOf(14), Integer.valueOf(4), Integer.valueOf(11), Integer.valueOf(8) });
  
  private static final Random random = new Random();
  
  private final ItemStack rainbowWool;
  
  private final Language language;
  
  public DropRainbowWool(Language language) {
    ItemStack stack = new ItemStack(Material.WOOL, 64);
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(language.getRainbowWoolName());
    meta.setLore(language.getRainbowWoolLore());
    stack.setItemMeta(meta);
    this.rainbowWool = NBTUtils.applyNBTTags(stack, "rainbowWool");
    this.language = language;
  }
  
  public static byte randomWool() {
    return (byte)((Integer)colors.get(random.nextInt(colors.size()))).intValue();
  }
  
  public void drop(Location location, Player player) {
    location.getWorld().dropItemNaturally(location, this.rainbowWool);
    this.language.sendReceivedItemMessage(player, this.language.getRainbowWoolName(), 64);
  }
}
