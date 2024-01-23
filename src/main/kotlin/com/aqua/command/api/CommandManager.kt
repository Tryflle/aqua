package com.aqua.command.api

import com.aqua.command.impl.HelpCommand
import com.aqua.event.impl.ChatSendEvent
import com.aqua.radbus.Listen


object CommandManager {

    var Commands: HashMap<String, Command> = hashMapOf(
        HelpCommand().name to HelpCommand()
    )


    @Listen
    fun onChatSend(e: ChatSendEvent) {
        for (command in Commands.values) {
            if (e.message.startsWith(".$command.name")) {
                command.handle(e.message.split(" ") as ArrayList<String>)
                e.setCancelled(true)
            }
        }
    }
}