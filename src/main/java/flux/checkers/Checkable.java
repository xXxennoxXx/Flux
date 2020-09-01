package flux.checkers;

import flux.Flux;

public interface Checkable<T> {
    public boolean check(Flux<T> flux);
}
