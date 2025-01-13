package com.skrilled.cnrf.registry;

import com.skrilled.cnrf.CNRegionalFood;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.skrilled.cnrf.CNRegionalFood.MOD_ID;

public enum ItemsRegistry {

    TIANJIN_JIANBING("tianjin_jianbing", () -> new Item(new FabricItemSettings().food(
                    new FoodComponent.Builder()
                            .hunger(5)
                            .saturationModifier(0.4F)
                            .build())
            .maxCount(64)
    )),
    DONGBEI_ZHUOJIAO("dongbei_zhuojiao", () -> new Item(new FabricItemSettings().food(
                    new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(0.2F)
                            // 添加反胃效果，概率为20%
                            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), 0.2F)
                            .build())
            .maxCount(64)
    ));


    private final String name;
    private final Supplier<Item> itemSupplier;
    private Item item;

    ItemsRegistry(String name, Supplier<Item> item) {
        this.name = name;
        this.itemSupplier = item;
    }

    public static void initialize() {

    }

    public static void registerItems() {
        Arrays.stream(ItemsRegistry.values()).forEach(item -> {
            Registry.register(Registries.ITEM, new Identifier(MOD_ID, item.name), item.get());
            ItemGroupEvents.modifyEntriesEvent(CNRegionalFood.ITEM_GROUP).register(entries -> entries.add(item.get()));
        });
    }

    public Item get() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    public String getId() {
        return Registries.ITEM.getId(get()).toString();
    }
}
