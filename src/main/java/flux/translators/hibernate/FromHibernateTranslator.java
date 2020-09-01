package flux.translators.hibernate;

import flux.fieldholders.FieldHolder;
import flux.translators.FromTranslator;

import java.util.Map;

public class FromHibernateTranslator implements FromTranslator {


    @Override
    public String translate(String className) {

        Map<String, String> charToClass = FieldHolder.getCharToClass();
        String s = charToClass.get(className);

        if (s == null)
            s = "";

        StringBuilder sb = new StringBuilder();

        sb.append("FROM")
                .append("\n\t")
                .append(className)
                .append(" ")
                .append(s)
                .append("\n");

        return sb.toString();
    }
}
