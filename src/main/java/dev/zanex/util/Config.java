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

    public Config(String configName) {

        File dir = new File("./plugins/soupffa");

        if (!dir.exists()){
            dir.mkdir();
        }

        this.file = new File(dir, configName + ".yml");

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

    public void add(String dataGroupName, String dataType, String DataString ) {
        if (!config.contains(dataGroupName)) {
            config.set(String.format("%s."+ dataType, dataGroupName), DataString);
        }
        save();
    }

    public String getData(String dataGroupName, String dataType){
        String outPut = config.getString(String.format("%s."+dataType, dataGroupName));
        return outPut;
    }



}
