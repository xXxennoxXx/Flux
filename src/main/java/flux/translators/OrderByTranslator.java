package flux.translators;

import flux.fieldholders.OrderByFieldHolder;

import java.util.List;

public interface OrderByTranslator {
    public  String translate(List<OrderByFieldHolder> fields);
}
