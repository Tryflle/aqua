package com.aqua.event.impl

import com.aqua.event.Event

class PacketEvent(val packet: Any, val direction: PacketDirection): Event() {
}

enum class PacketDirection {
    INCOMING,
    OUTGOING
}