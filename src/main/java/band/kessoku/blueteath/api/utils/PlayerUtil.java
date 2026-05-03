package band.kessoku.blueteath.api.utils;

import band.kessoku.blueteath.common.BTConfig;
import band.kessoku.blueteath.common.attachments.BTAttachments;
import band.kessoku.blueteath.common.items.BTItems;
import band.kessoku.blueteath.common.items.transceiver.Transceiver;
import band.kessoku.blueteath.common.items.transceiver.TransceiverTier;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NullMarked;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.function.Function;

@NullMarked
public final class PlayerUtil {

    public static boolean connectable(Player f, Player s) {
        if (f.isDeadOrDying() || s.isDeadOrDying()) return false;
        if (isConnecting(f, s)) return false; // Already connected is unconnectable
        if (calculateDistance(f, s) == -1.0) {
            return hasAdapter(f) || hasAdapter(s);
        }
        return checkDistance(f, s);
    }

    public static boolean isConnecting(Player f, Player s) {
        if (f.hasData(BTAttachments.BLUETEATH_HANDLER) && s.hasData(BTAttachments.BLUETEATH_HANDLER)) {
            var fHandler = f.getData(BTAttachments.BLUETEATH_HANDLER);
            var sHandler = s.getData(BTAttachments.BLUETEATH_HANDLER);
            return fHandler.isConnecting(s.getUUID()) && sHandler.isConnecting(f.getUUID());
        }
        return false;
    }

    public static double calculateDistance(Player f, Player s) {
        if (f.getDimensions(f.getPose()).equals(s.getDimensions(s.getPose()))) return -1.0;
        var pos1 = f.getOnPos();
        var pos2 = s.getOnPos();
        return distance(
                pos1.getX(), pos1.getY(), pos1.getZ(),
                pos2.getX(), pos2.getY(), pos2.getZ()
        );
    }

    public static boolean checkDistance(Player f, Player s) {
        if (BTConfig.SERVER_CONFIG.defaultDistance.getAsInt() == 0) return true;
        return getTier(f).getDistance() + getTier(s).getDistance() >= calculateDistance(f, s);
    }

    public static boolean hasTransceiver(Player player) {
        return isEquipped(player, (handler) -> handler.isEquipped(stack -> stack.getItem() instanceof Transceiver));
    }

    public static boolean hasGlasses(Player player) {
        return isEquipped(player, (handler) -> handler.isEquipped(stack -> stack.is(BTItems.BLUETEATH_GLASSES.asItem())));
    }

    public static boolean hasAdapter(Player player) {
        return isEquipped(player, (handler) -> handler.isEquipped(stack -> stack.is(BTItems.DIMENSION_BLUETEATH_ADAPTER.asItem())));
    }

    public static TransceiverTier getTier(Player player) {
        if (!hasTransceiver(player)) return TransceiverTier.NONE;
        if (CuriosApi.getCuriosInventory(player).isPresent()) {
            var handler = CuriosApi.getCuriosInventory(player).get();
            var optional = handler.findFirstCurio(stack -> stack.getItem() instanceof Transceiver);
            if (optional.isPresent()) {
                var result = optional.get();
                return result.stack().getItem() instanceof Transceiver transceiver ? transceiver.getTier() : TransceiverTier.NONE;
            }
        }
        return TransceiverTier.NONE;
    }

    private static boolean isEquipped(Player player, Function<ICuriosItemHandler, Boolean> function) {
        if (CuriosApi.getCuriosInventory(player).isPresent()) {
            return function.apply(CuriosApi.getCuriosInventory(player).get());
        }
        return false;
    }

    private static double distance(
            double x1, double y1, double z1,
            double x2, double y2, double z2) {

        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;

        return Math.hypot(Math.hypot(dx, dy), dz);
    }

}
