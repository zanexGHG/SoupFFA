package dev.zanex.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Config {
    private final File file;
    private final YamlConfiguration config;

    public Config() {

        File dir = new File("./plugins/craftattack/config");

        if (!dir.exists()){
            dir.mkdir();
        }

        this.file = new File(dir,"config.yml");

        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){ e.printStackTrace();}
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void save() {

        try {
            config.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void add(String name, ) {
        if (!config.contains(name)) {
            config.set(String.format("%s.Damage", name), damage);
            config.set(String.format("%s.Chatcolor", name), chatcolor);
            config.set(String.format("%s.Location", name), location);
            config.set(String.format("%s.Tickspeed", name), tickspeed);

        }
        save();
    }



}
