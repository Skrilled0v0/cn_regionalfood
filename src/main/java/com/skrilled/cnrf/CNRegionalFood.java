package com.skrilled.cnrf;

import com.skrilled.cnrf.registry.BlocksRegistry;
import com.skrilled.cnrf.registry.ItemsRegistry;
import com.skrilled.cnrf.registry.SoundsRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CNRegionalFood implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("CN Regional Food");

    public static final String MOD_ID = "cnregionalfood";

    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "main"));

    @Override
    public void onInitialize() {
        ItemsRegistry.initialize();
        BlocksRegistry.initialize();
        SoundsRegistry.initialize();

        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ItemsRegistry.TIANJIN_JIANBING.get()))
                .displayName(Text.translatable("itemGroup.cnregionalfood.main"))
                .build());

        ItemsRegistry.registerItems();
        BlocksRegistry.registerBlocks();
        SoundsRegistry.registerSounds();
    }
}