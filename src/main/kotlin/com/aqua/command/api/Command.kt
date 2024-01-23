package com.aqua.command.api

open class Command (
    val name: String,
    val description: String,
    val aliases: Array<String>
) {
    open fun handle(args: ArrayList<String>) {}
}