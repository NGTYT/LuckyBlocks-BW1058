package ir.pmzhero.luckyblocks.drops;

import ir.pmzhero.luckyblocks.data.Language;
import ir.pmzhero.luckyblocks.utils.NBTUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@RequiredArgsConstructor
public class DropSleepingDust extends Drop {

    private final ItemStack drop;
    private final Language language;

    public DropSleepingDust(Language language) {

        ItemStack stack = new ItemStack(Material.CARPET, 1, (short) 10);

        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sleeping Dust");

        stack.setItemMeta(meta);

        this.drop = NBTUtils.applyNBTTags(stack, "sleepDust");
        this.language = language;
    }

    @Override
    public void drop(Location location, Player player) {

        ItemStack clone = drop.clone();

        int amount = getRandom().nextInt(2) + 1;

        clone.setAmount(amount);

        location.getWorld().dropItem(location, clone);

        if (player != null) {
            language.sendReceivedItemMessage(
                    player,
                    ChatColor.LIGHT_PURPLE + "Sleeping Dust",
                    amount
            );
        }
    }
}