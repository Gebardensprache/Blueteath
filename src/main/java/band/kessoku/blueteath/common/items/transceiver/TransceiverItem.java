package band.kessoku.blueteath.common.items.transceiver;

import band.kessoku.blueteath.Blueteath;
import band.kessoku.blueteath.api.data.BlueteathData;
import band.kessoku.blueteath.api.item.BlueteathItem;
import band.kessoku.blueteath.common.components.BTDataComponentTypes;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.component.ItemLore;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class TransceiverItem extends BlueteathItem implements ICurioItem {

    public static final TagKey<Item> TRANSCEIVERS = TagKey.create(Registries.ITEM, Blueteath.asId("transceivers"));

    private final TransceiverTier tier;

    public TransceiverItem(Properties properties, TransceiverTier tier) {
        super(properties
                .component(DataComponents.LORE,
                        new ItemLore(List.of(Component.translatable("item.blueteath.transceiver.tooltip", tier.getDistance()))))
                .component(DataComponents.ITEM_NAME,
                        Component.translatable("item.blueteath.transceiver.name", tier.getTranslation()))
                .component(BTDataComponentTypes.BLUETEATH,
                        new BlueteathData(Blueteath.asId(tier.getRegistryName()), tier.getMaxModuleAmount()))
                .rarity(tier.getRarity()));
        this.tier = tier;
    }

    public TransceiverTier getTier() {
        return tier;
    }
}
