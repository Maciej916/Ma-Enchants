package com.maciej916.maenchants.common.capabilities.level;

import com.maciej916.maenchants.common.registries.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.maciej916.maenchants.common.registries.ModEnchants.TRUE_SHOT;

public class LevelCapability implements ILevelCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<ILevelCapability> levelCapabilityLazyOptional  = LazyOptional.of(() -> this);
    private final Level level;

    public ArrayList<AbstractArrow> TRUE_SHOT_ARROWS = new ArrayList<>();

    public LevelCapability(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public void tick() {
        if (TRUE_SHOT_ARROWS.size() > 0) {
            for (AbstractArrow arrow : (ArrayList<AbstractArrow>) TRUE_SHOT_ARROWS.clone()) {
                if (arrow.isAlive()) {
                    if (arrow.tickCount >= 200) {
                        arrow.discard();
                        TRUE_SHOT_ARROWS.remove(arrow);
                    } else {
                        arrow.setDeltaMovement(arrow.getDeltaMovement().scale(1/.99));
                    }
                } else {
                    TRUE_SHOT_ARROWS.remove(arrow);
                }
            }
        }
    }

    @Override
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
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
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }


    public void handleTrueShot(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof AbstractArrow abstractArrow) {
            if (abstractArrow.getOwner() instanceof Player player) {
                ItemStack stack = player.getItemInHand(player.getUsedItemHand());
                if (EnchantmentHelper.getItemEnchantmentLevel(TRUE_SHOT, stack) > 0) {
                    entity.setNoGravity(true);
                    TRUE_SHOT_ARROWS.add(abstractArrow);
                }
            }
        }
    }




}
