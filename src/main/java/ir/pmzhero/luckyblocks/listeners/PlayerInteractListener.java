package ir.pmzhero.luckyblocks.listeners;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.arena.Arena;
import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.data.Language;
import ir.pmzhero.luckyblocks.drops.DropPlayerLauncher;
import ir.pmzhero.luckyblocks.utils.NBTUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Generated;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {
  private final Random random;
  
  private final Language language;
  
  @Generated
  public PlayerInteractListener(Language language) {
    this.random = new Random();
    this.language = language;
  }
  
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    Action action = event.getAction();
    if (action != Action.RIGHT_CLICK_BLOCK && action != Action.RIGHT_CLICK_AIR)
      return; 
    ItemStack stack = event.getItem();
    Player player = event.getPlayer();
    if (stack == null)
      return; 
    IArena arena = Arena.getArenaByPlayer(player);
    if (arena == null)
      return; 
    if (NBTUtils.hasNBTTag(stack, "inventoryCopier")) {
      List<Player> players = new ArrayList<>(arena.getPlayers());
      players.remove(player);
      if (players.isEmpty()) {
        player.sendMessage(ChatColor.RED + "Cannot use this item if there is no other player!");
        return;
      } 
      Player target = players.get(this.random.nextInt(players.size()));
      PlayerInventory playerInventory1 = player.getInventory();
      PlayerInventory playerInventory2 = target.getInventory();
      playerInventory1.clear();
      ItemStack[] items = playerInventory2.getContents();
      for (int i = 0; i < items.length; i++)
        playerInventory1.setItem(i, items[i]); 
      this.language.sendInventoryCopierPlayerMessage(player, target);
      this.language.sendInventoryCopierTargetMessage(target, player);
      consumeItem(player, stack);
    } else if (NBTUtils.hasNBTTag(stack, "lightningScroll")) {
      List<Player> players = new ArrayList<>(arena.getPlayers());
      players.remove(player);
      if (players.isEmpty()) {
        player.sendMessage(ChatColor.RED + "Cannot use this item if there is no other player!");
        return;
      } 
      Player target = players.get(this.random.nextInt(players.size()));
      Location location = target.getLocation();
      if (location.getWorld() != null)
        location.getWorld().strikeLightning(location); 
      this.language.sendLightningScrollPlayerMessage(player, target);
      this.language.sendLightningScrollTargetMessage(target, player);
      consumeItem(player, stack);
    } else if (NBTUtils.hasNBTTag(stack, "playerLauncher")) {
      DropPlayerLauncher.addLauncher(player);
      consumeItem(player, stack);
      player.sendMessage(this.language.getTypeNameToLaunchLang());
    } else if (NBTUtils.hasNBTTag(stack, "iceBridge")) {
      startIceBridge(player);
      consumeItem(player, stack);
    } 
  }
  
  private void startIceBridge(Player player) {
    final List<Location> blocks = new ArrayList<>();
    Location start = player.getLocation().clone();
    start.setY(start.getY() - 1.0D);
    Vector dir = start.getDirection().normalize();
    for (int i = 1; i <= 10; i++) {
      Location place = start.clone().add(dir.clone().multiply(i));
      Block block = place.getBlock();
      if (block.getType() == Material.AIR) {
        block.setType(Material.ICE);
        blocks.add(block.getLocation());
      } 
    } 
    (new BukkitRunnable() {
        int ticks = 0;
        
        public void run() {
          this.ticks += 20;
          if (this.ticks >= 1200) {
            Random r = new Random();
            for (Location loc : blocks) {
              if (r.nextBoolean() && 
                loc.getBlock().getType() == Material.ICE)
                loc.getBlock().setType(Material.AIR); 
            } 
            cancel();
          } 
        }
      }).runTaskTimer((Plugin)LuckyBlocks.getInstance(), 20L, 20L);
  }
  
  private void consumeItem(Player player, ItemStack stack) {
    if (stack.getAmount() > 1) {
      stack.setAmount(stack.getAmount() - 1);
    } else {
      player.getInventory().removeItem(new ItemStack[] { stack });
    } 
  }
}
