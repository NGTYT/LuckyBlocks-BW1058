package ir.pmzhero.luckyblocks.command;

import ir.pmzhero.luckyblocks.command.subcommands.SubAddGenerator;
import ir.pmzhero.luckyblocks.command.subcommands.SubAutoSetup;
import ir.pmzhero.luckyblocks.command.subcommands.SubCommand;
import ir.pmzhero.luckyblocks.command.subcommands.SubRemoveGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
  private final SubCommand subAddGenerator = (SubCommand)new SubAddGenerator();
  
  private final SubCommand subRemoveGenerator = (SubCommand)new SubRemoveGenerator();
  
  private final SubCommand subAutoSetup = (SubCommand)new SubAutoSetup();
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Cannot execute this command as console");
      return true;
    } 
    Player player = (Player)sender;
    if (args.length == 0) {
      player.sendMessage(ChatColor.RED + "/luckyblock <addgenerator/removegenerator/autosetup>");
      return true;
    } 
    String str;
    switch ((str = args[0].toLowerCase()).hashCode()) {
      case -1854912686:
        if (!str.equals("addgenerator"))
          break; 
        this.subAddGenerator.execute(player, args);
        return true;
      case 394589903:
        if (!str.equals("removegenerator"))
          break; 
        this.subRemoveGenerator.execute(player, args);
        return true;
      case 1679331886:
        if (!str.equals("autosetup"))
          break; 
        this.subAutoSetup.execute(player, args);
        return true;
    } 
    player.sendMessage(ChatColor.RED + "/luckyblock <addgenerator/removegenerator/autosetup>");
    return true;
  }
}
