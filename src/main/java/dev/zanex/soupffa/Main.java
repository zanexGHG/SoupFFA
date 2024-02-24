package dev.zanex.soupffa;

import dev.zanex.commands.GamemodeCommand;
import dev.zanex.commands.SoupFFACommand;
import dev.zanex.util.Config;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {


        /* -- Register commands -- */
        getCommand("soupffa").setExecutor(new SoupFFACommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());

    }

    @Override
    public void onDisable() {


    }

    public static Plugin getInstance() {
        return instance;
    }
}
