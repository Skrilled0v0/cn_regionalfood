package com.skrilled.cnrf.registry;

import com.skrilled.cnrf.CNRegionalFood;
import com.skrilled.cnrf.block.DongbeiTableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.skrilled.cnrf.CNRegionalFood.MOD_ID;
import static net.minecraft.block.Blocks.register;

public enum BlocksRegistry {

    DONGBEI_TABLE("dongbei_table", () -> new DongbeiTableBlock(FabricBlockSettings.copy(Blocks.GLASS)));

    private final String name;
    private final Supplier<Block> blockSupplier;
    private Block block;

    BlocksRegistry(String name, Supplier<Block> blockSupplier) {
        this.name = name;
        this.blockSupplier = blockSupplier;
    }

    public static void initialize() {

    }

    public static void registerBlocks() {
        Arrays.stream(values()).forEach(block -> {
            Registry.register(Registries.BLOCK, new Identifier(MOD_ID, block.name), block.get());
            Registry.register(Registries.ITEM, new Identifier(MOD_ID, block.name), new BlockItem(block.get(), new FabricItemSettings()));
            ItemGroupEvents.modifyEntriesEvent(CNRegionalFood.ITEM_GROUP).register(entries -> entries.add(block.get().asItem()));
        });
    }

    public Block get() {
        if (block == null) {
            block = blockSupplier.get();
        }
        return block;
    }

    public String getId() {
        return Registries.BLOCK.getId(get()).toString();
    }
}
