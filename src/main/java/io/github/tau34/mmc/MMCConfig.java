package io.github.tau34.mmc;

import com.electronwill.nightconfig.core.CommentedConfig;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.MekanismConfigHelper;
import mekanism.common.config.value.CachedBooleanValue;
import mekanism.common.config.value.CachedDoubleValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class MMCConfig extends BaseMekanismConfig {
    public static MMCConfig INSTANCE = new MMCConfig();

    public final CachedIntValue dropperFluidCapacity;
    public final CachedLongValue dropperGasCapacity;
    public final CachedLongValue dropperInfusionCapacity;
    public final CachedLongValue dropperPigmentCapacity;
    public final CachedLongValue dropperSlurryCapacity;
    public final CachedIntValue dropperFluidRate;
    public final CachedLongValue dropperGasRate;
    public final CachedLongValue dropperInfusionRate;
    public final CachedLongValue dropperPigmentRate;
    public final CachedLongValue dropperSlurryRate;

    public final CachedIntValue energyUnitStacks;
    public final CachedIntValue excavationUnitStacks;
    public final CachedIntValue attackUnitStacks;
    public final CachedIntValue farmingUnitStacks;
    public final CachedIntValue fortuneUnitStacks;
    public final CachedIntValue blastingUnitStacks;
    public final CachedIntValue elecBreathUnitStacks;
    public final CachedIntValue visionUnitStacks;
    public final CachedIntValue locomotiveUnitStacks;
    public final CachedIntValue repulsorUnitStacks;
    public final CachedIntValue propulsionUnitStacks;
    public final CachedIntValue magneticUnitStacks;
    public final CachedIntValue solarUnitStacks;
    public final CachedIntValue geothermalUnitStacks;
    public final CachedIntValue weaponUnitStacks;
    public final CachedIntValue drawUnitStacks;

    public final CachedDoubleValue fusionIgnitionTemp;
    public final CachedBooleanValue isFusionHeatedByLava;
    public final CachedIntValue modificationSpeed;

    public final CachedIntValue basicFactoryProcesses;
    public final CachedIntValue advancedFactoryProcesses;
    public final CachedIntValue eliteFactoryProcesses;
    public final CachedIntValue ultimateFactoryProcesses;

    public final CachedLongValue quintupling1stMult;
    public final CachedLongValue quintupling2ndMult;
    public final CachedIntValue quintupling3rdMult;

    public final CachedLongValue nucleosynthesizerInputCapacity;
    public final CachedLongValue crystallizerInputCapacity;
    public final CachedLongValue dissolutionInputCapacity;
    public final CachedLongValue chemicalInfuserLeftCapacity;
    public final CachedLongValue chemicalInfuserRightCapacity;
    public final CachedLongValue chemicalInfuserOutputCapacity;
    public final CachedLongValue oxidizerOutputCapacity;
    public final CachedLongValue dissolutionOutputCapacity;
    public final CachedLongValue washerInputCapacity;
    public final CachedIntValue washerFluidCapacity;
    public final CachedLongValue washerOutputCapacity;
    public final CachedIntValue pumpOutputCapacity;
    public final CachedIntValue separatorInputCapacity;
    public final CachedLongValue separatorLeftCapacity;
    public final CachedLongValue separatorRightCapacity;
    public final CachedIntValue plenisherInputCapacity;
    public final CachedLongValue centrifugeInputCapacity;
    public final CachedLongValue centrifugeOutputCapacity;
    public final CachedLongValue activatorInputCapacity;
    public final CachedLongValue activatorOutputCapacity;
    public final CachedLongValue prcGasInputCapacity;
    public final CachedIntValue prcFluidCapacity;
    public final CachedLongValue prcGasOutputCapacity;
    public final CachedIntValue rotaryFluidCapacity;
    public final CachedLongValue rotaryGasCapacity;

    private final ForgeConfigSpec configSpec;

    MMCConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Gauge Dropper Settings").push("dropper");
        builder.comment("Gauge Dropper Capacity Settings").push("capacity");
        dropperFluidCapacity = CachedIntValue.wrap(this, builder.comment("Gauge Dropper Fluid Capacity(mB), default: 16000, max: 2147483647")
                .defineInRange("dropperFluidCapacity", 16000, 0, Integer.MAX_VALUE));
        dropperGasCapacity = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Gas Capacity(mB), default: 16000, max: 9223372036854775807")
                .defineInRange("dropperGasCapacity", 16000L, 0L, Long.MAX_VALUE));
        dropperInfusionCapacity = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Infusion Capacity(mB), default: 16000, max: 9223372036854775807")
                .defineInRange("dropperInfusionCapacity", 16000L, 0L, Long.MAX_VALUE));
        dropperPigmentCapacity = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Pigment Capacity(mB), default: 16000, max: 9223372036854775807")
                .defineInRange("dropperPigmentCapacity", 16000L, 0L, Long.MAX_VALUE));
        dropperSlurryCapacity = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Slurry Capacity(mB), default: 16000, max: 9223372036854775807")
                .defineInRange("dropperSlurryCapacity", 16000L, 0L, Long.MAX_VALUE));
        builder.pop().comment("Gauge Dropper Transfer Rate Settings").push("rate");
        dropperFluidRate = CachedIntValue.wrap(this, builder.comment("Gauge Dropper Fluid Transfer Rate(mB), default: 256, max: 2147483647")
                .defineInRange("dropperFluidRate", 256, 0, Integer.MAX_VALUE));
        dropperGasRate = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Gas Transfer Rate(mB), default: 256, max: 9223372036854775807")
                .defineInRange("dropperGasRate", 256L, 0L, Long.MAX_VALUE));
        dropperInfusionRate = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Infusion Transfer Rate(mB), default: 256, max: 9223372036854775807")
                .defineInRange("dropperInfusionRate", 256L, 0L, Long.MAX_VALUE));
        dropperPigmentRate = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Pigment Transfer Rate(mB), default: 256, max: 9223372036854775807")
                .defineInRange("dropperPigmentRate", 256L, 0L, Long.MAX_VALUE));
        dropperSlurryRate = CachedLongValue.wrap(this, builder.comment("Gauge Dropper Slurry Transfer Rate(mB), default: 256, max: 9223372036854775807")
                .defineInRange("dropperSlurryRate", 256L, 0L, Long.MAX_VALUE));
        builder.pop(2);

        builder.comment("Max Unit Stacks Settings").push("unit_stacks");
        energyUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Energy Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("energyUnitStacks", 8, 1, 64));
        excavationUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Excavation Escalation Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("excavationUnitStacks", 4, 1, 64));
        attackUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Attack Amplification Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("attackUnitStacks", 4, 1, 64));
        farmingUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Farming Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("farmingUnitStacks", 4, 1, 64));
        blastingUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Blasting Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("blastingUnitStacks", 4, 1, 64));
        elecBreathUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Electrolytic Breathing Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("elecBreathUnitStacks", 4, 1, 64));
        visionUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Vision Enhancement Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("visionUnitStacks", 4, 1, 64));
        fortuneUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Fortune Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("fortuneUnitStacks", 3, 1, 64));
        locomotiveUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Locomotive Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("locomotiveUnitStacks", 4, 1, 64));
        repulsorUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Hydrostatic Repulsor Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("repulsorUnitStacks", 4, 1, 64));
        propulsionUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Hydraulic Propulsion Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("propulsionUnitStacks", 4, 1, 64));
        magneticUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Magnetic Attraction Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("magneticUnitStacks", 4, 1, 64));
        solarUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Solar Recharging Propulsion Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("solarUnitStacks", 8, 1, 64));
        geothermalUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Geothermal Generator Unit Stacks. default: 8, min: 1, max: 64")
                .defineInRange("geothermalUnitStacks", 8, 1, 64));
        weaponUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Weapon Attack Amplification Unit (Mekanism: Weapons) Stacks. default: 4, min: 1, max: 64")
                .defineInRange("weaponUnitStacks", 4, 1, 64));
        drawUnitStacks = CachedIntValue.wrap(this, builder.comment("Max Draw Speed Unit (Mekanism: Weapons) Stacks. default: 4, min: 1, max: 64")
                .defineInRange("drawUnitStacks", 4, 1, 64));
        builder.pop();

        builder.comment("Misc Settings").push("misc");
        fusionIgnitionTemp = CachedDoubleValue.wrap(this, builder.comment("The ignition temperature of a fusion reactor. default: 1.0E8")
                .defineInRange("fusionIgnitionTemp", 1.0E8, 0, Double.MAX_VALUE));
        isFusionHeatedByLava = CachedBooleanValue.wrap(this, builder.comment("Whether fusion reactor would be heated by lava")
                .define("isFusionHeatedByLava", false));
        modificationSpeed = CachedIntValue.wrap(this, builder.comment("The ticks Modification Station requires to install. default: 40")
                .defineInRange("modificationSpeed", 20, 1, Integer.MAX_VALUE));
        builder.pop();

        builder.comment("Factory Processes Settings").push("factory");
        basicFactoryProcesses = CachedIntValue.wrap(this, builder.comment("Basic Factory Processes. default: 3, min: 1, max: 2147483647")
                .defineInRange("basicFactoryProcesses", 3, 1, Integer.MAX_VALUE));
        advancedFactoryProcesses = CachedIntValue.wrap(this, builder.comment("Advanced Factory Processes. default: 5, min: 1, max: 2147483647")
                .defineInRange("advancedFactoryProcesses", 5, 1, Integer.MAX_VALUE));
        eliteFactoryProcesses = CachedIntValue.wrap(this, builder.comment("Elite Factory Processes. default: 7, min: 1, max: 2147483647")
                .defineInRange("eliteFactoryProcesses", 7, 1, Integer.MAX_VALUE));
        ultimateFactoryProcesses = CachedIntValue.wrap(this, builder.comment("Ultimate Factory Processes. default: 9, min: 1, max: 2147483647")
                .defineInRange("ultimateFactoryProcesses", 9, 1, Integer.MAX_VALUE));
        builder.pop();

        builder.comment("Ore Processing Settings").push("ore");
        long MAX = Long.MAX_VALUE / 6000 * 5;
        quintupling1stMult = CachedLongValue.wrap(this, builder.comment("Chemical Dissolution Output Multiplier of Quintupling Ore Processing. default: 5, min: 1, max: " + MAX)
                .defineInRange("quintupling1stMult", 5L, 1L, MAX));
        MAX = Long.MAX_VALUE;
        quintupling2ndMult = CachedLongValue.wrap(this, builder.comment("Chemical Washing Output Multiplier of Quintupling Ore Processing. default: 1, min: 1, max: " + MAX)
                .defineInRange("quintupling2ndMult", 1L, 1L, MAX));
        quintupling3rdMult = CachedIntValue.wrap(this, builder.comment("Chemical Crystallizing Output Multiplier of Quintupling Ore Processing. default: 5, min: 1, max: 64")
                .defineInRange("quintupling3rdMult", 1, 1, 64));
        builder.pop();

        builder.comment("Machine Capacity Settings").push("machine_capacity");
        nucleosynthesizerInputCapacity = CachedLongValue.wrap(this, builder.comment("Antiprotonic Nucleosynthesizer Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("nucleosynthesizerInputCapacity", 10000L, 1L, Long.MAX_VALUE));
        crystallizerInputCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Crystallizer Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("crystallizerInputCapacity", 10000L, 1L, Long.MAX_VALUE));
        oxidizerOutputCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Oxidizer Output Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("oxidizerOutputCapacity", 10000L, 1L, Long.MAX_VALUE));
        dissolutionInputCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Dissolution Chamber Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("dissolutionInputCapacity", 10000L, 1L, Long.MAX_VALUE));
        dissolutionOutputCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Dissolution Chamber Output Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("dissolutionOutputCapacity", 10000L, 1L, Long.MAX_VALUE));
        chemicalInfuserLeftCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Infuser Left Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("chemicalInfuserLeftCapacity", 10000L, 1L, Long.MAX_VALUE));
        chemicalInfuserRightCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Infuser Right Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("chemicalInfuserRightCapacity", 10000L, 1L, Long.MAX_VALUE));
        chemicalInfuserOutputCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Infuser Output Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("chemicalInfuserOutputCapacity", 10000L, 1L, Long.MAX_VALUE));
        washerInputCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Washer Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("washerInputCapacity", 10000L, 1L, Long.MAX_VALUE));
        washerFluidCapacity = CachedIntValue.wrap(this, builder.comment("Chemical Washer Fluid Input Capacity. default: 10000, min: 1, max: " + Integer.MAX_VALUE)
                .defineInRange("washerFluidCapacity", 10000, 1, Integer.MAX_VALUE));
        washerOutputCapacity = CachedLongValue.wrap(this, builder.comment("Chemical Washer Output Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("washerOutputCapacity", 10000L, 1L, Long.MAX_VALUE));
        pumpOutputCapacity = CachedIntValue.wrap(this, builder.comment("Electric Pump Output Capacity. default: 10000, min: 1, max: " + Integer.MAX_VALUE)
                .defineInRange("pumpOutputCapacity", 10000, 1, Integer.MAX_VALUE));
        separatorInputCapacity = CachedIntValue.wrap(this, builder.comment("Electrolytic Separator Input Capacity. default: 24000, min: 1, max: " + Integer.MAX_VALUE)
                .defineInRange("separatorInputCapacity", 24000, 1, Integer.MAX_VALUE));
        separatorLeftCapacity = CachedLongValue.wrap(this, builder.comment("Electrolytic Separator Left Output Capacity. default: 2400, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("separatorLeftCapacity", 2400L, 1L, Long.MAX_VALUE));
        separatorRightCapacity = CachedLongValue.wrap(this, builder.comment("Electrolytic Separator Right Output Capacity. default: 2400, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("separatorRightCapacity", 2400L, 1L, Long.MAX_VALUE));
        plenisherInputCapacity = CachedIntValue.wrap(this, builder.comment("Fluidic Plenisher Input Capacity. default: 10000, min: 1, max: " + Integer.MAX_VALUE)
                .defineInRange("plenisherInputCapacity", 10000, 1, Integer.MAX_VALUE));
        centrifugeInputCapacity = CachedLongValue.wrap(this, builder.comment("Isotopic Centrifuge Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("centrifugeInputCapacity", 10000L, 1L, Long.MAX_VALUE));
        centrifugeOutputCapacity = CachedLongValue.wrap(this, builder.comment("Isotopic Centrifuge Output Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("centrifugeOutputCapacity", 10000L, 1L, Long.MAX_VALUE));
        activatorInputCapacity = CachedLongValue.wrap(this, builder.comment("Solar Neutron Activator Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("activatorInputCapacity", 10000L, 1L, Long.MAX_VALUE));
        activatorOutputCapacity = CachedLongValue.wrap(this, builder.comment("Solar Neutron Activator Output Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("activatorOutputCapacity", 10000L, 1L, Long.MAX_VALUE));
        prcGasInputCapacity = CachedLongValue.wrap(this, builder.comment("PRC Gas Input Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("prcGasInputCapacity", 10000L, 1L, Long.MAX_VALUE));
        prcFluidCapacity = CachedIntValue.wrap(this, builder.comment("PRC Fluid Input Capacity. default: 10000, min: 1, max: " + Integer.MAX_VALUE)
                .defineInRange("prcFluidCapacity", 10000, 1, Integer.MAX_VALUE));
        prcGasOutputCapacity = CachedLongValue.wrap(this, builder.comment("PRC Gas Output Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("prcGasOutputCapacity", 10000L, 1L, Long.MAX_VALUE));
        rotaryFluidCapacity = CachedIntValue.wrap(this, builder.comment("Rotary Fluid Capacity. default: 10000, min: 1, max: " + Integer.MAX_VALUE)
                .defineInRange("rotaryFluidCapacity", 10000, 1, Integer.MAX_VALUE));
        rotaryGasCapacity = CachedLongValue.wrap(this, builder.comment("Rotary Gas Capacity. default: 10000, min: 1, max: " + Long.MAX_VALUE)
                .defineInRange("rotaryGasCapacity", 10000L, 1L, Long.MAX_VALUE));
        builder.pop();

        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "more-config";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }

    public static void registerConfig(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        MekanismConfigHelper.registerConfig(modContainer, INSTANCE);
    }

    public static int getEnergyUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "energyUnitStacks", 8);
    }

    public static int getExcavationUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "excavationUnitStacks", 4);
    }

    public static int getAttackUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "attackUnitStacks", 4);
    }

    public static int getFarmingUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "farmingUnitStacks", 4);
    }

    public static int getFortuneUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "fortuneUnitStacks", 4);
    }

    public static int getBlastingUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "blastingUnitStacks", 4);
    }

    public static int getBreathingUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "elecBreathUnitStacks", 4);
    }

    public static int getVisionUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "visionUnitStacks", 3);
    }

    public static int getLocomotiveUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "locomotiveUnitStacks", 4);
    }

    public static int getRepulsorUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "repulsorUnitStacks", 4);
    }

    public static int getPropulsionUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "propulsionUnitStacks", 4);
    }

    public static int getMagneticUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "magneticUnitStacks", 4);
    }

    public static int getSolarUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "solarUnitStacks", 8);
    }

    public static int getGeothermalUnitStacks() {
        return getConfigFromPath(getUnitStacks(), "geothermalUnitStacks", 8);
    }

    private static CommentedConfig getUnitStacks() {
        return (CommentedConfig) MMCConfigLoader.loadTomlConfig().get("unit_stacks");
    }

    public static int getBasicProcesses() {
        return getConfigFromPath(getFactory(), "basicFactoryProcesses", 5);
    }

    public static int getAdvancedProcesses() {
        return getConfigFromPath(getFactory(), "advancedFactoryProcesses", 5);
    }

    public static int getEliteProcesses() {
        return getConfigFromPath(getFactory(), "eliteFactoryProcesses", 7);
    }

    public static int getUltimateProcesses() {
        return getConfigFromPath(getFactory(), "ultimateFactoryProcesses", 9);
    }

    private static CommentedConfig getFactory() {
        return (CommentedConfig) MMCConfigLoader.loadTomlConfig().get("factory");
    }

    private static int getConfigFromPath(CommentedConfig c, String path, int def) {
        return c == null ? def : c.getIntOrElse(path, def);
    }
}
