package io.github.tau34.mmc;

import com.electronwill.nightconfig.core.CommentedConfig;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.MekanismConfigHelper;
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

    public final CachedDoubleValue fusionIgnitionTemp;

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
        builder.pop();

        builder.comment("Fusion Reactor Settings").push("fusion");
        fusionIgnitionTemp = CachedDoubleValue.wrap(this, builder.comment("the ignition temperature of a fusion reactor")
                .defineInRange("ignitionTemp", 1.0E8, 0, Double.MAX_VALUE));
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
        return getUnitStacks().getInt("energyUnitStacks");
    }

    public static int getExcavationUnitStacks() {
        return getUnitStacks().getInt("excavationUnitStacks");
    }

    public static int getAttackUnitStacks() {
        return getUnitStacks().getInt("attackUnitStacks");
    }

    public static int getFarmingUnitStacks() {
        return getUnitStacks().getInt("farmingUnitStacks");
    }

    public static int getFortuneUnitStacks() {
        return getUnitStacks().getInt("fortuneUnitStacks");
    }

    public static int getBlastingUnitStacks() {
        return getUnitStacks().getInt("blastingUnitStacks");
    }

    public static int getBreathingUnitStacks() {
        return getUnitStacks().getInt("elecBreathUnitStacks");
    }

    public static int getVisionUnitStacks() {
        return getUnitStacks().getInt("visionUnitStacks");
    }

    public static int getLocomotiveUnitStacks() {
        return getUnitStacks().getInt("locomotiveUnitStacks");
    }

    public static int getRepulsorUnitStacks() {
        return getUnitStacks().getInt("repulsorUnitStacks");
    }

    public static int getPropulsionUnitStacks() {
        return getUnitStacks().getInt("propulsionUnitStacks");
    }

    public static int getMagneticUnitStacks() {
        return getUnitStacks().getInt("magneticUnitStacks");
    }

    public static int getSolarUnitStacks() {
        return getUnitStacks().getInt("solarUnitStacks");
    }

    public static int getGeothermalUnitStacks() {
        return getUnitStacks().getInt("geothermalUnitStacks");
    }

    private static CommentedConfig getUnitStacks() {
        return (CommentedConfig) MMCConfigLoader.loadTomlConfig().get("unit_stacks");
    }
}
