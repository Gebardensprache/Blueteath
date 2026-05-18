package band.kessoku.blueteath.common.items.transceiver;

import band.kessoku.blueteath.Blueteath;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class Transceiver extends Item implements ICurioItem {

    public static final TagKey<Item> TRANSCEIVERS = TagKey.create(Registries.ITEM, Blueteath.asId("transceivers"));

    private final TransceiverTier tier;

    public Transceiver(Properties properties, TransceiverTier tier) {
        super(properties
                .stacksTo(1)
                .component(DataComponents.LORE,
                        new ItemLore(List.of(Component.translatable("item.blueteath.transceiver.tooltip", tier.getDistance())))
                )
                .component(DataComponents.ITEM_NAME,
                        Component.translatable("item.blueteath.transceiver.name", tier.getTranslation()))
                .rarity(tier.getRarity()));
        this.tier = tier;
    }

    public TransceiverTier getTier() {
        return tier;
    }
}
