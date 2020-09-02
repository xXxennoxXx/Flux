package flux;

import flux.checkers.QueryChecklist;
import flux.evaluators.FieldEvaluator;
import flux.fieldholders.*;
import flux.statments.*;
import flux.translators.Dialect;
import flux.translators.Translator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

//TODO Zeby zapewnic sensowane API biblioteki wszystko powinno byc w jednej paczce?


public abstract class Flux<T> {

    private final Dialect dialect;
    private final Supplier<T> supplier;


    private final List<SelectFieldHolder> selectFields = new ArrayList<>();
    private final List<FromFieldHolder> fromFields = new ArrayList<>();
    private final List<JoinFieldHolder> joinFields = new ArrayList<>();
    private final List<WhereFieldHolder> whereFields = new ArrayList<>();
    private final List<GroupByFieldHolder> groupByFields = new ArrayList<>();
    private final List<HavingFieldHolder> havingFields = new ArrayList<>();
    private final List<OrderByFieldHolder> orderByFields = new ArrayList<>();

    private final FieldEvaluator<T> fieldEvaluator;


    public Flux(Dialect dialect, Supplier<T> supplier) {
        this.dialect = dialect;
        this.supplier = supplier;

        fieldEvaluator = new FieldEvaluator<>(supplier);
    }


    //TODO
    @SafeVarargs
    public final SelectStatement<T> select(Function<T, Object>... functions) {
        return select(null, functions);
    }

    @SafeVarargs
    public final SelectStatement<T> select(AggregateFunctionType aggregateFunctionType, Function<T, Object>... functions) {
        for (Function<T, Object> function : functions) {
            FieldHolder queryField = evaluateQueryField(function);
            SelectFieldHolder selectFieldHolder = new SelectFieldHolder(queryField);
            selectFieldHolder.setAggregateFunctionType(aggregateFunctionType);
            selectFields.add(selectFieldHolder);
        }
        return new SelectStatement<>(this);
    }

    @SafeVarargs
    public final FromStatement<T> from(Function<T, Object>... functions) {
        for (Function<T, Object> function : functions) {
            FieldHolder queryField = evaluateQueryField(function);
            FromFieldHolder fromFieldHolder = new FromFieldHolder(queryField);
            fromFields.add(fromFieldHolder);
        }
        return new FromStatement<>(this);
    }


    @SafeVarargs
    public final JoinStatement<T> join(Function<T, Object>... functions) {
        addJoinFields(JoinType.INNER, functions);
        return new JoinStatement<>(this);
    }

    @SafeVarargs
    public final JoinStatement<T> leftJoin(Function<T, Object>... functions) {
        addJoinFields(JoinType.LEFT_OUTER, functions);
        return new JoinStatement<>(this);

    }

    @SafeVarargs
    public final JoinStatement<T> rightJoin(Function<T, Object>... functions) {
        addJoinFields(JoinType.RIGHT_OUTER, functions);
        return new JoinStatement<>(this);

    }

    @SafeVarargs
    public final JoinStatement<T> fullJoin(Function<T, Object>... functions) {
        addJoinFields(JoinType.FULL_OUTER, functions);
        return new JoinStatement<>(this);
    }

    @SafeVarargs
    private final void addJoinFields(JoinType joinType, Function<T, Object>... functions) {
        for (Function<T, Object> function : functions) {
            FieldHolder fieldHolder = evaluateQueryField(function);
            JoinFieldHolder joinFieldHolder = new JoinFieldHolder(fieldHolder, joinType);
            joinFields.add(joinFieldHolder);
        }
    }

    public WhereCondition<T> where(Function<T, Object> function) {
        return newWhereStatement(function);
    }

    public WhereCondition<T> andWhere(Function<T, Object> function) {
        return newWhereStatement(function, ConditionsConcatenationType.AND);
    }

    public WhereCondition<T> orWhere(Function<T, Object> function) {
        return newWhereStatement(function, ConditionsConcatenationType.OR);
    }

    private WhereCondition<T> newWhereStatement(Function<T, Object> function) {
        return newWhereStatement(function, null);
    }

