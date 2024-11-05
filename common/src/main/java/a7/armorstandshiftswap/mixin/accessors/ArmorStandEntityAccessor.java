package a7.armorstandshiftswap.mixin.accessors;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorStandEntity.class)
public interface ArmorStandEntityAccessor {
    @Invoker("isSlotDisabled")
    boolean asss$isSlotDisabled(EquipmentSlot slot);
}
