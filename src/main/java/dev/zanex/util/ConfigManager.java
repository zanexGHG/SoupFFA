package dev.zanex.util;



public class ConfigManager {

    public void createConfig(String configName){
        Config config = new Config(configName);
    }

    public void addDataString(String configName,String dataGroupName, String dataType, String dataString){
        Config config = new Config(configName);
        config.add(dataGroupName, dataType, dataString);
    }

    public String getDataString(String configName,String dataGroupName, String dataType){
        Config config = new Config(configName);
        return config.getData(dataGroupName, dataType);
    }
}