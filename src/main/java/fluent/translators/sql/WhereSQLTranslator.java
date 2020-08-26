package fluent.translators.sql;

import fluent.Flux;
import fluent.WhereType;
import fluent.translators.WhereTranslator;

public class WhereSQLTranslator extends WhereTranslator {
    public WhereSQLTranslator(WhereType type, Object one) {
        super(type, one);
    }

    public WhereSQLTranslator(WhereType type, Object bottom, Object top) {
        super(type, bottom, top);
    }

    public WhereSQLTranslator(WhereType type, Object[] in) {
        super(type, in);
    }


    @Override
    public <T> String translate(Flux<T> flux) {
        StringBuilder sb = new StringBuilder();

        switch (type) {
            case EQUAL:
                sb.append(" = ").append(one.toString());
                break;
            case NOT_EQUAL:
                sb.append(" != ").append(one.toString());
                break;
            case LESS_THEN:
                sb.append(" < ").append(one.toString());
                break;
            case GREATER_THEN:
                sb.append(" > ").append(one.toString());
                break;
            case BETWEEN:
                sb.append(" BETWEEN ").append(bottom.toString()).append(" AND ").append(top);
                break;
            case NOT_BETWEEN:
                sb.append(" NOT BETWEEN ").append(bottom.toString()).append(" AND ").append(top);
                break;
            case IN:
                sb.append(" IN (");
                for (Object o : in)
                    sb.append(" ").append(o.toString()).append(",");
                sb.deleteCharAt(sb.length() - 1)
                        .append(")");
                break;
            case NOT_IN:
                sb.append(" NOT IN (");
                for (Object o : in)
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
