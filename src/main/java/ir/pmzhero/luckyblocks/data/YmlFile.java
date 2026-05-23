package ir.pmzhero.luckyblocks.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public final class YmlFile {
  private final Plugin plugin;
  
  private final String fileName;
  
  private FileConfiguration dataConfig = null;
  
  private File configFile = null;
  
  public YmlFile(Plugin plugin, String fileName) {
    this.plugin = plugin;
    this.fileName = fileName;
    saveDefaultConfig();
  }
  
  public void reloadConfig() {
    if (this.configFile == null)
      this.configFile = new File(this.plugin.getDataFolder(), this.fileName); 
    this.dataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configFile);
    InputStream defaultStream = this.plugin.getResource(this.fileName);
    if (defaultStream != null) {
      YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
      this.dataConfig.setDefaults((Configuration)defaultConfig);
    } 
  }
  
  public FileConfiguration getConfig() {
    if (this.dataConfig == null)
      reloadConfig(); 
    return this.dataConfig;
  }
  
  public void saveConfig() {
    try {
      if (this.dataConfig == null || this.configFile == null)
        return; 
      getConfig().save(this.configFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void saveDefaultConfig() {
    if (this.configFile == null)
      this.configFile = new File(this.plugin.getDataFolder(), this.fileName); 
    if (!this.configFile.exists())
      this.plugin.saveResource(this.fileName, false); 
  }
}
