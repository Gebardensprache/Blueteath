package band.kessoku.blueteath.mixins.minecraft;

import band.kessoku.blueteath.api.utils.PlayerUtil;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    @Nullable
    public LocalPlayer player;

    @ModifyExpressionValue(
            method = "shouldEntityAppearGlowing",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isCurrentlyGlowing()Z")
    )
    private boolean blueteach$glowingConnectingPlayer(boolean original, @Local(argsOnly = true, name = "entity") Entity entity) {
        if (entity instanceof Player target && player != null) {
            return original || (PlayerUtil.isConnecting(player, target) && PlayerUtil.hasGlasses(player));
        }
        return original;
    }

}
