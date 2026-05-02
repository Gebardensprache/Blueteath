package band.kessoku.blueteath.common.attachments;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public class BlueteathSyncHandler implements AttachmentSyncHandler<BlueteathHandler> {
    @Override
    public void write(RegistryFriendlyByteBuf buf, BlueteathHandler attachment, boolean initialSync) {
        BlueteathHandler.STREAM_CODEC.encode(buf, attachment);
    }

    @Override
    public @Nullable BlueteathHandler read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable BlueteathHandler previousValue) {
        return BlueteathHandler.STREAM_CODEC.decode(buf);
    }

    @Override
    public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer to) {
        return holder == to;
    }
}
