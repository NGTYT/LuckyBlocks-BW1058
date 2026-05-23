package ir.pmzhero.luckyblocks.luckyblock;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.arena.Arena;
import ir.pmzhero.luckyblocks.drops.Drop;
import ir.pmzhero.luckyblocks.luckyblock.texture.LuckyBlockTexture;
import ir.pmzhero.luckyblocks.tasks.CreateLuckyBlockTask;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LuckyBlock {
  @Generated
  public Block getBlock() {
    return this.block;
  }
  
  @Generated
  public LuckyBlockHead getHead() {
    return this.head;
  }
  
  @Generated
  public LuckyBlockTexture getTexture() {
    return this.texture;
  }
  
  private static final Map<Block, LuckyBlock> blocks = new HashMap<>();
  
  private final Block block;
  
  private final LuckyBlockHead head;
  
  private final LuckyBlockTexture texture;
  
  public LuckyBlock(Block block, LuckyBlockTexture texture, IArena arena) {
    this.block = block;
    this.texture = texture;
    this.head = new LuckyBlockHead(block.getLocation().add(0.5D, -1.25D, 0.5D), texture);
  }
  
  public static void createLuckyBlock(Block block, ItemStack itemInHand) {
    LuckyBlock luckyBlock = new LuckyBlock(block, LuckyBlockTexture.byBukkitItem(itemInHand), Arena.getArenaByIdentifier(block.getWorld().getName()));
    blocks.put(block, luckyBlock);
    CreateLuckyBlockTask.create(luckyBlock);
  }
  
  public static void destroyLuckyBlock(Block block, Player player) {
    ((LuckyBlock)blocks.get(block)).getHead().destroy();
    blocks.remove(block);
    Drop.randomDrop().drop(block.getLocation(), player);
  }
  
  public static boolean isLuckyBlock(Block block) {
    return blocks.containsKey(block);
  }
}
