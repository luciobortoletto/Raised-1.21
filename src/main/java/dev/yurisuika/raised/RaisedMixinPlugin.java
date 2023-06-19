package dev.yurisuika.raised;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class RaisedMixinPlugin implements IMixinConfigPlugin {

    private static final Map<String, Supplier<Boolean>> BOTANIA = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.BotaniaMixin$HUDHandlerMixin", () -> FMLLoader.getLoadingModList().getModFileById("botania") != null,
            "dev.yurisuika.raised.mixin.mods.BotaniaMixin$ItemFlightTiaraMixin", () -> FMLLoader.getLoadingModList().getModFileById("botania") != null
    );

    private static final Map<String, Supplier<Boolean>> CREATE = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.CreateMixin$RemainingAirOverlayMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$SchematicHotbarSlotOverlayMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$ToolboxHandlerClientMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$ToolSelectionScreenMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$TrainHUDMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null
    );

    private static final Map<String, Supplier<Boolean>> DETAILARMORBAR = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.DetailArmorBarMixin$ArmorBarRendererMixin", () -> FMLLoader.getLoadingModList().getModFileById("detailab") != null
    );

    private static final Map<String, Supplier<Boolean>> HEALTHOVERLAY = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.HealthOverlayMixin$HeartRendererMixin", () -> FMLLoader.getLoadingModList().getModFileById("healthoverlay") != null
    );

    private static final Map<String, Supplier<Boolean>> INVENTORIO = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.InventorioMixin$HotbarHUDRendererMixin", () -> FMLLoader.getLoadingModList().getModFileById("inventorio") != null
    );

    private static final Map<String, Supplier<Boolean>> INVENTORYPROFILESNEXT = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.InventoryProfilesNextMixin$LockSlotsHandlerMixin", () -> FMLLoader.getLoadingModList().getModFileById("inventoryprofilesnext")  != null
    );

    private static final Map<String, Supplier<Boolean>> LEVELHEARTS = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.LevelHeartsMixin$IngameGuiMixin", () -> FMLLoader.getLoadingModList().getModFileById("levelhearts") != null
    );

    private static final Map<String, Supplier<Boolean>> QUARK = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.QuarkMixin$HotbarChangerModuleMixin", () -> FMLLoader.getLoadingModList().getModFileById("quark") != null,
            "dev.yurisuika.raised.mixin.mods.QuarkMixin$UsageTickerModuleMixin", () -> FMLLoader.getLoadingModList().getModFileById("quark") != null
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return BOTANIA.getOrDefault(mixinClassName, () -> true).get() && CREATE.getOrDefault(mixinClassName, () -> true).get() && DETAILARMORBAR.getOrDefault(mixinClassName, () -> true).get() && HEALTHOVERLAY.getOrDefault(mixinClassName, () -> true).get() && INVENTORIO.getOrDefault(mixinClassName, () -> true).get() && INVENTORYPROFILESNEXT.getOrDefault(mixinClassName, () -> true).get() && LEVELHEARTS.getOrDefault(mixinClassName, () -> true).get() && QUARK.getOrDefault(mixinClassName, () -> true).get();
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

}