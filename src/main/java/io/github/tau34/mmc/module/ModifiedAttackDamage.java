package io.github.tau34.mmc.module;

import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedAttackDamage implements IHasTextComponent {
    OFF(0),
    LOW(4),
    MED(8),
    HIGH(16),
    EXTREME(24),
    MAX04(32),
    MAX05(44),
    MAX06(56),
    MAX07(68),
    MAX08(80),
    MAX09(96),
    MAX10(112),
    MAX11(128),
    MAX12(144),
    MAX13(160),
    MAX14(180),
    MAX15(200),
    MAX16(220),
    MAX17(240),
    MAX18(260),
    MAX19(280),
    MAX20(304),
    MAX21(328),
    MAX22(352),
    MAX23(376),
    MAX24(400),
    MAX25(424),
    MAX26(448),
    MAX27(476),
    MAX28(504),
    MAX29(532),
    MAX30(560),
    MAX31(588),
    MAX32(616),
    MAX33(644),
    MAX34(672),
    MAX35(704),
    MAX36(736),
    MAX37(768),
    MAX38(800),
    MAX39(832),
    MAX40(864),
    MAX41(896),
    MAX42(928),
    MAX43(960),
    MAX44(996),
    MAX45(1068),
    MAX46(1104),
    MAX47(1140),
    MAX48(1176),
    MAX49(1212),
    MAX50(1240),
    MAX51(1284),
    MAX52(1320),
    MAX53(1360),
    MAX54(1400),
    MAX55(1440),
    MAX56(1520),
    MAX57(1560),
    MAX58(1600),
    MAX59(1640),
    MAX60(1680),
    MAX61(1720),
    MAX62(1760),
    MAX63(1800),
    MAX64(1844);

    private final int damage;
    private final Component label;

    ModifiedAttackDamage(int damage) {
        this.damage = damage;
        this.label = TextComponentUtil.getString(Integer.toString(damage));
    }

    @Override
    public Component getTextComponent() {
        return label;
    }

    public int getDamage() {
        return damage;
    }
}
