package band.kessoku.blueteath.common.components;

import band.kessoku.blueteath.Blueteath;
import band.kessoku.blueteath.api.data.BlueteathData;
import band.kessoku.blueteath.api.data.BlueteathModuleData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BTDataComponentTypes {
    private static final DeferredRegister.DataComponents COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Blueteath.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlueteathData>> BLUETEATH = COMPONENTS.registerComponentType(
            "blueteath",
            builder -> builder
                    .persistent(BlueteathData.CODEC)
                    .networkSynchronized(BlueteathData.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlueteathModuleData>> BLUETEATH_MODULE = COMPONENTS.registerComponentType(
            "blueteath_module",
            builder -> builder
                    .persistent(BlueteathModuleData.CODEC)
                    .networkSynchronized(BlueteathModuleData.STREAM_CODEC)
    );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}
