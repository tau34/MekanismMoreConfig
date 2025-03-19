package io.github.tau34.mmc.module;

import mekanism.api.IIncrementalEnum;
import mekanism.api.math.MathUtils;
import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedSprintSpeed implements IHasTextComponent, IIncrementalEnum<ModifiedSprintSpeed> {
    OFF(0F),
    M01(0.05F),
    M02(0.10F),
    M03(0.15F),
    M04(0.20F),
    M05(0.25F),
    M06(0.30F),
    M07(0.35F),
    M08(0.40F),
    M09(0.45F),
    M10(0.50F),
    M11(0.55F),
    M12(0.60F),
    M13(0.65F),
    M14(0.70F),
    M15(0.75F),
    M16(0.80F),
    M17(0.85F),
    M18(0.90F),
    M19(0.95F),
    M20(1.00F),
    M21(1.05F),
    M22(1.10F),
    M23(1.15F),
    M24(1.20F),
    M25(1.25F),
    M26(1.30F),
    M27(1.35F),
    M28(1.40F),
    M29(1.45F),
    M30(1.50F),
    M31(1.55F),
    M32(1.60F),
    M33(1.65F),
    M34(1.70F),
    M35(1.75F),
    M36(1.80F),
    M37(1.85F),
    M38(1.90F),
    M39(1.95F),
    M40(2.00F),
    M41(2.05F),
    M42(2.10F),
    M43(2.15F),
    M44(2.20F),
    M45(2.25F),
    M46(2.30F),
    M47(2.35F),
    M48(2.40F),
    M49(2.45F),
    M50(2.50F),
    M51(2.55F),
    M52(2.60F),
    M53(2.65F),
    M54(2.70F),
    M55(2.75F),
    M56(2.80F),
    M57(2.85F),
    M58(2.90F),
    M59(2.95F),
    M60(3.00F),
    M61(3.05F),
    M62(3.10F),
    M63(3.15F),
    M64(3.20F);
    
    private final float boost;
    private final Component label;

    ModifiedSprintSpeed(float boost) {
        this.boost = boost;
        this.label = TextComponentUtil.getString(Float.toString(boost));
    }

    @Override
    public Component getTextComponent() {
        return label;
    }

    public float getBoost() {
        return boost;
    }

    @Override
    public ModifiedSprintSpeed byIndex(int index) {
        return MathUtils.getByIndexMod(values(), index);
    }
}
