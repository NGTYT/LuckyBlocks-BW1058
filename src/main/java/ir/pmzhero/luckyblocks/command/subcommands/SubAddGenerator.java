package ir.pmzhero.luckyblocks.command.subcommands;

import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.data.YmlFile;
import ir.pmzhero.luckyblocks.utils.SetupUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SubAddGenerator extends SubCommand {
  public void execute(Player player, String[] args) {
    if (args.length != 4) {
      player.sendMessage(ChatColor.RED + "/luckyblock addgenerator <arena-name> <team-name> <cooldown>");
      return;
    } 
    if (!StringUtils.isNumeric(args[3])) {
      player.sendMessage(ChatColor.RED + "Please provide a number (seconds) at arg 3");
      return;
    } 
    Location location = player.getLocation();
    YmlFile file = LuckyBlocks.getInstance().getDataFile();
    FileConfiguration data = file.getConfig();
    String id = SetupUtils.addGenerator(data, args[1], location, args[2], args[3]);
    file.saveConfig();
    player.sendMessage(ChatColor.GREEN + "Created Lucky Block Generator " + ChatColor.GRAY + "[" + args[1] + "-" + id + "]");
  }
}
