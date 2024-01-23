package com.aqua.event.impl

import com.aqua.event.Event

class ClickEvent(val button: MouseButton): Event() {
}

enum class MouseButton {
    LEFT,
    RIGHT
}