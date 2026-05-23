package ir.pmzhero.luckyblocks.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class ColorUtils {
  public static String colorize(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }
  
  public static List<String> colorizeList(List<String> list) {
    List<String> coloredList = new ArrayList<>();
    for (String lore : list)
      coloredList.add(colorize(lore)); 
    return coloredList;
  }
}
