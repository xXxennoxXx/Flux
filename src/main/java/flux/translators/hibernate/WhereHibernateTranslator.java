package flux.translators.hibernate;

import flux.WhereType;
import flux.fieldholders.WhereFieldHolder;
import flux.statments.WhereStatement;
import flux.translators.WhereTranslator;

public class WhereHibernateTranslator implements WhereTranslator {
    @Override
    public <T> String translate(WhereStatement<T> whereStatement) {
        if (whereStatement == null)
            return "";
        StringBuilder sb = new StringBuilder();

        sb.append("WHERE");
        do {
            WhereFieldHolder whereFieldHolder = whereStatement.getWhereFieldHolder();
            sb.append("\n\t")
                    .append(evaluate(whereFieldHolder));

            if (whereStatement.hasNext())
                sb.append("\n\t")
                        .append(whereStatement.getOperator());

            whereStatement = whereStatement.getNext();
        } while (whereStatement != null);

        sb.append("\n");
        return sb.toString();
    }

    private String evaluate(WhereFieldHolder whereFieldHolder) {

        String field = whereFieldHolder.getFieldHolder().returnName();

        String condition = evaluateWhere(whereFieldHolder.getWhereType(), whereFieldHolder.getValues());
        return field + condition;
    }

    private String evaluateWhere(WhereType type, Object values) {
        StringBuilder sb = new StringBuilder();

        switch (type) {
            case EQUAL:
                sb.append(" = ").append(values);
                break;
            case NOT_EQUAL:
                sb.append(" != ").append(values);
                break;
            case LESS_THEN:
                sb.append(" < ").append(values);
                break;
            case GREATER_THEN:
                sb.append(" > ").append(values);
                break;
            case BETWEEN:
                Object[] vals = (Object[]) values;
                sb.append(" BETWEEN ").append(vals[0]).append(" AND ").append(vals[1]);
                break;
            case NOT_BETWEEN:
                vals = (Object[]) values;
                sb.append(" NOT BETWEEN ").append(vals[0]).append(" AND ").append(vals[1]);
                break;
            case IN:
                vals = (Object[]) values;
                sb.append(" IN (");
                for (Object o : vals)
                    sb.append(" ").append(o.toString()).append(",");
                sb.deleteCharAt(sb.length() - 1)
                        .append(")");
                break;
            case NOT_IN:
                vals = (Object[]) values;
                sb.append(" NOT IN (");
                for (Object o : vals)
                    sb.append(" ").append(o.toString()).append(",");
                sb.deleteCharAt(sb.length() - 1)
                        .append(")");
                break;
            default:
                sb.append("Something want wrong.");
        }
        return sb.toString();
    }
}
