package io.github.tau34.mmc.mixin;

import mekanism.client.gui.element.custom.module.GuiModuleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "mekanism.client.gui.element.custom.module.MiniElement", remap = false)
public interface MiniElementMixin {
    @Accessor
    GuiModuleScreen getParent();

    @Accessor
    int getXPos();

    @Invoker
    int invokeGetRelativeX();

    @Invoker
    int invokeGetRelativeY();
}
