package net.kenji.effortless_building_placement_fix;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CompatMixinPlugin implements IMixinConfigPlugin {



    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();



        return mixins;
    }

    public boolean isEffortlessBuildngPresent(){
        return EffortlessBuildingPlacementFix.isLoaded("effortlessbuilding");
    }

    public boolean isSophistocatedBuildngPresent(){
        return EffortlessBuildingPlacementFix.isLoaded("sophisticatedbuilding");
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {

        if(!isEffortlessBuildngPresent()){
            if(mixinClassName.endsWith("EBClientAimMixin")){
                return false;
            }
            if(mixinClassName.endsWith("EBLineOriginMixin")){
                return false;
            }
            if(mixinClassName.endsWith("EBLookVecMixin")){
                return false;
            }
        }
        if(!isSophistocatedBuildngPresent()){
            if(mixinClassName.endsWith("SBFarLookMixin")){
                return false;
            }
            if(mixinClassName.endsWith("SBLineOriginMixin")){
                return false;
            }
            if(mixinClassName.endsWith("SBLookVecMixin")){
                return false;
            }
        }
        return true;
    }

    // unused methods:
    @Override public void onLoad(String mixinPackage) {}
    @Override public String getRefMapperConfig() { return null; }
    @Override public void acceptTargets(Set<String> a, Set<String> b) {}
    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
}