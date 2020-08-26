package fluent.translators;

import fluent.Flux;

public abstract class Translator {
    public abstract <T> String translate(Flux<T> flux);
}
