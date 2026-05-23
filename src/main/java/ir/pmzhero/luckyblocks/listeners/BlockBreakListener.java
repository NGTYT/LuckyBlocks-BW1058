package ir.pmzhero.luckyblocks.listeners;

import ir.pmzhero.luckyblocks.luckyblock.LuckyBlock;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Block block = event.getBlock();
    if (LuckyBlock.isLuckyBlock(block))
      LuckyBlock.destroyLuckyBlock(block, event.getPlayer()); 
  }
}
