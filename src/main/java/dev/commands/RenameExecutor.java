package dev.commands;

import dev.utils.HexTranslator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = ((Player) commandSender);
            if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(HexTranslator.translated(String.join(" ", args)));
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);
                return true;
            }
        }
        return false;
    }
}
