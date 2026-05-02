package band.kessoku.blueteath.api.utils;

import band.kessoku.blueteath.common.attachments.BTAttachments;
import net.minecraft.world.entity.player.Player;

public final class PlayerUtil {

    public static boolean isConnecting(Player f, Player s) {
        if (f != null && s != null) {
            if (f.hasData(BTAttachments.BLUETEATH_HANDLER) && s.hasData(BTAttachments.BLUETEATH_HANDLER)) {
                var fHandler = f.getData(BTAttachments.BLUETEATH_HANDLER);
                var sHandler = s.getData(BTAttachments.BLUETEATH_HANDLER);
                return fHandler.isConnecting(s.getUUID()) && sHandler.isConnecting(f.getUUID());
            }
        }
        return false;
    }

}
