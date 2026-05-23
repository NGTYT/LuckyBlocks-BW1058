package ir.pmzhero.luckyblocks.data;

import ir.pmzhero.luckyblocks.utils.ColorUtils;
import java.util.List;
import lombok.Generated;
import org.bukkit.entity.Player;

public class Language {
  private final String receivedItemLang;
  
  private final String usedInventoryCopierPlayerLang;
  
  private final String usedInventoryCopierTargetLang;
  
  private final String usedLightningScrollPlayerLang;
  
  private final String usedLightningScrollTargetLang;
  
  private final String typeNameToLaunchLang;
  
  private final String usedPlayerLauncherPlayerLang;
  
  private final String usedPlayerLauncherTargetLang;
  
  private final String inventoryCopierName;
  
  private final List<String> inventoryCopierLore;
  
  private final String playerLauncherName;
  
  private final List<String> playerLauncherLore;
  
  private final String lightningScrollName;
  
  private final List<String> lightningScrollLore;
  
  private final String rainbowWoolName;
  
  private final List<String> rainbowWoolLore;
  
  @Generated
  public String getReceivedItemLang() {
    return this.receivedItemLang;
  }
  
  @Generated
  public String getUsedInventoryCopierPlayerLang() {
    return this.usedInventoryCopierPlayerLang;
  }
  
  @Generated
  public String getUsedInventoryCopierTargetLang() {
    return this.usedInventoryCopierTargetLang;
  }
  
  @Generated
  public String getUsedLightningScrollPlayerLang() {
    return this.usedLightningScrollPlayerLang;
  }
  
  @Generated
  public String getUsedLightningScrollTargetLang() {
    return this.usedLightningScrollTargetLang;
  }
  
  @Generated
  public String getTypeNameToLaunchLang() {
    return this.typeNameToLaunchLang;
  }
  
  @Generated
  public String getUsedPlayerLauncherPlayerLang() {
    return this.usedPlayerLauncherPlayerLang;
  }
  
  @Generated
  public String getUsedPlayerLauncherTargetLang() {
    return this.usedPlayerLauncherTargetLang;
  }
  
  @Generated
  public String getInventoryCopierName() {
    return this.inventoryCopierName;
  }
  
  @Generated
  public List<String> getInventoryCopierLore() {
    return this.inventoryCopierLore;
  }
  
  @Generated
  public String getPlayerLauncherName() {
    return this.playerLauncherName;
  }
  
  @Generated
  public List<String> getPlayerLauncherLore() {
    return this.playerLauncherLore;
  }
  
  @Generated
  public String getLightningScrollName() {
    return this.lightningScrollName;
  }
  
  @Generated
  public List<String> getLightningScrollLore() {
    return this.lightningScrollLore;
  }
  
  @Generated
  public String getRainbowWoolName() {
    return this.rainbowWoolName;
  }
  
  @Generated
  public List<String> getRainbowWoolLore() {
    return this.rainbowWoolLore;
  }
  
  public Language(String receivedItemLang, String usedInventoryCopierPlayerLang, String usedInventoryCopierTargetLang, String usedLightningScrollPlayerLang, String usedLightningScrollTargetLang, String typeNameToLaunchLang, String usedPlayerLauncherPlayerLang, String usedPlayerLauncherTargetLang, String rainbowWoolName, List<String> rainbowWoolLore, String inventoryCopierName, List<String> inventoryCopierLore, String playerLauncherName, List<String> playerLauncherLore, String lightningScrollName, List<String> lightningScrollLore) {
    this.receivedItemLang = ColorUtils.colorize(receivedItemLang);
    this.usedInventoryCopierPlayerLang = ColorUtils.colorize(usedInventoryCopierPlayerLang);
    this.usedInventoryCopierTargetLang = ColorUtils.colorize(usedInventoryCopierTargetLang);
    this.usedLightningScrollPlayerLang = ColorUtils.colorize(usedLightningScrollPlayerLang);
    this.usedLightningScrollTargetLang = ColorUtils.colorize(usedLightningScrollTargetLang);
    this.rainbowWoolName = ColorUtils.colorize(rainbowWoolName);
    this.rainbowWoolLore = ColorUtils.colorizeList(rainbowWoolLore);
    this.typeNameToLaunchLang = ColorUtils.colorize(typeNameToLaunchLang);
    this.usedPlayerLauncherPlayerLang = ColorUtils.colorize(usedPlayerLauncherPlayerLang);
    this.usedPlayerLauncherTargetLang = ColorUtils.colorize(usedPlayerLauncherTargetLang);
    this.inventoryCopierName = ColorUtils.colorize(inventoryCopierName);
    this.inventoryCopierLore = ColorUtils.colorizeList(inventoryCopierLore);
    this.playerLauncherName = ColorUtils.colorize(playerLauncherName);
    this.playerLauncherLore = ColorUtils.colorizeList(playerLauncherLore);
    this.lightningScrollName = ColorUtils.colorize(lightningScrollName);
    this.lightningScrollLore = ColorUtils.colorizeList(lightningScrollLore);
  }
  
  public void sendReceivedItemMessage(Player player, String name, int count) {
    player.sendMessage(this.receivedItemLang
        .replace("{item}", name)
        .replace("{count}", String.valueOf(count)));
  }
  
  public void sendInventoryCopierPlayerMessage(Player player, Player target) {
    player.sendMessage(this.usedInventoryCopierPlayerLang.replace("{target}", target.getName()));
  }
  
  public void sendInventoryCopierTargetMessage(Player target, Player copier) {
    target.sendMessage(this.usedInventoryCopierTargetLang.replace("{copier}", copier.getName()));
  }
  
  public void sendLightningScrollPlayerMessage(Player player, Player target) {
    player.sendMessage(this.usedLightningScrollPlayerLang.replace("{target}", target.getName()));
  }
  
  public void sendLightningScrollTargetMessage(Player target, Player striker) {
    target.sendMessage(this.usedLightningScrollTargetLang.replace("{striker}", striker.getName()));
  }
  
  public void sendPlayerLauncherPlayerMessage(Player player, Player target) {
    player.sendMessage(this.usedPlayerLauncherPlayerLang.replace("{target}", target.getName()));
  }
  
  public void sendPlayerLauncherTargetMessage(Player player, Player launcher) {
    player.sendMessage(this.usedPlayerLauncherTargetLang.replace("{launcher}", launcher.getName()));
  }
}
