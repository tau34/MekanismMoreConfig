package io.github.tau34.mmc;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.mojang.logging.LogUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MMCConfigLoader {
    public static Map<String, Object> loadTomlConfig() {
        File configFile = Paths.get("config", "Mekanism", "more-config.toml").toFile(); // Forgeの標準形式

        if (!configFile.exists()) {
            LogUtils.getLogger().info("config/Mekanism/more-config.toml not found.");
        }

        try (FileConfig config = FileConfig.of(configFile)) {
            config.load();

            Map<String, Object> result = new HashMap<>();
            parseConfig(config, result, "");

            return result;
        }
    }

    private static void parseConfig(FileConfig config, Map<String, Object> result, String prefix) {
        for (String key : config.valueMap().keySet()) {
            Object value = config.get(key);
            String fullKey = prefix.isEmpty() ? key : prefix + "." + key;

            if (value instanceof FileConfig fc) {
                parseConfig(fc, result, fullKey);
            } else {
                result.put(fullKey, value);
            }
        }
    }
}
