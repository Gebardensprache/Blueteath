package band.kessoku.blueteath.common.module;

import band.kessoku.blueteath.api.item.BlueteathModuleItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class DimensionBlueteathAdapter extends BlueteathModuleItem {
    public DimensionBlueteathAdapter(Properties properties) {
        super(properties);
    }

    @Override
    public void moduleTick(ItemStack stack, ServerLevel level, Entity owner, Entity object) {

    }
}
