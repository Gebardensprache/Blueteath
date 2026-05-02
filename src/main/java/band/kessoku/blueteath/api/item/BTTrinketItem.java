package band.kessoku.blueteath.api.item;

import eu.pb4.trinkets.api.callback.TrinketCallback;
import net.minecraft.world.item.Item;

public class BTTrinketItem extends Item implements TrinketCallback {
    public BTTrinketItem(Properties properties) {
        super(properties.stacksTo(1));
    }
}
