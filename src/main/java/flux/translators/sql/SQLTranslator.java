package flux.translators.sql;

import flux.Flux;
import flux.statments.*;
import flux.translators.*;

public class SQLTranslator extends Translator {

    public final SelectTranslator selectTranslator;
    public final FromTranslator fromTranslator;
    public final JoinTranslator joinTranslator;
    public final WhereTranslator whereTranslator;
    public final GroupByTranslator groupByTranslator;
    public final HavingTranslator havingTranslator;
    public final OrderByTranslator orderByTranslator;


    public SQLTranslator() {
        selectTranslator = null;
        fromTranslator = null;
        joinTranslator = null;
        whereTranslator = null;
        groupByTranslator = null;
        havingTranslator = null;
        orderByTranslator = null;
    }


    @Override
    public <T> String translate(Flux<T> flux) {
//        flux.getSelectStatement();
//        flux.getFromStatement();
//        flux.getWhereStatement();
//        flux.getGroupByStatement();
//        flux.getHavingStatement();
//        flux.getOrderByStatements();

        return null;
    }

    private <T> String select(SelectStatement<T> selectStatement) {
        return null;
    }

    ;
}
