package flux.translators;

import flux.statments.OrderByStatement;

import java.util.List;

public interface OrderByTranslator {
    public <T> String translate(List<OrderByStatement<T>> fields);
}
