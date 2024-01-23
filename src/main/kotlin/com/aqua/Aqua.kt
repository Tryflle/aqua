package com.aqua

import com.aqua.command.api.CommandManager
import com.aqua.event.Event
import com.aqua.event.impl.InitEvent
import com.aqua.features.api.FeatureManager
import com.aqua.radbus.PubSub
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager


@Mod(modid = Aqua.MOD_ID, version = Aqua.MOD_VERSION, name = Aqua.MOD_NAME, clientSideOnly = true)
class Aqua {

    companion object {
        const val MOD_ID = "aqua"
        const val MOD_VERSION = "0.0.1"
        const val MOD_NAME = "Aqua"
        val logger = LogManager.getLogger(MOD_NAME)!!
        val eventBus: PubSub<Event> = PubSub.newInstance { logger.info(it) }
    }

    @Mod.EventHandler
    fun preInit(e: FMLInitializationEvent?) {
        logger.info("Loaded $MOD_NAME")
        eventBus.subscribe(InitEvent.Post::class.java) {
            init()
        }
    }

    private fun init() {
        eventBus.subscribe(FeatureManager)
        eventBus.subscribe(CommandManager)
    }
}