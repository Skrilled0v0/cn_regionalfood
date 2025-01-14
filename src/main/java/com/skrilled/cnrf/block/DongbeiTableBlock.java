package com.skrilled.cnrf.block;

import com.skrilled.cnrf.registry.ItemsRegistry;
import com.skrilled.cnrf.registry.SoundsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DongbeiTableBlock extends Block {

    public DongbeiTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (world.getBlockState(pos.up()).isAir()) {
                if (player.getStackInHand(hand).isOf(ItemsRegistry.PLATE_OF_DONGBEIJIAOZI.get())) {
                    player.setStackInHand(hand, new ItemStack(ItemsRegistry.PLATE.get()));
                    world.playSound(null, pos, SoundsRegistry.DONGBEI.getSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
            }
        }
        return ActionResult.SUCCESS; // Indicate the interaction was handled
    }
}
