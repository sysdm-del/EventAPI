package me.sysdm.net.eventapi.events;

import lombok.Getter;
import me.sysdm.net.eventapi.events.other.PredicateConsumer;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventSubscriber<T extends Event> implements Listener, EventExecutor {

    private final Class<T> eventType;

    @Getter
    private final EventCaller<T> caller;

    private final Plugin plugin;

    private final EventPriority priority;

    private final List<Object> actionList;

    private final boolean ignoreCancelled;

    public EventSubscriber(Class<T> eventType, EventCaller<T> caller, Plugin plugin, EventPriority priority) {
        this.eventType = eventType;
        this.caller = caller;
        this.plugin = plugin;
        this.priority = priority;
        this.actionList = caller.getActionList();
        this.ignoreCancelled = false;
    }
    public EventSubscriber(Class<T> eventType, EventCaller<T> caller, Plugin plugin, EventPriority priority, boolean ignoreCancelled) {
        this.eventType = eventType;
        this.caller = caller;
        this.plugin = plugin;
        this.priority = priority;
        this.actionList = caller.getActionList();
        this.ignoreCancelled = ignoreCancelled;
    }

    public void register() {
        Bukkit.getPluginManager().registerEvent(eventType, this, priority, this, plugin, ignoreCancelled);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Listener listener, Event event) {


        if (event.getClass() != eventType) {
            return;
        }

        T eventCasted = eventType.cast(event);

        for (Object o : actionList) {
            if (o == null) {
                continue;
            }

            if (o instanceof PredicateConsumer && ((PredicateConsumer<T>) o).getPredicate().test(eventCasted)) {
                ((PredicateConsumer<T>) o).getConsumer().accept(eventCasted);
            } else if (o instanceof PredicateConsumer.Filter) {
                PredicateConsumer.Filter<T> filter = (PredicateConsumer.Filter<T>) o;
                if (!filter.getPredicate().test(eventCasted)) {
                    filter.getConsumer().accept(eventCasted);
                    break;
                }
            } else if (o instanceof Predicate && !((Predicate<T>) o).test(eventCasted)) {
                break;
            } else if (o instanceof Consumer) {
                ((Consumer<T>) o).accept(eventCasted);
            }
        }
    }
}
