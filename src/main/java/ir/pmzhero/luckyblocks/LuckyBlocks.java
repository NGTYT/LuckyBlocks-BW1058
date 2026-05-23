package ir.pmzhero.luckyblocks;

import ir.pmzhero.luckyblocks.command.MainCommand;
import ir.pmzhero.luckyblocks.data.CachedConfig;
import ir.pmzhero.luckyblocks.data.Language;
import ir.pmzhero.luckyblocks.data.LoadData;
import ir.pmzhero.luckyblocks.data.YmlFile;
import ir.pmzhero.luckyblocks.drops.Drop;
import ir.pmzhero.luckyblocks.drops.DropBridgeEgg;
import ir.pmzhero.luckyblocks.drops.DropFireball;
import ir.pmzhero.luckyblocks.drops.DropIceBridge;
import ir.pmzhero.luckyblocks.drops.DropInventoryCopier;
import ir.pmzhero.luckyblocks.drops.DropLightningScroll;
import ir.pmzhero.luckyblocks.drops.DropPlayerLauncher;
import ir.pmzhero.luckyblocks.drops.DropRainbowWool;
import ir.pmzhero.luckyblocks.drops.DropSleepingDust;
import ir.pmzhero.luckyblocks.drops.DropTnt;
import ir.pmzhero.luckyblocks.drops.DropWool;
import ir.pmzhero.luckyblocks.listeners.AsyncPlayerChatListener;
import ir.pmzhero.luckyblocks.listeners.BlockBreakListener;
import ir.pmzhero.luckyblocks.listeners.BlockPlaceListener;
import ir.pmzhero.luckyblocks.listeners.GameStateChangeListener;
import ir.pmzhero.luckyblocks.listeners.PlayerInteractListener;
import ir.pmzhero.luckyblocks.listeners.PlayerItemPickupListener;
import ir.pmzhero.luckyblocks.listeners.PlayerQuitListener;
import ir.pmzhero.luckyblocks.listeners.SleepDustListener;
import lombok.Generated;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuckyBlocks extends JavaPlugin {
  private static LuckyBlocks instance;
  
  private YmlFile dataFile;
  
  private YmlFile texturesFile;
  
  private YmlFile langFile;
  
  private CachedConfig cachedConfig;
  
  private Language language;
  
  @Generated
  public YmlFile getDataFile() {
    return this.dataFile;
  }
  
  @Generated
  public YmlFile getTexturesFile() {
    return this.texturesFile;
  }
  
  @Generated
  public YmlFile getLangFile() {
    return this.langFile;
  }
  
  @Generated
  public CachedConfig getCachedConfig() {
    return this.cachedConfig;
  }
  
  @Generated
  public Language getLanguage() {
    return this.language;
  }
  
  @Generated
  public static LuckyBlocks getInstance() {
    return instance;
  }
  
  public void onEnable() {

	    instance = this;

	    // ===== STARTUP BANNER =====
	    getLogger().info(" ");
	    getLogger().info("=====================================");
	    getLogger().info("   LuckyBlocks Successfully Enabled  ");
	    getLogger().info("=====================================");
	    getLogger().info(" Version : 2.0");
	    getLogger().info(" Author  : PMZHero,NGT_YT");
	    getLogger().info(" Status  : Running Smoothly ✔");
	    getLogger().info("=====================================");
	    getLogger().info(" ");

	    saveDefaultConfig();

	    this.dataFile = new YmlFile((Plugin)this, "data.yml");
	    this.texturesFile = new YmlFile((Plugin)this, "textures.yml");
	    this.langFile = new YmlFile((Plugin)this, "lang.yml");

	    LoadData.loadData(this.dataFile.getConfig());
	    LoadData.loadTextures(this.texturesFile.getConfig());
	    this.cachedConfig = LoadData.loadConfig(getConfig());
	    this.language = LoadData.loadLang(this.langFile.getConfig());

	    getCommand("luckyblock").setExecutor((CommandExecutor)new MainCommand());

	    Drop.registerDrop((Drop)new DropFireball(this.language));
	    Drop.registerDrop((Drop)new DropTnt(this.language));
	    Drop.registerDrop((Drop)new DropWool(this.language));
	    Drop.registerDrop((Drop)new DropInventoryCopier(this.language));
	    Drop.registerDrop((Drop)new DropLightningScroll(this.language));
	    Drop.registerDrop((Drop)new DropPlayerLauncher(this.language));
	    Drop.registerDrop((Drop)new DropRainbowWool(this.language));
	    Drop.registerDrop((Drop)new DropBridgeEgg(this.language));
	    Drop.registerDrop((Drop)new DropIceBridge(this.language));
	    Drop.registerDrop((Drop)new DropSleepingDust(this.language));

	    PluginManager manager = getServer().getPluginManager();

	    manager.registerEvents(new BlockPlaceListener(), this);
	    manager.registerEvents(new BlockBreakListener(), this);
	    manager.registerEvents(new GameStateChangeListener(), this);
	    manager.registerEvents(new PlayerItemPickupListener(), this);
	    manager.registerEvents(new PlayerQuitListener(), this);
	    manager.registerEvents(new PlayerInteractListener(this.language), this);
	    manager.registerEvents(new AsyncPlayerChatListener(this.language), this);
	    manager.registerEvents(new SleepDustListener(), this);
	}
  
  public void onDisable() {}
}
