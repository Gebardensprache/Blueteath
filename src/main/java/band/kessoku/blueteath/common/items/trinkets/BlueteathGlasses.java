package band.kessoku.blueteath.common.items.trinkets;

import eu.pb4.trinkets.api.callback.TrinketCallback;
import net.minecraft.world.item.Item;

public class BlueteathGlasses extends Item implements TrinketCallback {
    public BlueteathGlasses() {
        super(new Properties().stacksTo(1));
    }
}
