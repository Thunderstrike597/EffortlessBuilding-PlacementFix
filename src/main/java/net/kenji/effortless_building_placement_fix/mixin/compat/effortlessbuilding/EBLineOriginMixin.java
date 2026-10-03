package net.kenji.effortless_building_placement_fix.mixin.compat.effortlessbuilding;


import net.kenji.effortless_building_placement_fix.api.CameraAim;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import nl.requios.effortlessbuilding.buildmode.buildmodes.Line;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(value = Line.class, remap = false)
public abstract class EBLineOriginMixin {
    @Redirect(method = "findLine",
        at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/world/phys/Vec3;", ordinal = 0),
        remap = false)
    private static Vec3 camera$origin(double x, double y, double z) {
        Player p = Minecraft.getInstance().player;
        CameraAim.Ray r = p == null ? null : CameraAim.get(p);
        return r != null ? r.origin() : new Vec3(x, y, z);
    }
}