package flux.translators;

import flux.checkers.QueryHibernateCheckList;
import flux.translators.hibernate.*;
import flux.translators.sql.SQLTranslator;

public enum Dialect {
    HIBERNATE {


        @Override
        public SelectTranslator getSelectTranslator() {
            return new SelectHibernateTranslator();
        }

        @Override
        public FromTranslator getFromTranslator() {
            return new FromHibernateTranslator();
        }

        @Override
        public JoinTranslator getJoinTranslator() {
            return new JoinHibernateTranslator();
        }

        @Override
        public WhereTranslator getWhereTranslator() {
            return new WhereHibernateTranslator();
        }

        @Override
        public GroupByTranslator getGroupByTranslator() {
            return new GroupByHibernateTranslator();
        }

        @Override
        public HavingTranslator getHavingTranslator() {
            return new HavingHibernateTranslator();
        }

        @Override
        public OrderByTranslator getOrderByTranslator() {
            return new OrderByHibernateTranslator();
        }

        @Override
        public Translator getTranslator() {
            return new HibernateTranslator();
        }
    },
    SQL_SERVER {
        @Override
        public SelectTranslator getSelectTranslator() {
            return null;
        }

        @Override
        public FromTranslator getFromTranslator() {
            return null;
        }

        @Override
        public JoinTranslator getJoinTranslator() {
            return null;
        }

        @Override
        public WhereTranslator getWhereTranslator() {
            return null;
        }

        @Override
        public GroupByTranslator getGroupByTranslator() {
            return null;
        }

        @Override
        public HavingTranslator getHavingTranslator() {
            return null;
        }

        @Override
        public OrderByTranslator getOrderByTranslator() {
            return null;
        }

        @Override
        public Translator getTranslator() {
            return new SQLTranslator();
        }
    };


    public abstract SelectTranslator getSelectTranslator();

    public abstract FromTranslator getFromTranslator();

    public abstract JoinTranslator getJoinTranslator();

    public abstract WhereTranslator getWhereTranslator();

    public abstract GroupByTranslator getGroupByTranslator();

    public abstract HavingTranslator getHavingTranslator();

    public abstract OrderByTranslator getOrderByTranslator();


    public abstract Translator getTranslator();


}

