package band.kessoku.blueteath.common.attachments;

import band.kessoku.blueteath.Blueteath;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class BTAttachments {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Blueteath.MOD_ID);

    public static final Supplier<AttachmentType<BlueteathHandler>> BLUETEATH_HANDLER = ATTACHMENTS.register(
            "blueteath_handler",
            () -> AttachmentType.serializable(BlueteathHandler::new)
                    .sync(new BlueteathSyncHandler())
                    .build()
    );

    public static void register(IEventBus bus) {
        ATTACHMENTS.register(bus);
    }

}
