package ir.pmzhero.luckyblocks.data;

import ir.pmzhero.luckyblocks.arena.LoadedArena;
import ir.pmzhero.luckyblocks.luckyblock.texture.GlassColor;
import ir.pmzhero.luckyblocks.luckyblock.texture.LuckyBlockTexture;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class LoadData {
  public static void loadData(FileConfiguration data) {
    for (String key : data.getKeys(false)) {
      ConfigurationSection section = data.getConfigurationSection(key);
      LoadedArena arena = new LoadedArena(key);
      for (String id : section.getKeys(false)) {
        String[] split = section.getString(id).split(",");
        arena.addGenerator(id, split[4], split[0], split[1], split[2], split[3]);
      } 
    } 
  }
  
  public static void loadTextures(FileConfiguration data) {
    for (String key : data.getKeys(false)) {
      ConfigurationSection section = data.getConfigurationSection(key);
      LuckyBlockTexture.addTexture(
          section.getString("texture"), 
          GlassColor.valueOf(section.getString("glass")), 
          section.getString("item-name"), 
          section.getStringList("item-lore"));
    } 
  }
  
  public static Language loadLang(FileConfiguration data) {
    return new Language(
        data.getString("received-item"), 
        data.getString("used-inventory-copier.player"), 
        data.getString("used-inventory-copier.target"), 
        data.getString("used-lightning-scroll.player"), 
        data.getString("used-lightning-scroll.target"), 
        data.getString("used-player-launcher.type-name-to-launch"), 
        data.getString("used-player-launcher.player"), 
        data.getString("used-player-launcher.target"), 
        data.getString("rainbow-wool.name"), 
        data.getStringList("rainbow-wool.lore"), 
        data.getString("inventory-copier.name"), 
        data.getStringList("inventory-copier.lore"), 
        data.getString("player-launcher.name"), 
        data.getStringList("player-launcher.lore"), 
        data.getString("lightning-scroll.name"), 
        data.getStringList("lightning-scroll.lore"));
  }
  
  public static CachedConfig loadConfig(FileConfiguration data) {
    return new CachedConfig(
        data.getInt("generator-limit"), 
        data.getBoolean("do-not-spawn-if-team-dead"));
  }
}
