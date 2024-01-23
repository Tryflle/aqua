package com.aqua.event.impl

import com.aqua.event.Event

open class RenderGameOverlayEvent(private val partialTicks: Float, val isPre: Boolean): Event() {

    class Pre(partialTicks: Float) : RenderGameOverlayEvent(partialTicks, true)

    class Post(partialTicks: Float) : RenderGameOverlayEvent(partialTicks, false)
}