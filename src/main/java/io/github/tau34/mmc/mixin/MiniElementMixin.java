package io.github.tau34.mmc.mixin;

import mekanism.client.gui.element.custom.module.GuiModuleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "mekanism.client.gui.element.custom.module.MiniElement", remap = false)
public interface MiniElementMixin {
    @Accessor
    public GuiModuleScreen getParent();

    @Accessor
    public int getXPos();

    @Invoker
    public int invokeGetRelativeX();

    @Invoker
    public int invokeGetRelativeY();
}
