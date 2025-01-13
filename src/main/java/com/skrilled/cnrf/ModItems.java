package com.skrilled.cnrf;

import com.skrilled.cnrf.item.TestItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TEST_ITEM = register("cnregionalfood:test_item", new TestItem(new Item.Settings()));

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, new Identifier(name), item);
    }
    public static void init() {
    }
}
