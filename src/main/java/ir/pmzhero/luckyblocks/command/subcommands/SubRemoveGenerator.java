package ir.pmzhero.luckyblocks.command.subcommands;

import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.arena.LoadedArena;
import ir.pmzhero.luckyblocks.data.YmlFile;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SubRemoveGenerator extends SubCommand {
  public void execute(Player player, String[] args) {
    if (args.length != 3) {
      player.sendMessage(ChatColor.RED + "/luckyblock removegenerator <arena-name> <id>");
      return;
    } 
    if (!StringUtils.isNumeric(args[2])) {
      player.sendMessage(ChatColor.RED + "Please provide a number (seconds) at arg 2");
      return;
    } 
    YmlFile file = LuckyBlocks.getInstance().getDataFile();
    FileConfiguration data = file.getConfig();
    int id = Integer.parseInt(args[2]);
    if (data.contains(args[1])) {
      LoadedArena arena = LoadedArena.byName(args[1]);
      if (!data.contains(String.valueOf(args[1]) + "." + id)) {
        player.sendMessage(ChatColor.RED + "A generator with this id does not exist. (look at ./plugins/LuckyBlocks/data.yml for ids)");
        return;
      } 
      if (arena != null)
        arena.removeGenerator(arena.getGeneratorById(2)); 
      data.set(String.valueOf(args[1]) + "." + id, null);
      file.saveConfig();
    } else {
      player.sendMessage(ChatColor.RED + "There is no lucky block generator in the specified arena " + ChatColor.GRAY + "(" + args[1] + ")");
      return;
    } 
    player.sendMessage(ChatColor.GREEN + "Deleted Lucky Block Generator " + ChatColor.GRAY + "[" + args[1] + "-" + id + "]");
  }
}
