package ir.pmzhero.luckyblocks.listeners;

import ir.pmzhero.luckyblocks.drops.DropRainbowWool;
import ir.pmzhero.luckyblocks.luckyblock.LuckyBlock;
import ir.pmzhero.luckyblocks.luckyblock.texture.LuckyBlockTexture;
import ir.pmzhero.luckyblocks.utils.NBTUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  public void onBlockPlace(BlockPlaceEvent event) {
    ItemStack itemInHand = event.getPlayer().getItemInHand();
    Block block = event.getBlock();
    if (block.getType() == Material.SKULL && LuckyBlockTexture.isLuckyBlockItem(itemInHand)) {
      LuckyBlock.createLuckyBlock(block, itemInHand);
    } else if (NBTUtils.hasNBTTag(itemInHand, "rainbowWool")) {
      block.setData(DropRainbowWool.randomWool());
    } 
  }
}
