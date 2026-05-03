package band.kessoku.blueteath.common.items.transceiver;

import band.kessoku.blueteath.common.BTConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public enum TransceiverTier {
    NONE(Rarity.COMMON, 1, 0),
    COPPER(Rarity.COMMON, 2, 1),
    IRON(Rarity.COMMON, 4, 2),
    GOLDEN(Rarity.UNCOMMON, 8, 3),
    DIAMOND(Rarity.RARE, 16, 4),
    NETHERITE(Rarity.EPIC, 32, 5);

    private final Rarity rarity;
    private final int distance;
    private final int maxModuleAmount;

    TransceiverTier(Rarity rarity, int distance, int maxModuleAmount) {
        this.rarity = rarity;
        this.distance = distance;
        this.maxModuleAmount = maxModuleAmount;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public int getMaxModuleAmount() {
        return maxModuleAmount;
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

    public TransceiverItem create(Item.Properties properties) {
        return new TransceiverItem(properties, this);
    }

    public boolean isNone() {
        return this == NONE;
    }
}
