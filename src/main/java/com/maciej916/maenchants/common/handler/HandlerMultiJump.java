package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.network.ModNetworking;
import com.maciej916.maenchants.common.network.packet.PacketMultiJumpDo;
import com.maciej916.maenchants.common.network.packet.PacketMultiJumpSync;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.network.NetworkDirection;

public class HandlerMultiJump {

    public static void handlerPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.isOnGround()) {
            IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(event.player);
            if (enchantsCap == null) return;
            enchantsCap.setMultiJump(0);
        }
    }

    public static void handlerLoggedIn(ServerPlayer player) {
        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        int jumps = enchantsCap.getMultiJump();
        if (jumps > 0) {
            ModNetworking.INSTANCE.sendTo(new PacketMultiJumpSync(jumps), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    private static void handlerJumpClient(Player player) {
        if (handlerJump(player)) {
            ModNetworking.INSTANCE.sendToServer(new PacketMultiJumpDo());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerKeyInput(Minecraft mc, InputEvent.Key event) {
        if (!mc.isWindowActive() || mc.player == null || mc.level == null) return;

        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(mc.player);
        if (enchantsCap == null) return;

        boolean down = mc.options.keyJump.isDown();
        if (down) {
            if (!enchantsCap.getMultiJumpSpace()) {
                enchantsCap.setMultiJumpSpace(true);
                HandlerMultiJump.handlerJumpClient(mc.player);
            }
        } else {
            enchantsCap.setMultiJumpSpace(false);
        }
    }

    public static boolean handlerJump(Player player) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.MULTI_JUMP.get(), stack);
        if (lvl == 0 || !allowJump(player)) return false;

        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return false;

        int jumps = enchantsCap.getMultiJump();
        if (jumps < lvl) {
            enchantsCap.setMultiJump(++jumps);
            player.jumpFromGround();
            extraExhaustion(player);
            return true;
        }
        return false;
    }

    private static boolean allowJump(Player player) {
        if (player.isCrouching()) return false;

        boolean performingAction = player.isOnGround() || !player.getPassengers().isEmpty() || player.getAbilities().flying || player.getAbilities().mayfly;
        boolean insideLiquid = player.isInWater() || player.isInLava();

        if (performingAction || insideLiquid) return false;

        ItemStack itemstack = player.getItemBySlot(EquipmentSlot.CHEST);
        boolean fallFlyingReady = itemstack.getItem() == Items.ELYTRA && ElytraItem.isFlyEnabled(itemstack);

        return !fallFlyingReady;
    }

    private static void extraExhaustion(Player player) {
        if (player.isSprinting()) {
            player.causeFoodExhaustion(0.2F * 3.0F);
        } else {
            player.causeFoodExhaustion(0.05F * 3.0F);
        }
    }
}
