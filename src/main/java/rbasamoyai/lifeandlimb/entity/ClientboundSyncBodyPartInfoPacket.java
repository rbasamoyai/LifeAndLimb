package rbasamoyai.lifeandlimb.entity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import rbasamoyai.lifeandlimb.base.ModClientHandlers;

import java.util.function.Supplier;

public class ClientboundSyncBodyPartInfoPacket {

    private final int entityId;
    private final EntityType<?> belongsTo;
    private final String modelPath;

    public ClientboundSyncBodyPartInfoPacket(BodyPartEntity bodyPart) {
        this.entityId = bodyPart.getId();
        this.belongsTo = bodyPart.getBelongsTo();
        this.modelPath = bodyPart.getModelPath();
    }

    public ClientboundSyncBodyPartInfoPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.belongsTo = buf.readBoolean() ? buf.readRegistryIdUnsafe(ForgeRegistries.ENTITIES) : null;
        this.modelPath = buf.readUtf();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(this.belongsTo != null);
        if (this.belongsTo != null) buf.writeRegistryIdUnsafe(ForgeRegistries.ENTITIES, this.belongsTo);
        buf.writeUtf(this.modelPath);
    }

    public void handle(Supplier<NetworkEvent.Context> sup) {
        NetworkEvent.Context ctx = sup.get();
        ctx.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ModClientHandlers.handleSyncBodyPartInfo(this));
        });
        ctx.setPacketHandled(true);
    }

    public int getEntityId() { return this.entityId; }
    public EntityType<?> getBelongsTo() { return this.belongsTo; }
    public String getModelPath() { return this.modelPath; }

}
