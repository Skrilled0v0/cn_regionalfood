package com.skrilled.cnrf.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class ZhuoJiaoBlock extends Block {

    public static final IntProperty BITES = IntProperty.of("bites", 0, 7);
    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(0, 0, 0, 16, 8, 16),
            Block.createCuboidShape(2, 0, 0, 16, 8, 16),
            Block.createCuboidShape(4, 0, 0, 16, 8, 16),
            Block.createCuboidShape(6, 0, 0, 16, 8, 16),
            Block.createCuboidShape(8, 0, 0, 16, 8, 16),
            Block.createCuboidShape(10, 0, 0, 16, 8, 16),
            Block.createCuboidShape(12, 0, 0, 16, 8, 16),
            Block.createCuboidShape(14, 0, 0, 16, 8, 16)
    };

    public ZhuoJiaoBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BITES, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, net.minecraft.world.BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(BITES)];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            int bites = state.get(BITES);
            if (bites < 7) {
                world.setBlockState(pos, state.with(BITES, bites + 1));
                player.getHungerManager().add(2, 0.2F);
            } else {
                world.removeBlock(pos, false);
            }
        }
        return ActionResult.SUCCESS;
    }
}
