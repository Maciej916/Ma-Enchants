package com.maciej916.maenchants.handler;

import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.network.Networking;
import com.maciej916.maenchants.network.PacketMultiJumpDo;
import com.maciej916.maenchants.network.PacketMultiJumpSync;
import com.maciej916.maenchants.utils.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.network.NetworkDirection;

import static com.maciej916.maenchants.init.ModEnchants.MULTI_JUMP;

public class HandlerMultiJump {

    public static void handlerUpdate(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.onGround) {
            IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(event.player);
            enchantsCap.setMultiJump(0);
        }
    }

    public static void handlerLoggedIn(ServerPlayerEntity player) {
        IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(player);
        int jumps = enchantsCap.getMultiJump();
        if (jumps > 0) {
            Networking.INSTANCE.sendTo(new PacketMultiJumpSync(jumps), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    private static void handlerJumpClient(PlayerEntity player) {
        if (handlerJump(player)) {
            Networking.INSTANCE.sendToServer(new PacketMultiJumpDo());
        }
    }

    public static void handlerKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (!mc.isGameFocused() || mc.world == null) return;
        IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(mc.player);
        boolean down = mc.gameSettings.keyBindJump.isKeyDown();
        if (down) {
            if (!enchantsCap.getMultiJumpSpace()) {
                enchantsCap.setMultiJumpSpace(true);
                HandlerMultiJump.handlerJumpClient(mc.player);
            }
        } else {
            enchantsCap.setMultiJumpSpace(false);
        }
    }

    public static boolean handlerJump(PlayerEntity player) {
        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(MULTI_JUMP, player);
        if (lvl == 0 || !allowJump(player)) return false;
        IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(player);
        int jumps = enchantsCap.getMultiJump();
        if (jumps < lvl) {
            enchantsCap.setMultiJump(++jumps);
            player.jump();
            setFallDistance(player, jumps);
            extraExhaustion(player);
            return true;
        }
        return false;
    }

    private static boolean allowJump(PlayerEntity player) {
        if (player.isSneaking()) return false;

        boolean performingAction = player.onGround || player.isBeingRidden() || player.abilities.isFlying || player.abilities.allowFlying;
        boolean insideLiquid = player.isInWater() || player.isInLava();

        if (performingAction || insideLiquid) return false;

        ItemStack itemstack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        boolean fallFlyingReady = !player.isElytraFlying() && itemstack.getItem() == Items.ELYTRA && ElytraItem.isUsable(itemstack);

        if (fallFlyingReady) return false;

        return true;
    }

    private static void setFallDistance(PlayerEntity player, int jumps) {
        float f = -1.25F;
        player.fallDistance = player.fallDistance + f;
    }

    private static void extraExhaustion(PlayerEntity player) {
        if (player.isSprinting()) {
            player.addExhaustion(0.2F * 3.0F);
        } else {
            player.addExhaustion(0.05F * 3.0F);
        }
    }

}
