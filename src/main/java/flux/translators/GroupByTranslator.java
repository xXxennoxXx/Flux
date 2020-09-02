package flux.translators;

import flux.fieldholders.GroupByFieldHolder;

import java.util.List;

public interface GroupByTranslator {

    public String translate(List<GroupByFieldHolder> groupByFields);
}
