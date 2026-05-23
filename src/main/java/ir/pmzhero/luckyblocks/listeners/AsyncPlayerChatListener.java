package ir.pmzhero.luckyblocks.listeners;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.arena.Arena;
import ir.pmzhero.luckyblocks.data.Language;
import ir.pmzhero.luckyblocks.drops.DropPlayerLauncher;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {
  private final Language language;
  
  @Generated
  public AsyncPlayerChatListener(Language language) {
    this.language = language;
  }
  
  @EventHandler
  public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    String message = event.getMessage();
    IArena arena = Arena.getArenaByPlayer(player);
    if (arena == null)
      return; 
    if (DropPlayerLauncher.isLauncher(player)) {
      Player target = Bukkit.getPlayerExact(message);
      if (target == null) {
        player.sendMessage(this.language.getTypeNameToLaunchLang());
      } else {
        target.setVelocity(target.getLocation().getDirection().multiply(2.0D).setY(1.3D));
        this.language.sendPlayerLauncherPlayerMessage(player, target);
        this.language.sendPlayerLauncherTargetMessage(target, player);
        DropPlayerLauncher.removeLauncher(player);
        event.setCancelled(true);
      } 
    } 
  }
}
