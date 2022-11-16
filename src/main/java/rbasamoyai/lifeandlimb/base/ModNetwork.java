package rbasamoyai.lifeandlimb.base;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import rbasamoyai.lifeandlimb.LifeAndLimb;
import rbasamoyai.lifeandlimb.entity.ClientboundSyncBodyPartInfoPacket;

public class ModNetwork {

    public static final String VERSION = "1.0.0";
    public static final SimpleChannel INSTANCE = construct();

    public static SimpleChannel construct() {
        SimpleChannel channel = NetworkRegistry.ChannelBuilder
                .named(LifeAndLimb.path("network"))
                .clientAcceptedVersions(VERSION::equals)
                .serverAcceptedVersions(VERSION::equals)
                .networkProtocolVersion(() -> VERSION)
                .simpleChannel();

        int id = 0;

        channel.messageBuilder(ClientboundSyncBodyPartInfoPacket.class, id++)
                .encoder(ClientboundSyncBodyPartInfoPacket::encode)
                .decoder(ClientboundSyncBodyPartInfoPacket::new)
                .consumer(ClientboundSyncBodyPartInfoPacket::handle)
                .add();

        return channel;
    }

    public static void init() {}

}
