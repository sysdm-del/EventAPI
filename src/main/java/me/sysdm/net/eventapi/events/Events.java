package me.sysdm.net.eventapi.events;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;

public class Events {

    public static <T extends Event> EventCaller<T> listen(Class<T> e) {
        return new EventCaller<>(e);
    }



}
