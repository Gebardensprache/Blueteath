package band.kessoku.blueteath.api.utils;

import band.kessoku.blueteath.common.BTConfig;
import band.kessoku.blueteath.common.attachments.BTAttachments;
import band.kessoku.blueteath.common.items.BTItems;
import band.kessoku.blueteath.common.items.transceiver.Transceiver;
import band.kessoku.blueteath.common.items.transceiver.TransceiverTier;
import eu.pb4.trinkets.api.TrinketsApi;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Comparator;

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
        return TrinketsApi.getAttachment(player).isEquipped(Transceiver.TRANSCEIVERS);
    }

    public static boolean hasGlasses(Player player) {
        return TrinketsApi.getAttachment(player).isEquipped(BTItems.BLUETEATH_GLASSES.asItem());
    }

    public static boolean hasAdapter(Player player) {
        return TrinketsApi.getAttachment(player).isEquipped(BTItems.DIMENSION_BLUETEATH_ADAPTER.asItem());
    }

    public static TransceiverTier getTier(Player player) {
        if (!hasTransceiver(player)) return TransceiverTier.NONE;
        return TrinketsApi.getAttachment(player).getEquipped(stack -> stack.is(Transceiver.TRANSCEIVERS))
                .stream()
                .filter(tuple -> tuple.getB().getItem() instanceof Transceiver)
                .map(tuple -> (Transceiver) tuple.getB().getItem())
                .sorted(Comparator.comparing(Transceiver::getTier))
                .toList().getLast().getTier();
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
