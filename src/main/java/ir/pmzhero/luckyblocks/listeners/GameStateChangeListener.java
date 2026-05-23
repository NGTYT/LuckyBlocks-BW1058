package ir.pmzhero.luckyblocks.listeners;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import ir.pmzhero.luckyblocks.arena.LoadedArena;
import ir.pmzhero.luckyblocks.arena.LuckyBlockArena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStateChangeListener implements Listener {
  @EventHandler
  public void onGameStart(GameStateChangeEvent event) {
    IArena arena = event.getArena();
    String arenaName = arena.getArenaName();
    GameState state = event.getNewState();
    if (state == GameState.playing) {
      LoadedArena loadedArena = LoadedArena.byName(arenaName);
      if (loadedArena != null)
        LuckyBlockArena.start(loadedArena, arena); 
    } else if (state == GameState.restarting) {
      LuckyBlockArena.stop(arenaName);
    } 
  }
}
