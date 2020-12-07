package me.sysdm.net.eventapi.events.interfaces;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public interface EventExecutor {

    void handler(Listener listener, Event event);

    void register();
}
