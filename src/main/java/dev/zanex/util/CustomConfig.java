package dev.zanex.util;


import java.util.ArrayList;
import java.util.List;

public class CustomConfig {

   public CustomConfig(){


   }

    public Config build(String configName){
       Config config = new Config(configName);
       return config;
    }

    public void addData(String configName, String dataString){

        Config config = new Config(configName);
       config.add(dataString);
    }

    public void removeData(String configName, String key){
       Config config = new Config(configName);
       config.remove(key);
    }



   public static List<String>confignameList = new ArrayList<>();




    public static List<String> getConfignameList() {
        return confignameList;
    }
}