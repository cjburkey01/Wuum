package com.cjburkey.wuum.event;

import com.cjburkey.wuum.ModInfo;
import com.cjburkey.wuum.Wuum;
import com.cjburkey.wuum.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ModInfo.modid)
public class EventPlayerRightClickEntity {
    
    @SubscribeEvent
    public static void onEvent(PlayerInteractEvent.EntityInteract e) {
        if (!e.getWorld().isRemote) {
            handleHand(e.getItemStack(), e.getEntityPlayer(), e.getTarget());
        }
    }
    
    private static void handleHand(ItemStack stack, EntityPlayer player, Entity entity) {
        if (stack == null || stack.isEmpty() || !stack.getItem().equals(ModItems.itemEntityCollector) || player == null || entity == null || !(entity instanceof EntityLiving) || entity.isDead) {
            return;
        }
        ModItems.itemEntityCollector.addEntity(stack, player, (EntityLiving) entity);
    }
    
}