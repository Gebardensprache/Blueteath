package band.kessoku.blueteath.api.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class BlueteathModuleItem extends Item {
    public BlueteathModuleItem(Properties properties) {
        super(properties);
    }

    public abstract void moduleTick(ItemStack stack, ServerLevel level, Entity owner, Entity object);
}
