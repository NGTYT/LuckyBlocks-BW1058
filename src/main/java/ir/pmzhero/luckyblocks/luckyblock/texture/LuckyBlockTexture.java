package ir.pmzhero.luckyblocks.luckyblock.texture;

import ir.pmzhero.luckyblocks.utils.ColorUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class LuckyBlockTexture {

  private static final List<LuckyBlockTexture> textures = new ArrayList<>();
  private static final Random random = new Random();

  private final ItemStack bukkitItem;
  private final int glassValue;

  public LuckyBlockTexture(String value, int glassValue, String itemName, List<String> itemLore) {

    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

    SkullMeta meta = (SkullMeta) skull.getItemMeta();

    // Create fake UUID for texture
    UUID hashAsId = new UUID(value.hashCode(), value.hashCode());

    // Apply custom texture using unsafe (1.8 method)
    ItemStack item = Bukkit.getUnsafe().modifyItemStack(
            skull,
            "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
    );

    meta = (SkullMeta) item.getItemMeta();
    meta.setDisplayName(ColorUtils.colorize(itemName));

    List<String> coloredLore = new ArrayList<>();
    for (String lore : itemLore) {
      coloredLore.add(ColorUtils.colorize(lore));
    }

    meta.setLore(coloredLore);
    item.setItemMeta(meta);

    this.bukkitItem = item;
    this.glassValue = glassValue;
  }

  public ItemStack getBukkitItem() {
    return bukkitItem;
  }

  public int getGlassValue() {
    return glassValue;
  }

  public static void addTexture(String value, GlassColor color, String itemName, List<String> itemLore) {
    textures.add(new LuckyBlockTexture(value, color.getData(), itemName, itemLore));
  }

  public static LuckyBlockTexture byBukkitItem(ItemStack stack) {
    if (stack == null) return null;

    ItemStack clone = stack.clone();
    clone.setAmount(1);

    for (LuckyBlockTexture texture : textures) {
      if (texture.getBukkitItem().isSimilar(clone)) {
        return texture;
      }
    }
    return null;
  }

  public static boolean isLuckyBlockItem(ItemStack stack) {
    return byBukkitItem(stack) != null;
  }

  public static LuckyBlockTexture randomTexture() {
    return textures.get(random.nextInt(textures.size()));
  }
}