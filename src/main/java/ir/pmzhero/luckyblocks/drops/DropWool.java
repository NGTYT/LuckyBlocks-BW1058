package ir.pmzhero.luckyblocks.drops;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.arena.team.TeamColor;
import com.andrei1058.bedwars.arena.Arena;
import ir.pmzhero.luckyblocks.data.Language;
import lombok.Generated;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropWool extends Drop {
  private final Language language;
  
  @Generated
  public DropWool(Language language) {
    this.language = language;
  }
  
  public void drop(Location location, Player player) {
    IArena arena = Arena.getArenaByPlayer(player);
    if (arena == null)
      return; 
    ITeam team = arena.getTeam(player);
    TeamColor color = team.getColor();
    byte colorByte = color.itemByte();
    ItemStack wool = new ItemStack(Material.WOOL);
    wool.setDurability(colorByte);
    ItemMeta meta = wool.getItemMeta();
    String name = String.valueOf(TeamColor.enName(colorByte)) + " Wool";
    meta.setDisplayName(name);
    wool.setItemMeta(meta);
    wool.setAmount(64);
    location.getWorld().dropItem(location, wool);
    this.language.sendReceivedItemMessage(player, name, 64);
  }
}
