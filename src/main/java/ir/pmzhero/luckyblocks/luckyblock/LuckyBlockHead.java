package ir.pmzhero.luckyblocks.luckyblock;

import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.luckyblock.texture.LuckyBlockTexture;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LuckyBlockHead {

    private final Location location;

    private final Set<Player> players = new HashSet<>();
    private final Set<Player> displaying = new HashSet<>();

    private final BukkitTask keepalive;

    private Packet<?> spawnPacket;
    private Packet<?> metaDataPacket;
    private Packet<?> armorPacket;
    private Packet<?> destroyPacket;

    public LuckyBlockHead(final Location location, LuckyBlockTexture skin) {

        this.location = location;

        WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();

        EntityArmorStand stand = new EntityArmorStand(
                (World) worldServer,
                location.getX(),
                location.getY(),
                location.getZ()
        );

        stand.setInvisible(true);
        stand.n(true);

        this.spawnPacket = new PacketPlayOutSpawnEntityLiving(stand);

        this.metaDataPacket = new PacketPlayOutEntityMetadata(
                stand.getId(),
                stand.getDataWatcher(),
                true
        );

        // FIXED: use Bukkit item -> NMS conversion
        this.armorPacket = new PacketPlayOutEntityEquipment(
                stand.getId(),
                4,
                CraftItemStack.asNMSCopy(skin.getBukkitItem())
        );

        this.destroyPacket = new PacketPlayOutEntityDestroy(new int[]{stand.getId()});

        this.keepalive = new BukkitRunnable() {
            @Override
            public void run() {

                Set<Player> playersTemp = new HashSet<>(players);

                for (Player player : playersTemp) {

                    if (!player.isOnline()
                            || !player.getWorld().getName().equals(location.getWorld().getName())) {

                        players.remove(player);
                        displaying.remove(player);
                        continue;
                    }

                    double distance = player.getLocation().distance(location);
                    boolean contains = displaying.contains(player);

                    if (contains && distance > 32.0D) {
                        displaying.remove(player);
                        despawn(player);
                        continue;
                    }

                    if (!contains && distance <= 32.0D) {
                        spawn(player);
                    }
                }
            }
        }.runTaskTimer((Plugin) LuckyBlocks.getInstance(), 0L, 20L);
    }

    public void addPlayer(Player player) {
        if (player.getWorld().getName().equals(location.getWorld().getName())) {
            players.add(player);
            spawn(player);
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
        displaying.remove(player);
        despawn(player);
    }

    public void spawn(Player player) {

        if (player.getLocation().distance(location) > 32.0D) return;

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

        connection.sendPacket(spawnPacket);
        connection.sendPacket(metaDataPacket);
        connection.sendPacket(armorPacket);

        displaying.add(player);
    }

    public void despawn(Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroyPacket);
    }

    public void destroy() {

        keepalive.cancel();

        for (Player player : players) {
            despawn(player);
        }

        players.clear();
        displaying.clear();

        spawnPacket = null;
        metaDataPacket = null;
        armorPacket = null;
        destroyPacket = null;
    }
}