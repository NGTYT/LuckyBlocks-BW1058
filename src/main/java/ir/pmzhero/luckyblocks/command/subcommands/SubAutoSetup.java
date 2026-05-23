package ir.pmzhero.luckyblocks.command.subcommands;

import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.data.YmlFile;
import ir.pmzhero.luckyblocks.utils.SetupUtils;
import java.io.File;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SubAutoSetup extends SubCommand {
  private final Plugin bedWarsPlugin = Bukkit.getPluginManager().getPlugin("BedWars1058");
  
  public void execute(Player player, String[] args) {
    if (args.length != 3) {
      player.sendMessage(ChatColor.RED + "/luckyblock autosetup <arena> <cooldown>");
      return;
    } 
    File arenaFile = new File(this.bedWarsPlugin.getDataFolder(), "Arenas" + File.separator + args[1] + ".yml");
    YmlFile file = LuckyBlocks.getInstance().getDataFile();
    FileConfiguration data = file.getConfig();
    if (arenaFile.exists()) {
      YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
      ConfigurationSection teams = yamlConfiguration.getConfigurationSection("Team");
      Set<String> keys = teams.getKeys(false);
      for (String key : keys) {
        String location = teams.getStringList(String.valueOf(key) + ".Iron").iterator().next();
        SetupUtils.addGenerator(data, args[1], SetupUtils.stringToLocation(args[1], location), key, args[2]);
      } 
      file.saveConfig();
      player.sendMessage(ChatColor.GREEN + "Auto-Setup completed for " + ChatColor.AQUA + args[1]);
    } else {
      player.sendMessage(ChatColor.RED + "Arena not found! use /bw arenaList");
    } 
  }
}
