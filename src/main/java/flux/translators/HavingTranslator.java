package flux.translators;

import flux.fieldholders.HavingFieldHolder;

import java.util.List;

public interface HavingTranslator {
    public String translate(List<HavingFieldHolder> havingFields);
}
