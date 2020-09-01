package flux.translators.hibernate;

import flux.Flux;
import flux.JoinType;
import flux.fieldholders.FieldHolder;
import flux.fieldholders.JoinFieldHolder;
import flux.fieldholders.WhereFieldHolder;
import flux.statments.*;
import flux.translators.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateTranslator extends Translator {

    public final SelectTranslator selectTranslator = Dialect.HIBERNATE.getSelectTranslator();
    public final FromTranslator fromTranslator = Dialect.HIBERNATE.getFromTranslator();
    public final JoinTranslator joinTranslator = Dialect.HIBERNATE.getJoinTranslator();
    public final WhereTranslator whereTranslator = Dialect.HIBERNATE.getWhereTranslator();
    public final GroupByTranslator groupByTranslator = Dialect.HIBERNATE.getGroupByTranslator();
    public final HavingTranslator havingTranslator = Dialect.HIBERNATE.getHavingTranslator();
    public final OrderByTranslator orderByTranslator = Dialect.HIBERNATE.getOrderByTranslator();

    public final List<JoinFieldHolder> joinFieldHolders = new ArrayList<>();

    @Override
    public <T> String translate(Flux<T> flux) {
        StringBuilder sb = new StringBuilder();

        prepareJoin(flux);
        String select = evaluateSelect(flux),
                from = evaluateFrom(flux),
                where = evaluateWhere(flux),
                join = evaluateJoin(flux),
                orderBy = evaluateOrderBy(flux);


        sb.append(select)
                .append(from)
                .append(join)
                .append(where)
                .append(orderBy);

        return sb.toString();
    }


    public <T> String evaluateSelect(Flux<T> flux) {
        SelectStatement<T> selectStatement = flux.getSelectStatement();
        if (selectStatement == null)
            return "";
        List<FieldHolder> fields = selectStatement.getFields();
        return selectTranslator.translate(fields);
    }

    public <T> String evaluateFrom(Flux<T> flux) {
        String className = flux.getClassName();
        return fromTranslator.translate(className);
    }

    public <T> void prepareJoin(Flux<T> flux) {
        List<JoinStatement<T>> joinStatements = flux.getJoinStatements();
        if (joinStatements.isEmpty())
            return;
        for (JoinStatement<T> s : joinStatements)
            joinFieldHolders.add(s.getJoinFieldHolder());
    }

    public <T> String evaluateJoin(Flux<T> flux) {
        if (joinFieldHolders.isEmpty())
            return "";
        return joinTranslator.translate(joinFieldHolders);
    }

    public <T> String evaluateWhere(Flux<T> flux) {
        String translate = whereTranslator.translate(flux.getWhereStatement());

        WhereStatement<T> whereStatement = flux.getWhereStatement();
        List<WhereFieldHolder> fields = new ArrayList<>();
        while (whereStatement != null) {
            fields.add(whereStatement.getWhereFieldHolder());
            whereStatement = whereStatement.getNext();
        }

        fields.removeIf(fieldHolder -> fieldHolder.getFieldHolder().getSubField() == null);
        List<JoinStatement<T>> joinStatements = flux.getJoinStatements();
        for (JoinStatement<T> field : joinStatements) {
            for (Iterator<WhereFieldHolder> iterator = fields.iterator(); iterator.hasNext(); ) {
                WhereFieldHolder whereField = iterator.next();
                JoinFieldHolder joinHolder = field.getJoinFieldHolder();
                if (whereField.getFieldHolder().getClassName().equals(joinHolder.getClassName()))
                    if (whereField.getFieldHolder().getMainField().getName().equals(joinHolder.getMainField().getName()))
                        iterator.remove();
            }
        }
        for (WhereFieldHolder whereFieldHolder : fields) {
            joinFieldHolders.add(new JoinFieldHolder(new FieldHolder(whereFieldHolder.getFieldHolder().getClassName(), whereFieldHolder.getFieldHolder().getMainField()), JoinType.INNER));
        }
        return whereTranslator.translate(flux.getWhereStatement());
    }

    private <T> String evaluateOrderBy(Flux<T> flux) {
        List<OrderByStatement<T>> orderByStatements = flux.getOrderByStatements();
        if (orderByStatements.isEmpty())
            return "";
        return orderByTranslator.translate(orderByStatements);
    }

}
