package me.sysdm.net.eventapi.events;

import lombok.Getter;
import me.sysdm.net.eventapi.EventAPI;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventCaller<T extends Event> {

    private final Class<T> event;

    @Getter
    private final List<Object> actionList = new ArrayList<>();

    public EventCaller(Class<T> event) {
        this.event = event;
    }

    public EventCaller<T> filter(Predicate<T> predicate) {
        actionList.add(predicate);
        return this;
    }

    public void handler(Consumer<T> action, EventPriority priority) {
        actionList.add(action);
        EventSubscriber<T> eventSubscriber = new EventSubscriber<T>(event, this, EventAPI.getInstance(), priority);
        eventSubscriber.register();
    }
    public void handler(Consumer<T> action, EventPriority priority, boolean ignoreCancelled) {
        actionList.add(action);
        EventSubscriber<T> eventSubscriber = new EventSubscriber<T>(event, this, EventAPI.getInstance(), priority, ignoreCancelled);
        eventSubscriber.register();
    }
    public void handler(Consumer<T> action) {
        actionList.add(action);
        EventSubscriber<T> eventSubscriber = new EventSubscriber<T>(event, this, EventAPI.getInstance(), EventPriority.NORMAL);
        eventSubscriber.register();
    }

}
