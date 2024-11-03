package a7.armorstandshiftswap.fabriclike;

import a7.armorstandshiftswap.ArmorStandShiftSwap;
import a7.armorstandshiftswap.CommonHandler;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;

public final class ArmorStandShiftSwapFabricLike {
    public static void init() {
        ArmorStandShiftSwap.init();

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) ->
                CommonHandler.entityInteract(
                        player, hand, entity));
    }
}
