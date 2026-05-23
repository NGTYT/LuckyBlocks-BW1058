package ir.pmzhero.luckyblocks.generator;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import ir.pmzhero.luckyblocks.LuckyBlocks;
import ir.pmzhero.luckyblocks.arena.LuckyBlockArena;
import ir.pmzhero.luckyblocks.luckyblock.texture.LuckyBlockTexture;
import ir.pmzhero.luckyblocks.tasks.GeneratorSpawnTask;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class LuckyBlockGenerator {

    private static final Vector ZERO = new Vector(0, 0, 0);

    private final double x;
    private final double y;
    private final double z;

    private final int cooldown;
    private final int limit;

    private final LuckyBlockArena luckyBlockArena;

    private String arenaName;
    private World bukkitWorld;
    private ITeam team;
    private Location location;
    private BukkitTask task;
    private int count;

    public LuckyBlockGenerator(LoadedGenerator generator, IArena arena, LuckyBlockArena luckyBlockArena) {

        this.arenaName = generator.getArenaName();
        this.luckyBlockArena = luckyBlockArena;

        this.bukkitWorld = arena.getWorld();

        this.x = generator.getX();
        this.y = generator.getY();
        this.z = generator.getZ();

        this.location = new Location(bukkitWorld, x, y, z);

        this.team = arena.getTeam(generator.getTeam());

        this.cooldown = generator.getCooldown();

        this.limit = LuckyBlocks.getInstance().getCachedConfig().getGeneratorLimit();

        this.task = GeneratorSpawnTask.start(this);
    }

    public void generateItem() {

        if (count >= limit) return;

        if (team == null || team.getMembers().isEmpty()) {
            destroy();
            luckyBlockArena.removeGenerator(this);
            return;
        }

        count++;

        Item item = bukkitWorld.dropItem(
                location,
                LuckyBlockTexture.randomTexture().getBukkitItem()
        );

        item.setVelocity(ZERO);
        item.setCustomName("custom");
    }

    public void decreaseCount(int i) {
        this.count -= i;
    }

    public void destroy() {

        arenaName = null;
        bukkitWorld = null;
        team = null;
        location = null;

        if (task != null) task.cancel();
        task = null;

        count = 0;
    }

    // ✅ REQUIRED GETTERS (THIS FIXES YOUR ERRORS)

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    public int getCooldown() { return cooldown; }

    public World getBukkitWorld() { return bukkitWorld; }

    public Location getLocation() { return location; }

    public ITeam getTeam() { return team; }

    public int getCount() { return count; }
}