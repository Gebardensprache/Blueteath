package band.kessoku.blueteath.common.items;

import band.kessoku.blueteath.Blueteath;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BTItems {

    private static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Blueteath.MOD_ID);
    public static final DeferredItem<Item> LOGO =
            ITEMS.registerSimpleItem("logo", () -> new Item.Properties().stacksTo(1));
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Blueteath.MOD_ID);
    public static final Supplier<CreativeModeTab> BLUETEATH_TAB =
            CREATIVE_MODE_TAB.register("blueteath_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.blueteath"))
                    .icon(LOGO::toStack)
                    .displayItems((param, output) -> {
                    })
                    .build());

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        CREATIVE_MODE_TAB.register(bus);
    }

}
