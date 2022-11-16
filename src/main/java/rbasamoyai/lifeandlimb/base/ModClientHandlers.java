package rbasamoyai.lifeandlimb.base;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import rbasamoyai.lifeandlimb.entity.BodyPartEntity;
import rbasamoyai.lifeandlimb.entity.ClientboundSyncBodyPartInfoPacket;

import java.util.function.Supplier;

public class ModClientHandlers {

    public static void handleSyncBodyPartInfo(ClientboundSyncBodyPartInfoPacket pkt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || !(mc.level.getEntity(pkt.getEntityId()) instanceof BodyPartEntity bodyPart)) return;
        bodyPart.setBelongsTo(pkt.getBelongsTo());
        bodyPart.setModelPath(pkt.getModelPath());
    }

}
