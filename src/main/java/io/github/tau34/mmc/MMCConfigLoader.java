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
            try {
                FileWriter fw = new FileWriter(configFile, true);
                fw.write("\n" +
                        "#Gauge Dropper Settings\n" +
                        "[dropper]\n" +
                        "\n" +
                        "\t#Gauge Dropper Capacity Settings\n" +
                        "\t[dropper.capacity]\n" +
                        "\t\t#Gauge Dropper Fluid Capacity(mB), default: 16000, max: 2147483647\n" +
                        "\t\t#Range: > 0\n" +
                        "\t\tdropperFluidCapacity = 16000\n" +
                        "\t\t#Gauge Dropper Gas Capacity(mB), default: 16000, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperGasCapacity = 16000\n" +
                        "\t\t#Gauge Dropper Infusion Capacity(mB), default: 16000, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperInfusionCapacity = 16000\n" +
                        "\t\t#Gauge Dropper Pigment Capacity(mB), default: 16000, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperPigmentCapacity = 16000\n" +
                        "\t\t#Gauge Dropper Slurry Capacity(mB), default: 16000, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperSlurryCapacity = 16000\n" +
                        "\n" +
                        "\t#Gauge Dropper Transfer Rate Settings\n" +
                        "\t[dropper.rate]\n" +
                        "\t\t#Gauge Dropper Fluid Transfer Rate(mB), default: 256, max: 2147483647\n" +
                        "\t\t#Range: > 0\n" +
                        "\t\tdropperFluidRate = 256\n" +
                        "\t\t#Gauge Dropper Gas Transfer Rate(mB), default: 256, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperGasRate = 256\n" +
                        "\t\t#Gauge Dropper Infusion Transfer Rate(mB), default: 256, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperInfusionRate = 256\n" +
                        "\t\t#Gauge Dropper Pigment Transfer Rate(mB), default: 256, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperPigmentRate = 256\n" +
                        "\t\t#Gauge Dropper Slurry Transfer Rate(mB), default: 256, max: 9223372036854775807\n" +
                        "\t\t#Range: 0 ~ 9223372036854775807\n" +
                        "\t\tdropperSlurryRate = 256\n" +
                        "\n" +
                        "#Max Unit Stacks Settings\n" +
                        "[unit_stacks]\n" +
                        "\t#Max Energy Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tenergyUnitStacks = 64\n" +
                        "\t#Max Excavation Escalation Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\texcavationUnitStacks = 64\n" +
                        "\t#Max Attack Amplification Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tattackUnitStacks = 64\n" +
                        "\t#Max Fortune Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tfortuneUnitStacks = 64\n" +
                        "\t#Max Locomotive Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tlocomotiveUnitStacks = 64\n" +
                        "\t#Max Farming Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tfarmingUnitStacks = 4\n" +
                        "\t#Max Blasting Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tblastingUnitStacks = 4\n" +
                        "\t#Max Electrolytic Breathing Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\telecBreathUnitStacks = 4\n" +
                        "\t#Max Vision Enhancement Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tvisionUnitStacks = 4\n" +
                        "\t#Max Hydrostatic Repulsor Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\trepulsorUnitStacks = 4\n" +
                        "\t#Max Hydraulic Propulsion Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tpropulsionUnitStacks = 4\n" +
                        "\t#Max Magnetic Attraction Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tmagneticUnitStacks = 4\n" +
                        "\t#Max Solar Recharging Propulsion Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tsolarUnitStacks = 8\n" +
                        "\t#Max Geothermal Generator Unit Stacks. default: 8, min: 1, max: 64\n" +
                        "\t#Range: 1 ~ 64\n" +
                        "\tgeothermalUnitStacks = 8\n" +
                        "\n" +
                        "#Fusion Reactor Settings\n" +
                        "[fusion]\n" +
                        "\t#the ignition temperature of a fusion reactor\n" +
                        "\t#Range: 0.0 ~ 1.7976931348623157E308\n" +
                        "\tignitionTemp = 1E8\n" +
                        "\n" +
                        "#Factory Processes Settings\n" +
                        "[factory]\n" +
                        "\t#Basic Factory Processes. default: 3, min: 1, max: 2147483647\n" +
                        "\t#Range: > 1\n" +
                        "\tbasicFactoryProcesses = 3\n" +
                        "\t#Advanced Factory Processes. default: 5, min: 1, max: 2147483647\n" +
                        "\t#Range: > 1\n" +
                        "\tadvancedFactoryProcesses = 5\n" +
                        "\t#Elite Factory Processes. default: 7, min: 1, max: 2147483647\n" +
                        "\t#Range: > 1\n" +
                        "\teliteFactoryProcesses = 7\n" +
                        "\t#Ultimate Factory Processes. default: 9, min: 1, max: 2147483647\n" +
                        "\t#Range: > 1\n" +
                        "\tultimateFactoryProcesses = 9\n" +
                        "\n");
                fw.close();
            } catch (IOException e) {
                LogUtils.getLogger().info(e.getLocalizedMessage());
            }
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
