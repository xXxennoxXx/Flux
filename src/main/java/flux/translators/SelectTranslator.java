package flux.translators;

import flux.fieldholders.FieldHolder;

import java.util.List;

public interface SelectTranslator {
    public String translate(List<FieldHolder> fields);

}
