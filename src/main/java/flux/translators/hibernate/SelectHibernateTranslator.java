package flux.translators.hibernate;

import flux.fieldholders.FieldHolder;
import flux.translators.SelectTranslator;

import java.util.List;

public class SelectHibernateTranslator implements SelectTranslator {
    @Override
    public String translate(List<FieldHolder> fields) {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT");
        for (FieldHolder field : fields)
            sb.append("\n\t")
                    .append(field.returnName())
                    .append(",");
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }
}
