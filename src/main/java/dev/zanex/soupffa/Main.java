package dev.zanex.soupffa;

import dev.zanex.commands.BuildCommand;
import dev.zanex.commands.FlyCommand;
import dev.zanex.commands.GamemodeCommand;
import dev.zanex.commands.SoupFFACommand;
import dev.zanex.listener.DisableEventListener;
import dev.zanex.listener.JoinQuitListener;
import dev.zanex.listener.SoupHealingListener;
import dev.zanex.util.Config;
import dev.zanex.util.CustomConfig;
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
    private CustomConfig customConfig = new CustomConfig();


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
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("build").setExecutor(new BuildCommand());

        /* -- Register listener -- */
        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new SoupHealingListener(), this);
        pluginManager.registerEvents(new DisableEventListener(), this);

        /*-- Create configs --*/
       Config test =  customConfig.build("test.json");
       Config test1 = customConfig.build("test1.yml");
       Config test2 = customConfig.build("test2");
       Config test3 = customConfig.build("test3");

        customConfig.addData( test1,"peter.pan.jo");
        customConfig.addData( test1,"peter.faul.jo");

        customConfig.addData( test2,"peter.faul.jo");
        customConfig.removeDataGroup(test2 ,"peter");


        customConfig.addData(test3, "pan.peter.jo");
        customConfig.removeData(test3, "pan.peter");
        customConfig.addData(test3, "pan.peter.no");




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
