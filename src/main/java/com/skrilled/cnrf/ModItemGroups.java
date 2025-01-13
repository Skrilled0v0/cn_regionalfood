package com.skrilled.cnrf;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TEST_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier("test", "test_group"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.TEST_ITEM))
            .displayName(Text.translatable("itemGroup.cnregionalfood.test_group"))
            .entries((context, entries) -> {
                entries.add(ModItems.TEST_ITEM);
            })
            .build());

    public static void init() {
    }
}

