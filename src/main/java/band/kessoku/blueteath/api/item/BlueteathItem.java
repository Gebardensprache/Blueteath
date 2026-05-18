package band.kessoku.blueteath.api.item;

import band.kessoku.blueteath.api.data.BlueteathData;
import band.kessoku.blueteath.api.data.BlueteathModuleData;
import band.kessoku.blueteath.client.menu.BlueteathMenu;
import band.kessoku.blueteath.common.components.BTDataComponentTypes;
import eu.pb4.trinkets.api.callback.TrinketCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class BlueteathItem extends Item implements TrinketCallback {
    public BlueteathItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        BlueteathData data = stack.get(BTDataComponentTypes.BLUETEATH);
        if (data != null) {
            Entity connectingObject = level.getEntity(data.getConnectingObject());
            for (BlueteathModuleData module : data.getModules()) {
                Optional<Holder.Reference<Item>> optionalHolder = BuiltInRegistries.ITEM.get(module.getId());
                optionalHolder.ifPresent(itemReference -> {
                    if (itemReference.value() instanceof BlueteathModuleItem moduleItem) {
                        moduleItem.moduleTick(stack, level, owner, connectingObject);
                    }
                });
            }
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            BlueteathData data = stack.get(BTDataComponentTypes.BLUETEATH);

            if (data != null) {
                serverPlayer.openMenu(new SimpleMenuProvider(
                        (containerId, inv, _) -> new BlueteathMenu(containerId, inv, data, stack),
                        Component.literal("蓝牙配置界面")
                ), buf -> BlueteathData.STREAM_CODEC.encode(buf, data));
            }
        }

        return InteractionResult.SUCCESS;
    }
}
