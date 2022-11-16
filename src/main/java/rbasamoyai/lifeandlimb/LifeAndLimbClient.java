package rbasamoyai.lifeandlimb;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rbasamoyai.lifeandlimb.entity.BodyPartEntityRenderer;

public class LifeAndLimbClient {

    public static void onCtor(IEventBus modBus, IEventBus forgeBus) {
        modBus.addListener(LifeAndLimbClient::onClientSetup);
        modBus.addListener(LifeAndLimbClient::onRegisterEntityRenderer);
    }

    public static void onClientSetup(FMLClientSetupEvent evt) {

    }

    public static void onRegisterEntityRenderer(EntityRenderersEvent.RegisterRenderers evt) {
        evt.registerEntityRenderer(LifeAndLimb.BODY_PART.get(), BodyPartEntityRenderer::new);
    }

}
