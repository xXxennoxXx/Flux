package fluent;

import java.util.Arrays;

public class WhereCondition {
    enum Type {IN, NOT_IN, BETWEEN, NOT_BETWEEN, EQUAL, NOT_EQUAL, LESS_THEN, GREATER_THEN}

    private Type type;
    private Object one, bottom, top;
    private Object[] in;

    public WhereCondition(Type type, Object one) {
        this.type = type;
        this.one = one;
    }

    public WhereCondition(Type type, Object bottom, Object top) {
        this.type = type;
        this.bottom = bottom;
        this.top = top;
    }

    public WhereCondition(Type type, Object[] in) {
        this.type = type;
        this.in = in;
    }

    @Override
    public String toString() {
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
