package flux.translators;

import flux.fieldholders.FieldHolder;
import flux.fieldholders.SelectFieldHolder;

import java.util.List;

public interface SelectTranslator {
    public String translate(List<SelectFieldHolder> fields);

}
