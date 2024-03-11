package dev.zanex.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class CustomConfig {
    private final Plugin plugin;
    private File configFile;
    private YamlConfiguration config;
    private Map<String, Object> configMap;

    public CustomConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), fileName);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void addLine(String path, Object value) {
        config.set(path, value);
        save();
    }

    public Object getValue(String path) {
        return config.get(path);
    }

    public Object getByString(String path) {
        String[] keys = path.split("\\.");
        Object value = config;
        for (String key : keys) {
            if (value instanceof Map) {
                value = ((Map<?, ?>) value).get(key);
            } else {
                return null;
            }
        }
        return value;
    }

    public Map<String, Object> getConfigMap() {
        return configMap;
    }

    public void setTemplate(String templateName) {
        InputStream inputStream = plugin.getResource(templateName);
        if (inputStream == null) {
            plugin.getLogger().warning("Template '" + templateName + "' not found in resources.");
            return;
        }

        Yaml yaml = new Yaml();
        InputStreamReader reader = new InputStreamReader(inputStream);
        Map<String, Object> templateConfig = (Map<String, Object>) yaml.load(reader);
        if (templateConfig != null) {
            for (Map.Entry<String, Object> entry : templateConfig.entrySet()) {
                config.set(entry.getKey(), entry.getValue());
            }
            save();
        } else {
            plugin.getLogger().warning("Failed to load template '" + templateName + "'.");
        }

        try {
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to close input streams: " + e.getMessage());
        }
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().warning("Could not save config file: " + e.getMessage());
        }
    }
}