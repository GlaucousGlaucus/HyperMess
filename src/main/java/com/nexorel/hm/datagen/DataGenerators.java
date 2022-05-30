package com.nexorel.hm.datagen;

import com.nexorel.hm.HyperMess;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = HyperMess.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new HMRecipes(generator));
            generator.addProvider(new HMLootTables(generator));
            HMBlockTags blockTags = new HMBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new HMItemTags(generator, blockTags, event.getExistingFileHelper()));
//            generator.addProvider(new HMBiomeTags(generator, event.getExistingFileHelper()));
//            generator.addProvider(new HMStructureSetTags(generator, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new HMBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new HMItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new HMLanguageProvider(generator, "en_us"));
        }
    }
}
