package flux.translators;

import flux.fieldholders.FromFieldHolder;

import java.util.List;

public interface FromTranslator {

    public String translate(List<FromFieldHolder> fromFields);
}
