package flux.translators.hibernate;

import flux.fieldholders.JoinFieldHolder;
import flux.statments.JoinStatement;
import flux.translators.JoinTranslator;

import java.util.List;

public class JoinHibernateTranslator implements JoinTranslator {
    @Override
    public <T> String translate(List<JoinFieldHolder> joinStatements) {
        if (joinStatements.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();

        for (JoinFieldHolder statement : joinStatements)
            sb.append(statement.getJoinType())
                    .append("\n\t")
                    .append(statement.returnName())
                    .append("\n");
        return sb.toString();
    }
}
