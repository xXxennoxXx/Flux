package fluent.translators;

import fluent.WhereType;
import fluent.statments.SelectStatement;
import fluent.statments.WhereStatement;
import fluent.translators.Translator;
import fluent.translators.sql.WhereSQLTranslator;

public enum Dialect {
    HIBERNATE {
        @Override
        public <T> WhereTranslator getWhereTranslator(WhereStatement<T> whereStatement) {
            return null;
        }

        @Override
        public <T> SelectTranslator getSelectTranslator(SelectStatement<T> selectStatement) {
            return null;
        }

        @Override
        public Translator getFromTranslator() {
            return null;
        }
    },
    SQL_SERVER {
        @Override
        public <T> WhereTranslator getWhereTranslator(WhereStatement<T> whereStatement) {
            return null;
        }

        @Override
        public <T> SelectTranslator getSelectTranslator(SelectStatement<T> selectStatement) {
            return null;
        }

        @Override
        public Translator getFromTranslator() {
            return null;
        }
    };

    public abstract <T> WhereTranslator getWhereTranslator(WhereStatement<T> whereStatement);

    public abstract <T> SelectTranslator getSelectTranslator(SelectStatement<T> selectStatement);

    public abstract Translator getFromTranslator();


}

