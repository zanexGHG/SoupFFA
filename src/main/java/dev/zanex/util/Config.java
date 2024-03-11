package dev.zanex.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Config {
    private final File file;
    private Object configObject = null;

    public Config(String configName) {
        // Use a constant for the plugin directory


        this.file = new File( getConfigFilePath(configName));

        try {
            initializeConfigObject();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create/configure the config file", e);
        }
    }

    private void initializeConfigObject() throws IOException {
        if (!file.exists()) {
            // Create the parent directory if it does not exist
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create a new file and initialize the configObject
            file.createNewFile();

            if (isYamlFile()) {
                YamlConfiguration yamlConfig = new YamlConfiguration();
                yamlConfig.save(file);
                this.configObject = yamlConfig;
            } else if (isJsonFile()) {
                try {
                    Files.write(file.toPath(), "{}".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.configObject = new JSONObject();
            } else {
                throw new IllegalArgumentException("Unsupported file extension");
            }
        } else {
            if (isYamlFile()) {
                this.configObject = YamlConfiguration.loadConfiguration(file);
            } else if (isJsonFile()) {
                // Handle loading JSON content if needed
                this.configObject = loadJsonFromFile();
            } else {
                throw new IllegalArgumentException("Unsupported file extension");
            }
        }
    }

    private String getConfigFilePath(String configName) {
        if (configName.toLowerCase().endsWith(".yml") || configName.toLowerCase().endsWith(".json")) {
            return "./plugins/SoupFFA/" + configName;
        } else {
            return "./plugins/SoupFFA/" + configName + ".yml";
        }
    }

    private boolean isYamlFile() {
        return file.getName().toLowerCase().endsWith(".yml");
    }

    private boolean isJsonFile() {
        return file.getName().toLowerCase().endsWith(".json");
    }

    // Implement the loadJsonFromFile() method if needed

    public void save() {
        try {
            if (isYamlFile()) {
                ((YamlConfiguration) configObject).save(file);
            } else if (isJsonFile()) {
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(((JSONObject) configObject).toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add( String dataString) {
        if (isYamlFile()) {
            String lowDataString = dataString.toLowerCase();
            String [] dataArray = lowDataString.split("\\.");
            String dataGroupName = dataArray[0];
            String dataType = dataArray[1];
            String data = dataArray[2];
            ((YamlConfiguration) configObject).set(String.format("%s."+ dataType.toLowerCase(), dataGroupName.toLowerCase()), data.toLowerCase());
        } else if (isJsonFile()) {
            String lowDataString = dataString.toLowerCase();
            String [] dataArray = lowDataString.split("\\.");
            String key = dataArray[0];
            String data = dataArray[1];

            ((JSONObject) configObject).put(key, data);
        }
        save();
    }

    public Object get(String key) {
        if (isYamlFile()) {
            return ((YamlConfiguration) configObject).get(key);
        } else if (isJsonFile()) {
            return ((JSONObject) configObject).get(key);
        }
        return null;
    }

    private JSONObject loadJsonFromFile() {
        try {
            String fileContent = new String(Files.readAllBytes(file.toPath()));
            System.out.println("File Content: " + fileContent); // Debug statement

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(new FileReader(file));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse JSON content", e);
        }
    }

    public void remove(String key) {
        if (isYamlFile()) {
            ((YamlConfiguration) configObject).set(key, null);
        } else if (isJsonFile()) {
            ((JSONObject) configObject).remove(key);
        }
        save();
    }


}
