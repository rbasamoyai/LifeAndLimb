package rbasamoyai.lifeandlimb;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BodyPartEntityRenderer extends EntityRenderer<BodyPartEntity> {

    public BodyPartEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override public ResourceLocation getTextureLocation(BodyPartEntity entity) { return null; }

}
