package flux.translators.hibernate;

import flux.fieldholders.FieldHolder;
import flux.fieldholders.FromFieldHolder;
import flux.translators.FromTranslator;

import java.util.List;
import java.util.Map;

public class FromHibernateTranslator implements FromTranslator {


    @Override
    public String translate(List<FromFieldHolder> fromFields) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM");
        Map<String, String> charToClass = FieldHolder.getCharToClass();
        for (FromFieldHolder fromFieldHolder : fromFields) {
            String className = fromFieldHolder.getFieldHolder().getClassName();
            String s = charToClass.get(className);

            sb
                    .append("\n\t")
                    .append(className)
                    .append(" ")
                    .append(s)
                    .append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }
}
