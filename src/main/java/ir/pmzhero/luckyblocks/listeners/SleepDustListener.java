package ir.pmzhero.luckyblocks.listeners;

import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.utils.NBTUtils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SleepDustListener implements Listener {

    private final Set<Location> traps = new HashSet<>();
    private final Set<UUID> sleeping = new HashSet<>();
    private final Map<Location, UUID> placedBy = new HashMap<>();

    // FIXED: now multiple armorstands (2-line hologram)
    private final Map<UUID, List<ArmorStand>> holograms = new HashMap<>();

    // PLACE
    @EventHandler
    public void onPlace(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack stack = event.getItem();
        if (stack == null) return;

        if (!NBTUtils.hasNBTTag(stack, "sleepDust")) return;

        Player player = event.getPlayer();
        Block clicked = event.getClickedBlock();
        if (clicked == null) return;

        Location place = clicked.getLocation().add(0, 1, 0);

        if (place.getBlock().getType() != Material.AIR) return;

        place.getBlock().setType(Material.CARPET);
        place.getBlock().setData((byte) 10);

        traps.add(place.getBlock().getLocation());
        placedBy.put(place.getBlock().getLocation(), player.getUniqueId());

        stack.setAmount(stack.getAmount() - 1);
    }

    // STEP ON TRAP
    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        UUID id = player.getUniqueId();

        if (sleeping.contains(id)) {
            event.setTo(event.getFrom());
            return;
        }

        Location feet = player.getLocation().getBlock().getLocation();

        if (!traps.contains(feet)) return;

        UUID owner = placedBy.get(feet);

        if (owner != null && owner.equals(id)) return;

        traps.remove(feet);
        placedBy.remove(feet);
        feet.getBlock().setType(Material.AIR);

        startSleep(player);
    }

    // WAKE UP
    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof Player)) return;

        Player target = (Player) event.getRightClicked();
        Player clicker = event.getPlayer();

        UUID id = target.getUniqueId();

        if (!sleeping.contains(id)) return;

        sleeping.remove(id);
        removeHologram(id);

        target.sendMessage(ChatColor.GREEN + "You were woken up by " + clicker.getName());
    }

    // SLEEP SYSTEM
    private void startSleep(Player player) {

        UUID id = player.getUniqueId();
        sleeping.add(id);

        createHologram(player);

        new BukkitRunnable() {

            int time = 60;

            @Override
            public void run() {

                if (!player.isOnline() || !sleeping.contains(id)) {
                    sleeping.remove(id);
                    removeHologram(id);
                    cancel();
                    return;
                }

                if (time <= 0) {
                    sleeping.remove(id);
                    removeHologram(id);
                    player.sendMessage(ChatColor.GREEN + "☀ You woke up!");
                    cancel();
                    return;
                }

                player.setVelocity(player.getVelocity().zero());
                player.setFallDistance(0);

                updateHologram(player, time);

                time--;
            }

        }.runTaskTimer(LuckyBlocks.getInstance(), 0L, 20L);
    }

    // HOLOGRAM CREATE (2 LINES + FIXED HEIGHT)
    private void createHologram(Player player) {

        Location base = player.getLocation().add(0, 2.4, 0);

        List<ArmorStand> list = new ArrayList<>();

        ArmorStand line1 = (ArmorStand) player.getWorld().spawnEntity(
                base.clone().add(0, 0.25, 0),
                EntityType.ARMOR_STAND
        );

        line1.setVisible(false);
        line1.setGravity(false);
        line1.setCustomNameVisible(true);
        line1.setSmall(true);
        line1.setMarker(true);

        ArmorStand line2 = (ArmorStand) player.getWorld().spawnEntity(
                base.clone().add(0, 0.05, 0),
                EntityType.ARMOR_STAND
        );

        line2.setVisible(false);
        line2.setGravity(false);
        line2.setCustomNameVisible(true);
        line2.setSmall(true);
        line2.setMarker(true);

        list.add(line1);
        list.add(line2);

        holograms.put(player.getUniqueId(), list);

        updateHologram(player, 60);
    }

    // HOLOGRAM UPDATE (LIVE COUNTDOWN)
    private void updateHologram(Player player, int time) {

        List<ArmorStand> list = holograms.get(player.getUniqueId());
        if (list == null) return;

        list.get(0).setCustomName(ChatColor.LIGHT_PURPLE +
                player.getName() + " is taking a nap for " + time + "s");

        list.get(1).setCustomName(ChatColor.YELLOW +
                "Right click to wake up");
    }

    // REMOVE HOLOGRAM
    private void removeHologram(UUID id) {

        List<ArmorStand> list = holograms.remove(id);

        if (list == null) return;

        for (ArmorStand stand : list) {
            if (stand != null && !stand.isDead()) {
                stand.remove();
            }
        }
    }
}