package a7.armorstandshiftswap.fabric;

import net.fabricmc.api.ModInitializer;

import a7.armorstandshiftswap.fabriclike.ArmorStandShiftSwapFabricLike;

public final class ArmorStandShiftSwapFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run the Fabric-like setup.
        ArmorStandShiftSwapFabricLike.init();
    }
}
