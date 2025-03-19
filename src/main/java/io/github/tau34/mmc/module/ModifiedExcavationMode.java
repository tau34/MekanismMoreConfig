package io.github.tau34.mmc.module;

import mekanism.api.IIncrementalEnum;
import mekanism.api.math.MathUtils;
import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedExcavationMode  implements IIncrementalEnum<ModifiedExcavationMode>, IHasTextComponent {
    OFF(0),
    M00(4),
    M01(8),
    M02(12),
    M03(16),
    M04(20),
    M05(24),
    M06(28),
    M07(32),
    M08(36),
    M09(40),
    M10(44),
    M11(48),
    M12(52),
    M13(56),
    M14(60),
    M15(64),
    M16(68),
    M17(72),
    M18(76),
    M19(80),
    M20(84),
    M21(88),
    M22(92),
    M23(96),
    M24(100),
    M25(104),
    M26(108),
    M27(112),
    M28(116),
    M29(120),
    M30(124),
    M31(128),
    M32(132),
    M33(136),
    M34(140),
    M35(144),
    M36(148),
    M37(152),
    M38(156),
    M39(160),
    M40(164),
    M41(168),
    M42(172),
    M43(176),
    M44(180),
    M45(184),
    M46(188),
    M47(192),
    M48(196),
    M49(200),
    M50(204),
    M51(208),
    M52(212),
    M53(216),
    M54(220),
    M55(224),
    M56(228),
    M57(232),
    M58(236),
    M59(240),
    M60(244),
    M61(248),
    M62(252),
    M63(256),
    M64(260);

    private final Component label;
    private final int efficiency;

    ModifiedExcavationMode(int efficiency) {
        label = TextComponentUtil.getString(Integer.toString(efficiency));
        this.efficiency = efficiency;
    }

    @Override
    public ModifiedExcavationMode byIndex(int index) {
        return MathUtils.getByIndexMod(values(), index);
    }

    @Override
    public Component getTextComponent() {
        return label;
    }

    public int getEfficiency() {
        return efficiency;
    }
}
