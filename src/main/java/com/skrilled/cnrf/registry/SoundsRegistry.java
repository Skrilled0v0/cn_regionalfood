package com.skrilled.cnrf.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.Arrays;

import static com.skrilled.cnrf.CNRegionalFood.MOD_ID;

public enum SoundsRegistry {

    DONGBEI("dongbei");

    private final String name;
    private final Identifier identifier;

    SoundsRegistry(String name) {
        this.name = name;
        this.identifier = new Identifier(MOD_ID, name);
    }

    public static void initialize() {

    }

    public static void registerSounds() {
        Arrays.stream(values()).forEach(sound -> {
            Registry.register(Registries.SOUND_EVENT, sound.identifier, sound.getSound());
        });
    }


    public String getName() {
        return name;
    }

    public SoundEvent getSound() {
        return SoundEvent.of(identifier);
    }
}
