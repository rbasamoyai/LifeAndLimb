package rbasamoyai.lifeandlimb;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.stream.Collectors;

@Mod(LifeAndLimb.MOD_ID)
public class LifeAndLimb
{
    public static final String MOD_ID = "lifeandlimb";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);
    public static final RegistryObject<EntityType<BodyPartEntity>> BODY_PART = ENTITY_TYPES.register("body_part",
            () -> EntityType.Builder.of(BodyPartEntity::new, MobCategory.MISC)
                    .clientTrackingRange(5)
                    .sized(0.5f, 0.5f)
                    .build(LifeAndLimb.path("body_part").toString()));

    public LifeAndLimb()
    {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        ENTITY_TYPES.register(modBus);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> LifeAndLimbClient.onCtor(modBus, forgeBus));
    }

    public static ResourceLocation path(String path) { return new ResourceLocation(MOD_ID, path); }

}
