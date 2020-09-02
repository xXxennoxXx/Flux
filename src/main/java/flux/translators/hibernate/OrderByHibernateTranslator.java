package flux.translators.hibernate;

import flux.fieldholders.OrderByFieldHolder;
import flux.statments.OrderByStatement;
import flux.translators.OrderByTranslator;

import java.util.List;

public class OrderByHibernateTranslator implements OrderByTranslator {
    @Override
    public String translate(List<OrderByFieldHolder> fields) {
        StringBuilder sb = new StringBuilder();
//        sb.append("ORDER BY");
//        for (OrderByStatement<T> statement : fields)
//            sb.append("\n\t")
//                    .append(statement.getFieldHolder().returnName())
//                    .append(" ")
//                    .append(statement.getType().toString())
//                    .append(",");
//        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
