package com.aqua.command.impl

import com.aqua.command.api.Command
import com.aqua.command.api.CommandManager
import com.aqua.utils.player.PlayerUtils

class HelpCommand: Command(
    "help",
    "Displays a list of commands.",
    arrayOf("h", "cmds", "commands")) {

    override fun handle(args: ArrayList<String>) {
        PlayerUtils.addChatMessage("Available Commands:", true)
        for (command in CommandManager.Commands) {
            PlayerUtils.addChatMessage("$command.name - $command.description", true)
        }
    }
}