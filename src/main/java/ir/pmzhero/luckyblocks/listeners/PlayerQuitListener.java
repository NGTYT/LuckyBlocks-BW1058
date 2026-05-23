package ir.pmzhero.luckyblocks.listeners;

import ir.pmzhero.luckyblocks.drops.DropPlayerLauncher;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    DropPlayerLauncher.removeLauncher(event.getPlayer());
  }
}
