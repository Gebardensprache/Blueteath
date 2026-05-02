package band.kessoku.blueteath.common.attachments;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

@NullMarked
public class BlueteathHandler implements ValueIOSerializable {

    public static StreamCodec<ByteBuf, BlueteathHandler> STREAM_CODEC = ByteBufCodecs.TAG.map(
            BlueteathHandler::encode,
            BlueteathHandler::decode
    );

    private @Nullable UUID connecting;

    public BlueteathHandler() {
    }

    private static BlueteathHandler encode(Tag tag) {
        BlueteathHandler handler = new BlueteathHandler();
        if (tag instanceof IntArrayTag intArrayTag) {
            if (intArrayTag.getAsIntArray().length == 4) {
                handler.connect(UUIDUtil.uuidFromIntArray(intArrayTag.getAsIntArray()));
            }
        }
        return handler;
    }

    public void connect(UUID uuid) {
        connecting = uuid;
    }

    public void disconnect() {
        connecting = null;
    }

    public @Nullable Player getConnectedPlayer(Level world) {
        if (connecting == null) return null;
        return world.getPlayerByUUID(connecting);
    }

    @Override
    public void serialize(ValueOutput output) {
        if (connecting != null) {
            output.putIntArray("Connecting", UUIDUtil.uuidToIntArray(connecting));
        }
    }

    @Override
    public void deserialize(ValueInput input) {
        input.getIntArray("Connecting").ifPresent(intArray -> connecting = UUIDUtil.uuidFromIntArray(intArray));
    }

    public Tag decode() {
        return new IntArrayTag(connecting != null ? UUIDUtil.uuidToIntArray(connecting) : new int[0]);
    }
}
