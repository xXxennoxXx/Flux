package flux.translators.hibernate;

import flux.ConditionType;
import flux.fieldholders.WhereFieldHolder;
import flux.translators.WhereTranslator;

import java.util.List;

public class WhereHibernateTranslator implements WhereTranslator {
    @Override
    public String translate(List<WhereFieldHolder> whereFieldHolders) {
        if (whereFieldHolders.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();

        sb.append("WHERE");

        for (WhereFieldHolder whereFieldHolder : whereFieldHolders) {
            sb.append("\n\t")
                    .append(evaluate(whereFieldHolder));
            if (whereFieldHolder.getWhereConcatOperatorEnum() != null)
                sb.append("\n\t")
                        .append(whereFieldHolder.getWhereConcatOperatorEnum());
        }
        sb.append("\n");
        return sb.toString();
    }

    private String evaluate(WhereFieldHolder whereFieldHolder) {

        String field = whereFieldHolder.getFieldHolder().returnName();

        String condition = evaluateWhere(whereFieldHolder.getConditionType(), whereFieldHolder.getValues());
        return field + condition;
    }

    private String evaluateWhere(ConditionType type, Object values) {
        StringBuilder sb = new StringBuilder();

        switch (type) {
            case EQUAL:
                if (values instanceof String)
                    values = "'" + values + "'";
                sb.append(" = ").append(values);
                break;
            case NOT_EQUAL:
                if (values instanceof String)
                    values = "'" + values + "'";
                sb.append(" != ").append(values);
                break;
            case LESS_THEN:
                if (values instanceof String)
                    values = "'" + values + "'";
                sb.append(" < ").append(values);
                break;
            case GREATER_THEN:
                if (values instanceof String)
                    values = "'" + values + "'";
                sb.append(" > ").append(values);
                break;
            case BETWEEN:
                Object[] vals = (Object[]) values;
                for (Object val : vals)
                    if (val instanceof String)
                        val = "'" + values + "'";
                sb.append(" BETWEEN ").append(vals[0]).append(" AND ").append(vals[1]);
                break;
            case NOT_BETWEEN:
                vals = (Object[]) values;
                for (Object val : vals)
                    if (val instanceof String)
                        val = "'" + values + "'";
                sb.append(" NOT BETWEEN ").append(vals[0]).append(" AND ").append(vals[1]);
                break;
            case IN:
                vals = (Object[]) values;
                for (Object val : vals)
                    if (val instanceof String)
                        val = "'" + values + "'";
                sb.append(" IN (");
                for (Object o : vals)
                    sb.append(" ").append(o.toString()).append(",");
                sb.deleteCharAt(sb.length() - 1)
                        .append(")");
                break;
            case NOT_IN:
                vals = (Object[]) values;
                for (Object val : vals)
                    if (val instanceof String)
                        val = "'" + values + "'";
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
