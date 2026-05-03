package band.kessoku.blueteath.api.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerConnectingEvent extends PlayerEvent {
    private final Player target;

    public PlayerConnectingEvent(Player player, Player target) {
        super(player);
        this.target = target;
    }

    public Player getTarget() {
        return target;
    }
}
