package dev.commands;

import dev.utils.HexTranslator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoreExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player && args.length > 0) {
            Player player = ((Player) commandSender);
            if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                List<String> lore = meta.getLore();
                if (lore == null) {
                    lore = new ArrayList<>();
                }
                if (args[0].equalsIgnoreCase("clear")) {
                    lore.clear();
                } else if (args[0].equalsIgnoreCase("add")) {
                    String entry = "";
                    if (args.length > 1) {
                        entry = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    }
                    lore.add(HexTranslator.translated(entry));
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (args.length > 2) {
                        if (args[1].matches("\\d+")) {
                            int index = Integer.parseInt(args[1]);
                            if (lore.size() > index) {
                                String entry = "";
                                entry = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                                lore.set(index, HexTranslator.translated(entry));
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (args[1].matches("\\d+")) {
                        int index = Integer.parseInt(args[1]);
                        if (lore.size() > index) {
                            lore.remove(index);
                        }
                    }
                }
                meta.setLore(lore);
                if (!meta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)) {
                    meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                }
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);
                return true;
            }
        }

        return false;
    }

}
