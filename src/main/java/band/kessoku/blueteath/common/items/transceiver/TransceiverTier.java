package band.kessoku.blueteath.common.items.transceiver;

import band.kessoku.blueteath.common.BTConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public enum TransceiverTier {
    NONE(Rarity.COMMON),
    COPPER(Rarity.COMMON),
    IRON(Rarity.COMMON),
    GOLDEN(Rarity.UNCOMMON),
    DIAMOND(Rarity.RARE),
    NETHERITE(Rarity.EPIC);

    private final Rarity rarity;

    TransceiverTier(Rarity rarity) {
        this.rarity = rarity;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getDistance() {
        return BTConfig.SERVER_CONFIG.getDistance(this);
    }

    public Component getTranslation() {
        return Component.translatable("item.blueteath.transceiver.tier.%s".formatted(this.name().toLowerCase()));
    }

    public String getRegistryName() {
        return this.name().toLowerCase() + "_blueteath_transceiver";
    }

    public Transceiver create(Item.Properties properties) {
        return new Transceiver(properties, this);
    }

    public boolean isNone() {
        return this == NONE;
    }
}
