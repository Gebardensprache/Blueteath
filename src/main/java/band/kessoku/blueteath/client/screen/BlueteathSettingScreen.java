package band.kessoku.blueteath.client.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BlueteathSettingScreen extends Screen {
    public BlueteathSettingScreen() {
        super(Component.translatable("blueteath.screen.setting"));
    }

    @Override
    protected void init() {
        super.init(230, 219);
    }
}
