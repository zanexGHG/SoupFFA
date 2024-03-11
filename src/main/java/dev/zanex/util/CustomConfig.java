package dev.zanex.util;

import java.util.ArrayList;
import java.util.List;

public class CustomConfig {

    public CustomConfig() {
    }

    public Config build(String configName) {
        Config config = new Config(configName);
        configList.add(config);
        return config;
    }

    public void addData(Config config, String dataString) {
        config.add(dataString);
    }

    public void removeDataGroup(Config config, String key) {
        config.remove(key);
    }

    public void removeData(Config config, String dataPath) {


        config.remove(dataPath);
    }

    public static List<Config> configList = new ArrayList<>();

    public static List<Config> getConfigList() {
        return configList;
    }
}
