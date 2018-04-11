package com.rayton.gps.exception;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import java.util.function.Function;

public class Try {

    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    @FunctionalInterface
    public static interface UncheckedFunction<T, R> {

        R apply(T t) throws Exception;
    }

    // @FunctionalInterface
    // public static interface UncheckedConsumer<T, R> {
    //
    //     R apply(T t) throws Exception;
    // }
    //
    // @FunctionalInterface
    // public static interface UncheckedSupplier<T, R> {
    //
    //     R apply(T t) throws Exception;
    // }
    // @FunctionalInterface
    // public static interface UncheckedBiFunction<T, R> {
    //
    //     R apply(T t) throws Exception;
    // }

}