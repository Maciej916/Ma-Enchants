package com.maciej916.maenchants.common.capabilities.level;

import com.maciej916.maenchants.common.registries.ModCapabilities;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class LevelCapability implements ILevelCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<ILevelCapability> levelCapabilityLazyOptional = LazyOptional.of(() -> this);
    private final Level level;

    public HashMap<Integer, Integer> TRUE_SHOT_ARROWS = new HashMap<>();

    public LevelCapability(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public void tick() {
        try {
            Set<Integer> keys = TRUE_SHOT_ARROWS.keySet();
            Integer[] array = keys.toArray(new Integer[keys.size()]);

            for (int i = TRUE_SHOT_ARROWS.size() - 1; i >= 0; i--) {
                int arrowId = array[i];

                Entity entity = level.getEntity(arrowId);
                boolean toRemove = false;
                if (entity != null && entity.isAlive()) {
                    if (entity instanceof AbstractArrow abstractArrow) {
                        int arrowTickCount = TRUE_SHOT_ARROWS.get(arrowId);

                        if (abstractArrow.isInWaterOrBubble()) {
                            abstractArrow.setNoGravity(false);
                            toRemove = true;
                        }

                        if (arrowTickCount >= 400) {
                            toRemove = true;
                            abstractArrow.discard();
                        }

                        TRUE_SHOT_ARROWS.put(arrowId, arrowTickCount + 1);
                    }
                } else {
                    toRemove = true;
                }

                if (toRemove) {
                    TRUE_SHOT_ARROWS.remove(arrowId);
                }
            }
        }
        catch (Exception e) {
            TRUE_SHOT_ARROWS = new HashMap<>();
        }
    }

    @Override
    public void entityJoinLevelEvent(EntityJoinLevelEvent event) {
        handleTrueShot(event);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.LEVEL_CAPABILITY) {
            return levelCapabilityLazyOptional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("trueArrowSize", TRUE_SHOT_ARROWS.size());

        CompoundTag tagArrows = new CompoundTag();
        int i = 0;

        for (int id: TRUE_SHOT_ARROWS.keySet()) {
            tagArrows.putInt("id_" + i, id);
            tagArrows.putInt("tick_" + i, TRUE_SHOT_ARROWS.get(id));
        }
        tag.put("trueArrowList", tagArrows);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        int trueArrowSize = nbt.getInt("trueArrowSize");
        HashMap<Integer, Integer> arrowDes = new HashMap<>();
        CompoundTag tagArrows = nbt.getCompound("trueArrowList");
        for (int i = 0; i < trueArrowSize; i++) {
            arrowDes.put(tagArrows.getInt("id_" + i), tagArrows.getInt("tick_" + i));
        }
        this.TRUE_SHOT_ARROWS = arrowDes;
    }

    public void handleTrueShot(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            Entity entity = event.getEntity();
            if (entity instanceof AbstractArrow abstractArrow) {
                if (abstractArrow.getOwner() instanceof Player player) {

                    ItemStack stack = player.getItemInHand(player.getUsedItemHand());
                    int lvl = stack.getEnchantmentLevel(ModEnchantments.TRUE_SHOT.get());
                    if (lvl == 0) return;

                    entity.setNoGravity(true);
                    TRUE_SHOT_ARROWS.put(abstractArrow.getId() , 0);
                }
            }
        }
    }
}
