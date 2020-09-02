package flux.translators.hibernate;

import flux.Flux;
import flux.JoinType;
import flux.checkers.QueryHibernateCheckList;
import flux.fieldholders.*;
import flux.translators.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateTranslator extends Translator {

    public final SelectTranslator selectTranslator = Dialect.HIBERNATE.getSelectTranslator();
    public final FromTranslator fromTranslator = Dialect.HIBERNATE.getFromTranslator();
    public final JoinTranslator joinTranslator = Dialect.HIBERNATE.getJoinTranslator();
    public final WhereTranslator whereTranslator = Dialect.HIBERNATE.getWhereTranslator();
    public final GroupByTranslator groupByTranslator = Dialect.HIBERNATE.getGroupByTranslator();
    public final HavingTranslator havingTranslator = Dialect.HIBERNATE.getHavingTranslator();
    public final OrderByTranslator orderByTranslator = Dialect.HIBERNATE.getOrderByTranslator();


    @Override
    public <T> String translate(Flux<T> flux) {
        QueryHibernateCheckList[] values = QueryHibernateCheckList.values();
        for (QueryHibernateCheckList value : values)
            value.check(flux);

        StringBuilder sb = new StringBuilder();
        evaluateJoinsAccordingToWheres(flux);
        evaluateFromStatement(flux);
        String select = translateSelect(flux),
                from = translateFrom(flux),
                join = translateJoin(flux),
                where = translateWhere(flux),
                groupBy = translateGroupBy(flux),
                having = translateHaving(flux),
                orderBy = translateOrderBy(flux);



        sb.append(select)
                .append(from)
                .append(join)
                .append(where)
                .append(groupBy)
                .append(having)
                .append(orderBy);

        return sb.toString();
    }

    private <T> void evaluateFromStatement(Flux<T> flux) {
        List<FromFieldHolder> fromFields = flux.getFromFields();
        FromFieldHolder fromFieldHolder = flux.getFromFieldHolder();
        flux.getFromFields().add(fromFieldHolder);

    }

    private <T> void evaluateJoinsAccordingToWheres(Flux<T> flux) {
        List<FieldHolder> fieldHolders = hasProperJoins(flux);

        List<JoinFieldHolder> joinFieldHolders = fieldHolders.stream()
                .map(e -> new JoinFieldHolder(e, JoinType.INNER))
                .collect(Collectors.toList());

        flux.getJoinFields().addAll(joinFieldHolders);
    }

    private <T> List<FieldHolder> hasProperJoins(Flux<T> flux) {
        List<JoinFieldHolder> joinFields = flux.getJoinFields();
        List<FieldHolder> joinFieldHolders = joinFields
                .stream()
                .map(CustomFieldHolder::getFieldHolder)
                .collect(Collectors.toList());

        List<WhereFieldHolder> whereFields = flux.getWhereFields();
        List<FieldHolder> whereFieldHolders = whereFields
                .stream()
                .map(CustomFieldHolder::getFieldHolder)
                .filter(e -> e.getSubField() != null)
                .collect(Collectors.toList());

        Iterator<FieldHolder> whereField = whereFieldHolders.iterator();
        if (joinFieldHolders.isEmpty())
            return whereFieldHolders;
        while (whereField.hasNext()) {
            FieldHolder next = whereField.next();
            for (FieldHolder joinField : joinFieldHolders) {
                if (next.getMainField().getName().equals(joinField.getMainField().getName()))
                    whereField.remove();
            }
        }

        return whereFieldHolders;
    }


    public <T> String translateSelect(Flux<T> flux) {
        List<SelectFieldHolder> selectFields = flux.getSelectFields();
        return selectTranslator.translate(selectFields);
    }

    public <T> String translateFrom(Flux<T> flux) {
        List<FromFieldHolder> fromFields = flux.getFromFields();
        return fromTranslator.translate(fromFields);
    }

    public <T> String translateJoin(Flux<T> flux) {
        List<JoinFieldHolder> joinFields = flux.getJoinFields();
        return joinTranslator.translate(joinFields);
    }

    public <T> String translateWhere(Flux<T> flux) {
        List<WhereFieldHolder> whereFields = flux.getWhereFields();
        return whereTranslator.translate(whereFields);
    }

    private <T> String translateGroupBy(Flux<T> flux) {
        List<GroupByFieldHolder> groupByFields = flux.getGroupByFields();
        return groupByTranslator.translate(groupByFields);
    }

    private <T> String translateHaving(Flux<T> flux) {
        List<HavingFieldHolder> havingFields = flux.getHavingFields();
        return havingTranslator.translate(havingFields);
    }

    private <T> String translateOrderBy(Flux<T> flux) {
        List<OrderByFieldHolder> orderByFields = flux.getOrderByFields();
        return orderByTranslator.translate(orderByFields);
    }

}
