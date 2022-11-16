package rbasamoyai.lifeandlimb.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BodyPartEntityRenderer extends EntityRenderer<BodyPartEntity> {

    private final EntityModelSet modelSet;

    public BodyPartEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.modelSet = ctx.getModelSet();
    }

    @Override
    public void render(BodyPartEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffers, int light) {
        Model
    }

    @Override public ResourceLocation getTextureLocation(BodyPartEntity entity) { return null; }

}
