package ir.pmzhero.luckyblocks.utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTUtils {

  // Apply custom NBT tag
  public static ItemStack applyNBTTags(ItemStack item, String tag) {

    net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

    NBTTagCompound compound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

    compound.set(tag, new NBTTagString("true"));

    nmsItem.setTag(compound);

    return CraftItemStack.asBukkitCopy(nmsItem);
  }

  // Check if item has tag
  public static boolean hasNBTTag(ItemStack item, String tag) {

    if (item == null) return false;

    net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

    if (!nmsItem.hasTag()) return false;

    NBTTagCompound compound = nmsItem.getTag();

    return compound != null && compound.hasKey(tag) && compound.getString(tag).equals("true");
  }
}