package a7.armorstandshiftswap.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import a7.armorstandshiftswap.fabriclike.ArmorStandShiftSwapFabricLike;

public final class ArmorStandShiftSwapQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Run the Fabric-like setup.
        ArmorStandShiftSwapFabricLike.init();
    }
}
