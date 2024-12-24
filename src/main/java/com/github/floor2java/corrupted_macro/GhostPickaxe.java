package com.github.floor2java.corrupted_macro;

import com.github.floor2java.corrupted_macro.utils.ChatUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.Arrays;

import static com.github.floor2java.corrupted_macro.utils.ChatUtils.debug;
import static com.github.floor2java.corrupted_macro.utils.ChatUtils.str;

public class GhostPickaxe {

    Minecraft mc = Minecraft.getMinecraft();
    BlockPos bp = null;
    int tick = 0;

    private static final Block[] blacklistedBps = {
            Blocks.chest,
            Blocks.lever,
            Blocks.command_block,
            Blocks.skull,
            Blocks.trapped_chest};

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {

        mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;
        if (!isHoldingPickaxe()) return;
        if (Mouse.isButtonDown(1)) {
            if (getBlockLookingAt() == null) return;
            debug(System.currentTimeMillis());
            tick++;
            if (tick == 2) {
                tick = 0;
                if (!isBlacklisted(getBlockLookingAt()))
                    mc.theWorld.setBlockToAir(getBlockLookingAt());
            }
        }
    }


    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            if (event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem() instanceof ItemTool) {
                ItemTool tool = (ItemTool) event.entityPlayer.getHeldItem().getItem();
                if (tool.getToolClasses(event.entityPlayer.getHeldItem()).contains("pickaxe")) {
                    if (!isBlacklisted(getBlockLookingAt()))
                        event.setCanceled(true);
                }
            }
        }
    }


    public static BlockPos getBlockLookingAt() {
        Minecraft mc = Minecraft.getMinecraft();
        MovingObjectPosition mop = mc.thePlayer.rayTrace(6, 1.0F);
        if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            return mop.getBlockPos();
        }
        return null;
    }


    public static boolean isHoldingPickaxe() {
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack heldItem = mc.thePlayer.getHeldItem();

        if (heldItem != null) {
            Item item = heldItem.getItem();
            return item instanceof ItemTool && item.getToolClasses(heldItem).contains("pickaxe");
        }
        return false;
    }

    private static boolean isBlacklisted(BlockPos bp) {
        Block block = Minecraft.getMinecraft().theWorld.getBlockState(bp).getBlock();
        for (Block b : blacklistedBps) {
            if (block == b) return true;
        }
        return false;
    }

}
