package flux.translators.hibernate;

import flux.fieldholders.FieldHolder;
import flux.fieldholders.SelectFieldHolder;
import flux.translators.SelectTranslator;

import java.util.List;

public class SelectHibernateTranslator implements SelectTranslator {
    @Override
    public String translate(List<SelectFieldHolder> fields) {
        if (fields.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT");
        for (SelectFieldHolder field : fields)
            sb.append("\n\t")
                    .append(field.returnName())
                    .append(",");
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }
}
