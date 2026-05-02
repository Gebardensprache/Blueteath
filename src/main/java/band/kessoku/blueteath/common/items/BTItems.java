package band.kessoku.blueteath.common.items;

import band.kessoku.blueteath.Blueteath;
import band.kessoku.blueteath.common.items.transceiver.Transceiver;
import band.kessoku.blueteath.common.items.transceiver.TransceiverTier;
import band.kessoku.blueteath.common.items.trinkets.BlueteathGlasses;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemLore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class BTItems {

    private static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Blueteath.MOD_ID);

    public static final DeferredItem<Item> LOGO =
            ITEMS.registerSimpleItem("logo", () -> new Item.Properties().stacksTo(1));

    public static final DeferredItem<BlueteathGlasses> BLUETEATH_GLASSES =
            ITEMS.register("blueteath_glasses", BlueteathGlasses::new);

    public static final DeferredItem<Transceiver> COPPER = ITEMS.register(TransceiverTier.COPPER.getRegistryName(), TransceiverTier.COPPER::create);
    public static final DeferredItem<Transceiver> IRON = ITEMS.register(TransceiverTier.IRON.getRegistryName(), TransceiverTier.IRON::create);
    public static final DeferredItem<Transceiver> GOLDEN = ITEMS.register(TransceiverTier.GOLDEN.getRegistryName(), TransceiverTier.GOLDEN::create);
    public static final DeferredItem<Transceiver> DIAMOND = ITEMS.register(TransceiverTier.DIAMOND.getRegistryName(), TransceiverTier.DIAMOND::create);
    public static final DeferredItem<Transceiver> NETHERITE = ITEMS.register(TransceiverTier.NETHERITE.getRegistryName(), TransceiverTier.NETHERITE::create);

    public static final DeferredItem<Item> DIMENSION_BLUETEATH_ADAPTER =
            ITEMS.registerSimpleItem(
                    "dimension_blueteath_adapter",
                    () -> new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .component(DataComponents.LORE, new ItemLore(
                                    List.of(Component.translatable("item.blueteath.dimension_blueteath_adapter.tooltip")))
                            )
            );

    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Blueteath.MOD_ID);

    public static final Supplier<CreativeModeTab> BLUETEATH_TAB =
            CREATIVE_MODE_TAB.register("blueteath_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.blueteath"))
                    .icon(LOGO::toStack)
                    .displayItems((_, output) -> {
                        output.accept(BLUETEATH_GLASSES);
                        output.accept(COPPER);
                        output.accept(IRON);
                        output.accept(GOLDEN);
                        output.accept(DIAMOND);
                        output.accept(NETHERITE);
                        output.accept(DIMENSION_BLUETEATH_ADAPTER);
                    })
                    .build());

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        CREATIVE_MODE_TAB.register(bus);
    }

}
