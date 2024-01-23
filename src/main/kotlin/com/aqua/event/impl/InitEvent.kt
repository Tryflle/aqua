package com.aqua.event.impl

import com.aqua.event.Event

open class InitEvent(val isPre: Boolean): Event() {
    class Pre : InitEvent(true)
    class Post : InitEvent(false)
}