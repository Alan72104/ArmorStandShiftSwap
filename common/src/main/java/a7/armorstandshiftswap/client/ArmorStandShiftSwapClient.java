package a7.armorstandshiftswap.client;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class ArmorStandShiftSwapClient {
    public static KeyBinding KEYBINDING_SWAP = null;

    public static void registerKeyBinds(Consumer<KeyBinding> registrar) {
        KEYBINDING_SWAP = new KeyBinding(
                "key.armorstandshiftswap.swap",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "key.categories.armorstandshiftswap.main"
        );
        registrar.accept(KEYBINDING_SWAP);
    }
}
