package dev.zanex.soupffa;

import dev.zanex.commands.*;
import dev.zanex.commands.InvseeCommandListener;
import dev.zanex.listener.DisableEventListener;
import dev.zanex.listener.JoinQuitListener;
import dev.zanex.listener.SoupHealingListener;
import dev.zanex.util.CustomConfig;
import dev.zanex.util.manager.MySQLManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static Connection connection;
    private final MySQLManager mySQLManager = new MySQLManager();
    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        /* -- Create configs -- */
        CustomConfig config = new CustomConfig(this, "config.yml");
        config.setTemplate("configs/config.yml");
        config.save();

        CustomConfig messageConfig = new CustomConfig(this, "messages.yml");
        messageConfig.setTemplate("configs/messages.yml");
        messageConfig.save();

        CustomConfig mysqlConfig = new CustomConfig(this, "mysql.yml");
        mysqlConfig.setTemplate("configs/mysql.yml");
        mysqlConfig.save();

        /* -- Connect to MySQL database -- */
        connection = mySQLManager.connect("localhost", "3306", "SoupFFA", "admin", "admin");

        if(connection != null) {
            mySQLManager.createDefaultTables(connection);
            mySQLManager.executeUpdateStatement(connection, "INSERT INTO `players` (`uuid`, `name`, `kills`, `deaths`) VALUES ('1234', 'test', 51, 30)");
        } else {
            getLogger().severe("§cCould not connect to MySQL database");
        }

        /* -- Set standart gamerules -- */
        getServer().setDefaultGameMode(getServer().getDefaultGameMode());
        // change "world" to config value
        getServer().getWorld("world").setStorm(false);
        getServer().getWorld("world").setThundering(false);
        // change to time in config
        getServer().getWorld("world").setTime(6000);

        /* -- Register commands -- */
        getCommand("soupffa").setExecutor(new SoupFFACommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("invsee").setExecutor(new InvseeCommandListener());

        /* -- Register listener -- */
        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new SoupHealingListener(), this);
        pluginManager.registerEvents(new DisableEventListener(), this);
        pluginManager.registerEvents(new InvseeCommandListener(), this);
    }

    @Override
    public void onDisable() {
        /* -- Disabled warning -- */
        getLogger().severe("§cSoupFFA has been disabled");

    }

    public static Plugin getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }
}
