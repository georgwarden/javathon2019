package ru.mai.pubstash.util;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Result<T> {

    <R> R fold(Function<T, R> ifSuccess, Supplier<R> ifFail);
    <N> Result<N> map(Function<T, N> mapper);

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> fail() {
        return new Failure<>();
    }

    static <T> Result<T> retrieve(CatchingSupplier<T> from) {
        try {
            T result = from.get();
            return Result.success(result);
        } catch (Throwable e) {
            return Result.fail();
        }
    }

}

class Success<T> implements Result<T> {

    public final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public <R> R fold(Function<T, R> ifSuccess, Supplier<R> ifFail) {
        return ifSuccess.apply(value);
    }

    @Override
    public <N> Result<N> map(Function<T, N> mapper) {
        return new Success<>(mapper.apply(value));
    }
}

class Failure<T> implements Result<T> {

    @Override
    public <R> R fold(Function<T, R> ifSuccess, Supplier<R> ifFail) {
        return ifFail.get();
    }

    @Override
    public <N> Result<N> map(Function<T, N> mapper) {
        return new Failure<>();
    }

}