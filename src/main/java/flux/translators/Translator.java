package flux.translators;

import flux.Flux;

public abstract class Translator {
    public abstract <T> String translate(Flux<T> flux);
}
