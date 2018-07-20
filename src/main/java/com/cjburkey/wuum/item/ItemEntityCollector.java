package com.cjburkey.wuum.item;

import java.util.List;
import javax.annotation.Nullable;
import com.cjburkey.wuum.Wuum;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEntityCollector extends Item {
    
    public static final String typeKey = "entity_type";
    public static final String countKey = "entity_count";
    
    public ItemEntityCollector() {
        setMaxStackSize(1);
    }
    
    public void addEntity(ItemStack stack, EntityPlayer player, EntityLiving entity) {
        if (!hasEntity(stack)) {
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            stack.getTagCompound().setString(typeKey, EntityList.getKey(entity).toString());
            stack.getTagCompound().setInteger(countKey, 0);
        }
        if (getEntityClass(stack).equals(entity.getClass())) {
            incrementEntityCount(stack);
            entity.getEntityWorld().removeEntity(entity);
            entity.isDead = true;
        }
    }
    
    private void incrementEntityCount(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        if (!stack.getTagCompound().hasKey(countKey)) {
            stack.getTagCompound().setInteger(countKey, 1);
            return;
        }
        stack.getTagCompound().setInteger(countKey, stack.getTagCompound().getInteger(countKey) + 1);
    }
    
    public Class<? extends Entity> getEntityClass(ItemStack stack) {
        if (hasEntity(stack)) {
            return EntityList.getClass(getEntityKey(stack));
        }
        return null;
    }
    
    public ResourceLocation getEntityKey(ItemStack stack) {
        if (hasEntity(stack)) {
            return new ResourceLocation(stack.getTagCompound().getString(typeKey));
        }
        return null;
    }
    
    public int getEntityCount(ItemStack stack) {
        if (hasEntityCount(stack)) {
            return stack.getTagCompound().getInteger(countKey);
        }
        return 0;
    }
    
    public boolean hasEntity(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(typeKey);
    }
    
    private boolean hasEntityCount(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(countKey);
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        if (stack.hasTagCompound()) {
            if (!hasEntity(stack) || !hasEntityCount(stack)) {
                return;
            }
            NBTTagCompound nbt = stack.getTagCompound();
            String type = EntityList.getTranslationName(getEntityKey(stack));
            int count = nbt.getInteger(countKey);
            tooltip.add(TextFormatting.WHITE + "Type: " + TextFormatting.AQUA + type);
            tooltip.add(TextFormatting.WHITE + "Amount: " + TextFormatting.AQUA + count);
        }
    }
    
}