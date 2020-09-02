package flux.translators;

import flux.fieldholders.WhereFieldHolder;
import flux.statments.WhereStatement;

import java.util.List;

public interface WhereTranslator {
    public String translate(List<WhereFieldHolder> whereStatement);
}
