package rbasamoyai.lifeandlimb.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class BodyPartEntity extends Entity {

    @Nullable private EntityType<?> belongsTo = null;
    private String modelPath = "";

    public BodyPartEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    public void setBelongsTo(EntityType<?> type) { this.belongsTo = type; }
    public void setBelongsTo(Entity entity) { this.setBelongsTo(entity.getType()); }
    public EntityType<?> getBelongsTo() { return this.belongsTo; }

    public void setModelPath(String path) { this.modelPath = path; }
    public String getModelPath() { return this.modelPath; }

    @Override protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.belongsTo = tag.contains("BelongsTo")
                ? ForgeRegistries.ENTITIES.getValue(new ResourceLocation(tag.getString("BelongsTo")))
                : null;
        this.modelPath = tag.getString("ModelPath");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.belongsTo != null) tag.putString("BelongsTo", ForgeRegistries.ENTITIES.getKey(this.belongsTo).toString());
        tag.putString("ModelPath", this.modelPath);
    }

    @Override public Packet<?> getAddEntityPacket() { return new ClientboundAddEntityPacket(this); }

}
