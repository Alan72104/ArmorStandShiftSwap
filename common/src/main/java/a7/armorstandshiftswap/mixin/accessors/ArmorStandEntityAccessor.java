package a7.armorstandshiftswap.mixin.accessors;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorStand.class)
public interface ArmorStandEntityAccessor {
    @Invoker("isDisabled")
    boolean asss$isDisabled(EquipmentSlot slot);
}
