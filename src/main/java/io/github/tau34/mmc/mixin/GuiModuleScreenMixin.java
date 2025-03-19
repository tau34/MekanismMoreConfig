package io.github.tau34.mmc.mixin;

import mekanism.client.gui.element.custom.module.GuiModuleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = GuiModuleScreen.class, remap = false)
public interface GuiModuleScreenMixin {
    @Invoker
    public int invokeGetScreenWidth();
}
