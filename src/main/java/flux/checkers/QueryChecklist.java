package flux.checkers;

import flux.ConditionType;
import flux.Flux;
import flux.QueryException;
import flux.fieldholders.WhereFieldHolder;
import org.hibernate.HibernateException;

import java.lang.reflect.Field;
import java.util.List;

public enum QueryChecklist {

    Is_Group_By_Contain_Proper_Select("Select contains the fields which are not in the group by statement.") {
        @Override
        public <T> void check(Flux<T> flux) throws QueryException {
        }
    },
    Is_Where_Field_Have_Same_Type_With_Condition("Value given in the where condition is/are not proper to the field type.") {
        @Override
        public <T> void check(Flux<T> flux) throws QueryException {

            List<WhereFieldHolder> whereFields = flux.getWhereFields();
            for (WhereFieldHolder fieldHolder : whereFields) {

                ConditionType type = fieldHolder.getConditionType();

                Object values = fieldHolder.getValues();

                Field field;
                if (fieldHolder.getFieldHolder().getSubField() == null)
                    field = fieldHolder.getFieldHolder().getMainField();
                else field = fieldHolder.getFieldHolder().getSubField();

                switch (type) {
                    case EQUAL:
                    case NOT_EQUAL:
                    case LESS_THEN:
                    case GREATER_THEN:
                        Class<?> conditionType = values.getClass();
                        Class<?> fieldType = field.getType();
                        if (conditionType != fieldType)
                            throwException();
                        break;
                    case BETWEEN:
                    case NOT_BETWEEN:
                    case IN:
                    case NOT_IN:
                        Object[] betweenValues = (Object[]) values;
                        fieldType = field.getType();
                        for (Object o : betweenValues) {
                            conditionType = o.getClass();
                            if (conditionType != fieldType)
                                throwException();
                        }
                }
            }
        }
    };

    String errorMsg;

    QueryChecklist(String msg) {
        errorMsg = msg;
    }

    public abstract <T> void check(Flux<T> flux) throws QueryException;


    public void throwException() throws HibernateException {
        throw new HibernateException(errorMsg);
    }


}
