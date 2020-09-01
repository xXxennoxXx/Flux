package flux.translators;

import flux.fieldholders.JoinFieldHolder;
import flux.statments.JoinStatement;

import java.util.List;

public interface JoinTranslator {

    public <T> String translate(List<JoinFieldHolder> statementList);
}
