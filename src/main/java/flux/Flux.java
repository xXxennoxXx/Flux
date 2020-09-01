package flux;

import flux.checkers.EnumCheckList;
import flux.fieldholders.WhereConcatOperatorEnum;
import flux.statments.*;
import flux.translators.Dialect;
import flux.translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

//TODO Zeby zapewnic sensowane API biblioteki wszystko powinno byc w jednej paczce?


public class Flux<T> {

    private final Dialect dialect;
    private final Supplier<T> supplier;
    private SelectStatement<T> selectStatement;
    private FromStatement<T> fromStatement;
    private List<JoinStatement<T>> joinStatements;
    private WhereStatement<T> whereStatement;
    private GroupByStatement<T> groupByStatement;
    private HavingStatement<T> havingStatement;
    private List<OrderByStatement<T>> orderByStatements;

    public Flux(Dialect dialect, Supplier<T> supplier) {
        this.dialect = dialect;
        this.supplier = supplier;

        orderByStatements = new ArrayList<>();
        joinStatements = new ArrayList<>();
    }

    //    TODO O Co chodzi

    @SafeVarargs
    public final Flux<T> select(Function<T, Object>... functions) {
        selectStatement = new SelectStatement<T>(supplier, this, functions);
        return this;

    }

    public Flux<T> join(Function<T, Object> function) {
        joinStatements.add(new JoinStatement<>(supplier, this, function, JoinType.INNER));
        return this;
    }

    public Flux<T> leftJoin(Function<T, Object> function) {
        joinStatements.add(new JoinStatement<>(supplier, this, function, JoinType.LEFT_OUTER));
        return this;

    }

    public Flux<T> rightJoin(Function<T, Object> function) {
        joinStatements.add(new JoinStatement<>(supplier, this, function, JoinType.RIGHT_OUTER));
        return this;

    }

    public Flux<T> fullJoin(Function<T, Object> function) {
        joinStatements.add(new JoinStatement<>(supplier, this, function, JoinType.FULL_OUTER));
        return this;
    }

    public WhereStatement<T> where(Function<T, Object> function) {
        return whereStatement = new WhereStatement<>(supplier, function, this);
    }

    public WhereStatement<T> and(Function<T, Object> function) {
        WhereStatement<T> lastStatement = whereStatement.getLast();
        lastStatement.setOperator(WhereConcatOperatorEnum.AND);
        WhereStatement<T> newStatement = new WhereStatement<>(supplier, function, this);
        lastStatement.setNext(newStatement);
        return newStatement;
    }

    public WhereStatement<T> or(Function<T, Object> function) {
        WhereStatement<T> lastStatement = whereStatement.getLast();
        lastStatement.setOperator(WhereConcatOperatorEnum.OR);
        WhereStatement<T> newStatement = new WhereStatement<>(supplier, function, this);
        lastStatement.setNext(newStatement);
        return newStatement;
    }

    public OrderByStatement<T> orderBy(Function<T, Object> function) {
        OrderByStatement<T> statement = new OrderByStatement<>(supplier, function, this);
        orderByStatements.add(statement);
        return statement;
    }


    public Dialect getDialect() {
        return dialect;
    }


    public String toQuery() throws Exception {
        for (EnumCheckList check : EnumCheckList.values()) {
            check.check(this);
        }
        Translator translator = getTranslator();
        String translate = translator.translate(this);
        clean();

        return translate;
    }

    private void clean() {
        selectStatement = null;
        fromStatement = null;
        joinStatements.clear();
        whereStatement = null;
        groupByStatement = null;
        havingStatement = null;
        orderByStatements.clear();
    }

    public SelectStatement<T> getSelectStatement() {
        return selectStatement;
    }

    public FromStatement<T> getFromStatement() {
        return fromStatement;
    }

    public List<JoinStatement<T>> getJoinStatements() {
        return joinStatements;
    }

    public WhereStatement<T> getWhereStatement() {
        return whereStatement;
    }

    public GroupByStatement<T> getGroupByStatement() {
        return groupByStatement;
    }

    public HavingStatement<T> getHavingStatement() {
        return havingStatement;
    }

    public List<OrderByStatement<T>> getOrderByStatements() {
        return orderByStatements;
    }

    public Translator getTranslator() {
        return dialect.getTranslator();
    }

    public String getClassName() {
        return supplier.get().getClass().getSimpleName();
    }

    public List<T> toList() throws Exception {
        return null;
    }
}
