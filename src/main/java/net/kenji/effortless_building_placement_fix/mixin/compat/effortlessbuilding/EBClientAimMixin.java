package net.kenji.effortless_building_placement_fix.mixin.compat.effortlessbuilding;

import neoforge.nl.requios.effortlessbuilding.buildpipeline.BuildPipelineClient;
import net.kenji.effortless_building_placement_fix.api.CameraAim;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = BuildPipelineClient.class, remap = false)
public abstract class EBClientAimMixin {
    @Redirect(method = {"handleClick", "getPreviewBlocks"},
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;getEyePosition()Lnet/minecraft/world/phys/Vec3;")
    )
    private static Vec3 aimOrigin(Player p) {
        var r = CameraAim.get(p);
        return r != null ? r.origin() : p.getEyePosition();
    }

    @Redirect(method = {"handleClick", "getPreviewBlocks"},
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;getLookAngle()Lnet/minecraft/world/phys/Vec3;"))
    private static Vec3 aimDir(Player p) {
        var r = CameraAim.get(p);
        return r != null ? r.dir() : p.getLookAngle();
    }
}