    private WhereCondition<T> newWhereStatement(Function<T, Object> function, ConditionsConcatenationType conditionsConcatenationType) {
        if (conditionsConcatenationType != null) {
            WhereFieldHolder lastWhereField = whereFields.get(whereFields.size() - 1);
            lastWhereField.setWhereConcatOperatorEnum(conditionsConcatenationType);
        }
        FieldHolder fieldHolder = evaluateQueryField(function);
        WhereFieldHolder whereFieldHolder = new WhereFieldHolder(fieldHolder);
        whereFields.add(whereFieldHolder);
        return new WhereCondition<>(this, whereFieldHolder);
    }

    @SafeVarargs
    public final GroupByStatement<T> groupBy(Function<T, Object>... functions) {
        for (Function<T, Object> function : functions) {
            FieldHolder queryField = evaluateQueryField(function);
            GroupByFieldHolder groupByFieldHolder = new GroupByFieldHolder(queryField);
            groupByFields.add(groupByFieldHolder);
        }
        return new GroupByStatement<>(this);
    }


    public HavingAggregateCondition<T> having(Function<T, Object> function) {
        return newHavingStatement(function);
    }

    public HavingAggregateCondition<T> andHaving(Function<T, Object> function) {
        return newHavingStatement(function, ConditionsConcatenationType.AND);
    }

    public HavingAggregateCondition<T> orHaving(Function<T, Object> function) {
        return newHavingStatement(function, ConditionsConcatenationType.OR);
    }

    private HavingAggregateCondition<T> newHavingStatement(Function<T, Object> function) {
        return newHavingStatement(function, null);
    }

    private HavingAggregateCondition<T> newHavingStatement(Function<T, Object> function, ConditionsConcatenationType conditionsConcatenationType) {
        if (conditionsConcatenationType != null) {
            HavingFieldHolder lastHavingFieldHolder = havingFields.get(havingFields.size() - 1);
            lastHavingFieldHolder.setConcatenationOperatorTypeType(conditionsConcatenationType);
        }
        FieldHolder fieldHolder = evaluateQueryField(function);
        HavingFieldHolder havingFieldHolder = new HavingFieldHolder(fieldHolder);
        havingFields.add(havingFieldHolder);
        return new HavingAggregateCondition<>(this, havingFieldHolder);
    }

    public OrderByStatement<T> orderBy(Function<T, Object> function) {
        FieldHolder fieldHolder = evaluateQueryField(function);
        OrderByFieldHolder orderByFieldHolder = new OrderByFieldHolder(fieldHolder);
        orderByFields.add(orderByFieldHolder);
        return new OrderByStatement<>(this, orderByFieldHolder);
    }

    public String toQuery() {
        for (QueryChecklist check : QueryChecklist.values()) {
            check.check(this);
        }
        Translator translator = getTranslator();
        String translate = translator.translate(this);
        clean();

        return translate;
    }

    private void clean() {
        selectFields.clear();
        fromFields.clear();
        joinFields.clear();
        whereFields.clear();
        groupByFields.clear();
        havingFields.clear();
        orderByFields.clear();
    }

    private FieldHolder evaluateQueryField(Function<T, Object> function) {
        return fieldEvaluator.findQueryField(function);
    }


    public Translator getTranslator() {
        return dialect.getTranslator();
    }

    public String getClassName() {
        return supplier.get().getClass().getSimpleName();
    }

    public FromFieldHolder getFromFieldHolder() {
        Class<?> aClass = supplier.get().getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Field declaredField = declaredFields[0];
        return new FromFieldHolder(new FieldHolder(getClassName(), declaredField));
    }

    public List<SelectFieldHolder> getSelectFields() {
        return selectFields;
    }

    public List<FromFieldHolder> getFromFields() {
        return fromFields;
    }

    public List<JoinFieldHolder> getJoinFields() {
        return joinFields;
    }

    public List<WhereFieldHolder> getWhereFields() {
        return whereFields;
    }

    public List<GroupByFieldHolder> getGroupByFields() {
        return groupByFields;
    }

    public List<HavingFieldHolder> getHavingFields() {
        return havingFields;
    }

    public List<OrderByFieldHolder> getOrderByFields() {
        return orderByFields;
    }

    public abstract List<T> toList() throws Exception;


}
