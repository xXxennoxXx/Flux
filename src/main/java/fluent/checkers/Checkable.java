package fluent.checkers;

import fluent.Flux;

public interface Checkable<T> {
    public boolean check(Flux<T> flux);
}
