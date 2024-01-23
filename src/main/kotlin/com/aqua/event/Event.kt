package com.aqua.event

open class Event {

    private var cancelled: Boolean = false;

    fun isCancelled(): Boolean {
        return cancelled;
    }

    fun setCancelled(cancel: Boolean) {
        this.cancelled = cancel;
    }
}