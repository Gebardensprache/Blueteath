package band.kessoku.blueteath.client.key;

import band.kessoku.blueteath.Blueteath;
import band.kessoku.blueteath.client.screen.BlueteathSettingScreen;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Blueteath.MOD_ID)
public class BTKeyMappings {

    public static final KeyMapping.Category CATEGORY = new KeyMapping.Category(Blueteath.asId("blueteath_keybinds"));

    public static final KeyMapping OPEN_SETTING_SCREEN = new KeyMapping(
            "blueteath.key.open_setting_screen",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_S,
            CATEGORY
    );

    @SubscribeEvent
    public static void registerKeyMapping(RegisterKeyMappingsEvent event) {
        event.registerCategory(CATEGORY);

        event.register(OPEN_SETTING_SCREEN);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getKey() == OPEN_SETTING_SCREEN.getKey().getValue() && event.getAction() == InputConstants.RELEASE) {
            Minecraft.getInstance().pushGuiLayer(new BlueteathSettingScreen());
        }
    }

}
