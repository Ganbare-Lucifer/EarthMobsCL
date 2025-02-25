package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
public class BlockstateGenerator extends BlockStateProvider {
    public BlockstateGenerator(PackOutput gen, ExistingFileHelper exFileHelper) {
        super(gen, EarthMobsMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.crossBlock(ModBlocks.BUTTERCUP.get());
        this.crossBlock(ModBlocks.PINK_DAISY.get());
        this.horizontalBlock(ModBlocks.CARVED_MELON.get(), ResourceLocation.parse("minecraft:block/melon_side"), ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "block/carved_melon"), ResourceLocation.parse("minecraft:block/melon_top"));
        this.horizontalBlock(ModBlocks.CARVED_MELON_SHOOT.get(), ResourceLocation.parse("minecraft:block/melon_side"), ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "block/carved_melon_shoot"), ResourceLocation.parse("minecraft:block/melon_top"));
        this.simpleBlock(ModBlocks.RUBY.get());
    }

    public void translucentBlock(Block block) {
        simpleBlock(block, translucentCubeAll(block));
    }

    private ModelFile translucentCubeAll(Block block) {
        return models().cubeAll(name(block), blockTexture(block)).renderType("minecraft:translucent");
    }


    public void crossBlock(Block block) {
        crossBlock(block, models().cross(name(block), texture(name(block))).renderType("minecraft:cutout"));
    }

    private void crossBlock(Block block, ModelFile model) {
        getVariantBuilder(block).forAllStates(state ->
                ConfiguredModel.builder()
                        .modelFile(model)
                        .build());
    }

    protected ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    protected String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
    }
}
