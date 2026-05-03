package band.kessoku.blueteath.common;

import band.kessoku.blueteath.Blueteath;
import band.kessoku.blueteath.common.attachments.BTAttachments;
import band.kessoku.blueteath.common.attachments.BlueteathHandler;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Blueteath.MOD_ID)
@SuppressWarnings("unused")
public final class BTEventHandler {

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(BTAttachments.BLUETEATH_HANDLER)) {
            BlueteathHandler handler = event.getOriginal().getData(BTAttachments.BLUETEATH_HANDLER);

            var player = handler.getConnectedPlayer(event.getOriginal().level());
            if (player != null && player.hasData(BTAttachments.BLUETEATH_HANDLER)) {
                player.getData(BTAttachments.BLUETEATH_HANDLER).disconnect(); // Reset blueteath connect
                player.sendOverlayMessage(Component.translatable("blueteath.message.disconnected"));
            }

            handler.disconnect(); // Reset blueteath connect
            event.getEntity().setData(BTAttachments.BLUETEATH_HANDLER, handler);
        }
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        event.getEntity().getExistingData(BTAttachments.BLUETEATH_HANDLER)
                .ifPresent(handler -> handler.tick(event.getEntity()));
    }

}
