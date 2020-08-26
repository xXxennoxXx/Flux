package fluent;

import fluent.fieldholders.WhereConcatOperatorEnum;
import fluent.statments.*;
import fluent.translators.Dialect;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

//TODO Zeby zapewnic sensowane API biblioteki wszystko powinno byc w jednej paczce?


public class Flux<T> {

    private final Dialect dialect;
    private final Supplier<T> supplier;
    private final List<Statement<T>> statements;
    private SelectStatement<T> selectStatement;
    private FromStatement<T> fromStatement;
    private JoinStatement<T> joinStatement;
    private WhereStatement<T> whereStatement;
    private GroupByStatement<T> groupByStatement;
    private HavingStatement<T> havingStatement;
    private OrderByStatement<T> orderByStatement;

    public Flux(Dialect dialect, Supplier<T> supplier) {
        this.dialect = dialect;
        this.supplier = supplier;
        statements = new ArrayList<>();
    }

    public void addStatement(Statement<T> statement) {
        statements.add(statement);
    }

    //    TODO O Co chodzi
    @SafeVarargs
    public final Flux<T> select(Function<T, Object>... functions) {
        selectStatement = new SelectStatement<T>(supplier, this, functions);
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
        return new OrderByStatement<>(supplier, function, this);
    }

    public Dialect getDialect() {
        return dialect;
    }

    public List<Statement<T>> getStatements() {
        return statements;
    }

    @Override
    public String toString() {


        StringBuilder sb = new StringBuilder();
        for (Statement<T> s : statements) {
            sb.append("\n")
                    .append(s.toString());
        }
        return sb.toString();
    }

    public SelectStatement<T> getSelectStatement() {
        return selectStatement;
    }

    public FromStatement<T> getFromStatement() {
        return fromStatement;
    }

    public JoinStatement<T> getJoinStatement() {
        return joinStatement;
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

    public OrderByStatement<T> getOrderByStatement() {
        return orderByStatement;
    }
}
