package band.kessoku.blueteath.api.item;

import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BTTrinketItem extends Item implements ICurioItem {
    public BTTrinketItem(Properties properties) {
        super(properties.stacksTo(1));
    }
}
