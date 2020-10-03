package dev;

import dev.commands.LoreExecutor;
import dev.commands.RenameExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class RenamePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("rename").setExecutor(new RenameExecutor());
        getCommand("lore").setExecutor(new LoreExecutor());
    }
}
