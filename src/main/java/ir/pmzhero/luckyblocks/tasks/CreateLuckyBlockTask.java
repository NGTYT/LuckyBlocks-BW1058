package ir.pmzhero.luckyblocks.tasks;

import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.luckyblock.LuckyBlock;
import ir.pmzhero.luckyblocks.luckyblock.LuckyBlockHead;
import ir.pmzhero.luckyblocks.luckyblock.texture.LuckyBlockTexture;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CreateLuckyBlockTask extends BukkitRunnable {
  private final Block block;
  
  private final LuckyBlockTexture texture;
  
  private final LuckyBlockHead head;
  
  @Generated
  public CreateLuckyBlockTask(Block block, LuckyBlockTexture texture, LuckyBlockHead head) {
    this.block = block;
    this.texture = texture;
    this.head = head;
  }
  
  public static void create(LuckyBlock luckyBlock) {
    (new CreateLuckyBlockTask(luckyBlock.getBlock(), luckyBlock.getTexture(), luckyBlock.getHead())).runTaskLater((Plugin)LuckyBlocks.getInstance(), 1L);
  }
  
  public void run() {
    if (this.block.getType() == Material.AIR)
      return; 
    this.block.setType(Material.STAINED_GLASS);
    this.block.setData((byte)this.texture.getGlassValue());
    for (Player player : Bukkit.getOnlinePlayers())
      this.head.addPlayer(player); 
  }
}
