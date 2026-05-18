package band.kessoku.blueteath.common.items.transceiver;

import band.kessoku.blueteath.common.BTConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public enum TransceiverTier {
    NONE(Rarity.COMMON, 1),
    COPPER(Rarity.COMMON, 2),
    IRON(Rarity.COMMON, 4),
    GOLDEN(Rarity.UNCOMMON, 8),
    DIAMOND(Rarity.RARE, 16),
    NETHERITE(Rarity.EPIC, 32);

    private final Rarity rarity;
    private final int distance;

    TransceiverTier(Rarity rarity, int distance) {
        this.rarity = rarity;
        this.distance = distance;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public int getDistance() {
        if (BTConfig.SERVER_CONFIG_SPEC.isLoaded())
            return BTConfig.SERVER_CONFIG.getDistance(this);
        return this.distance;
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
