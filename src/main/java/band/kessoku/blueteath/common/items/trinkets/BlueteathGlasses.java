package band.kessoku.blueteath.common.items.trinkets;

import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BlueteathGlasses extends Item implements ICurioItem {
    public BlueteathGlasses(Properties properties) {
        super(properties.stacksTo(1));
    }
}
