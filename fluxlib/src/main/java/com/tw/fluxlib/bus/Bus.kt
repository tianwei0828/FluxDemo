package com.tw.fluxlib.bus

import org.greenrobot.eventbus.EventBus


/**
 * 自定义EventBus
 * Created by wei.tian
 * 2018/5/16
 */
class Bus {
    companion object {
        private val eventBus: EventBus = EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .build()

        fun getEventBus(): EventBus {
            return eventBus
        }
    }
}