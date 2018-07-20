package net.minecraft.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class _WuumEntityGetLoot {
    
    private static Random random;
    
    public static ResourceLocation getLootTable(EntityLiving entity) {
        return entity.getLootTable();
    }
    
    public static List<ItemStack> getDrops(EntityLiving entity, DamageSource source) {
        if (random == null) {
            random = new Random();
        }
        
        LootTable lootTable = entity.getEntityWorld().getLootTableManager().getLootTableFromLocation(getLootTable(entity));
        LootContext.Builder builder = (new LootContext.Builder((WorldServer) entity.getEntityWorld())).withLootedEntity(entity).withDamageSource(source);
        List<ItemStack> items = lootTable.generateLootForPools(random, builder.build());

        List<EntityItem> drops = new ArrayList<>();
        LivingDropsEvent e = new LivingDropsEvent(entity, DamageSource.GENERIC, drops, 0, false);
        MinecraftForge.EVENT_BUS.post(e);
        
        for (EntityItem drop : drops) {
            items.add(drop.getItem());
        }
        return items;
    }
    
}