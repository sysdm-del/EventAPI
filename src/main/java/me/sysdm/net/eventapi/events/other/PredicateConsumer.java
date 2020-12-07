package me.sysdm.net.eventapi.events.other;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateConsumer<T> {

    private final Predicate<T> predicate;
    private final Consumer<T> consumer;

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public Consumer<T> getConsumer() {
        return consumer;
    }

    public PredicateConsumer(Predicate<T> predicate,
                             Consumer<T> consumer,
                             boolean negated) {
        this.predicate = negated ? predicate.negate() : predicate;
        this.consumer = consumer;
    }

    public static class Filter<T> {

        private final Predicate<T> predicate;
        private final Consumer<T> consumer;

        public Filter(Predicate<T> predicate,
                      Consumer<T> consumer) {
            this.predicate = predicate;
            this.consumer = consumer;
        }

        public Predicate<T> getPredicate() {
            return predicate;
        }

        public Consumer<T> getConsumer() {
            return consumer;
        }
    }


}
