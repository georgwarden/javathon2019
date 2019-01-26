package ru.mai.pubstash.util;

@FunctionalInterface
public interface CatchingSupplier<T> {

    T get() throws Throwable;

}
