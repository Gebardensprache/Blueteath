package band.kessoku.blueteath.client.menu;

import band.kessoku.blueteath.api.data.BlueteathData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class BlueteathMenu extends AbstractContainerMenu {
    public final BlueteathData data;
    public final ItemStack heldItemStack;

    public BlueteathMenu(int windowId, Inventory inv, BlueteathData data, ItemStack stack) {
        super(null, windowId);
        this.data = data;
        this.heldItemStack = stack;
    }

    public BlueteathMenu(int windowId, Inventory inv, FriendlyByteBuf buf) {
        super(null, windowId);
        this.data = BlueteathData.STREAM_CODEC.decode(buf);
        this.heldItemStack = ByteBufCodecs.fromCodec(ItemStack.CODEC).decode(buf);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
