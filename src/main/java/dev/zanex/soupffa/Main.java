package dev.zanex.soupffa;

import dev.zanex.commands.GamemodeCommand;
import dev.zanex.commands.SoupFFACommand;
import dev.zanex.listener.JoinQuitListener;
import dev.zanex.listener.SoupHealingListener;
import dev.zanex.util.MySQLManager;
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

        /* -- Connect to MySQL database -- */
        connection = mySQLManager.connect("localhost", "3306", "SoupFFA", "admin", "admin");

        if(connection != null) {
            mySQLManager.createDefaultTables(connection);
            mySQLManager.executeUpdateStatement(connection, "INSERT INTO `players` (`uuid`, `name`, `kills`, `deaths`) VALUES ('1234', 'test', 51, 30)");
        } else {
            getLogger().severe("§cCould not connect to MySQL database");
        }

        /* -- Register commands -- */
        getCommand("soupffa").setExecutor(new SoupFFACommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());

        /* -- Register listener -- */
        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new SoupHealingListener(), this);
        pluginManager.registerEvents(new SoupHealingListener(), this);
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
