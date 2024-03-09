package dev.zanex.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {

   public static List<String>confignameList = new ArrayList<>();

    public static Config createConfig(String configName){
        Config config = new Config(configName.toLowerCase());
        confignameList.add(configName.toLowerCase());
        return config;
    }

    public static void addDataString(String configName,String dataGroupName, String dataType, String dataString){
        Config config = new Config(configName.toLowerCase());
        config.add(dataGroupName, dataType, dataString);
    }

    public static String getDataString(String dataString){
        String lowDataString = dataString.toLowerCase();
        String [] dataArray = lowDataString.split("\\.");
        String configName = dataArray[0];
        String dataGroupName = dataArray[1];
        String dataType = dataArray[2];
        Config config = new Config(configName.toLowerCase());
        return config.getData(dataGroupName.toLowerCase(), dataType.toLowerCase());
    }

    public static List<String> getConfignameList() {
        return confignameList;
    }
}