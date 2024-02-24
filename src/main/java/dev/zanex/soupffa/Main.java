package dev.zanex.soupffa;

import dev.zanex.util.ConfigManager;
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

    }

    @Override
    public void onDisable() {


    }

    public static Plugin getInstance() {
        return instance;
    }
}
