package io.github.tau34.mmc.module;

import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedWAttackDamage implements IHasTextComponent {
    OFF(0),
    MAX00(1),
    MAX01(2),
    MAX02(3),
    MAX03(4),
    MAX04(5),
    MAX05(6),
    MAX06(7),
    MAX07(8),
    MAX08(9),
    MAX09(10),
    MAX10(11),
    MAX11(12),
    MAX12(13),
    MAX13(14),
    MAX14(15),
    MAX15(16),
    MAX16(17),
    MAX17(18),
    MAX18(19),
    MAX19(20),
    MAX20(21),
    MAX21(22),
    MAX22(23),
    MAX23(24),
    MAX24(25),
    MAX25(26),
    MAX26(27),
    MAX27(28),
    MAX28(29),
    MAX29(30),
    MAX30(31),
    MAX31(32),
    MAX32(33),
    MAX33(34),
    MAX34(35),
    MAX35(36),
    MAX36(37),
    MAX37(38),
    MAX38(39),
    MAX39(40),
    MAX40(41),
    MAX41(42),
    MAX42(43),
    MAX43(44),
    MAX44(45),
    MAX45(46),
    MAX46(47),
    MAX47(48),
    MAX48(49),
    MAX49(50),
    MAX50(51),
    MAX51(52),
    MAX52(53),
    MAX53(54),
    MAX54(55),
    MAX55(56),
    MAX56(57),
    MAX57(58),
    MAX58(59),
    MAX59(60),
    MAX60(61),
    MAX61(62),
    MAX62(63),
    MAX63(64),
    MAX64(65);

    private final int damage;
    private final Component label;

    ModifiedWAttackDamage(int damage) {
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
