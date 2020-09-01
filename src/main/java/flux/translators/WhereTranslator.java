package flux.translators;

import flux.statments.WhereStatement;

public interface WhereTranslator {
    public <T> String translate(WhereStatement<T> whereStatement);
}
