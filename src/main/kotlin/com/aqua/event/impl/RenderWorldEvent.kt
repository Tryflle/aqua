package com.aqua.event.impl

import com.aqua.event.Event

open class RenderWorldEvent(val isPre: Boolean): Event() {

    class Pre() : RenderWorldEvent(true)

    class Post() : RenderWorldEvent(false)
}