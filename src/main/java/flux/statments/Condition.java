package flux.statments;

import flux.Flux;

public class Condition<T> {
    protected final Flux<T> flux;


    public Condition(Flux<T> flux) {
        this.flux = flux;
    }

}
