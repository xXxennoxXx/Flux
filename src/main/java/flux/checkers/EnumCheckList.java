package flux.checkers;

import flux.Flux;
import flux.WhereType;
import flux.fieldholders.FieldHolder;
import flux.statments.WhereStatement;

import java.lang.reflect.Field;

public enum EnumCheckList {

    Is_Group_By_Contain_Proper_Select("Select contains the fields which are not in the group by statement.") {
        @Override
        public <T> boolean check(Flux<T> flux) throws Exception {
            return true;
        }
    },
    Is_Where_Field_Have_Same_Type_With_Condition("Value given in the where condition is/are not proper to the field type.") {
        @Override
        public <T> boolean check(Flux<T> flux) throws Exception {

            WhereStatement<T> whereStatement = flux.getWhereStatement();

            while (whereStatement != null) {
                FieldHolder fieldHolder = whereStatement.getWhereFieldHolder().getFieldHolder();

                WhereType type = whereStatement.getWhereFieldHolder().getWhereType();

                Object values = whereStatement.getWhereFieldHolder().getValues();

                Field field;
                if (fieldHolder.getSubField() == null)
                    field = fieldHolder.getMainField();
                else field = fieldHolder.getSubField();

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
                whereStatement = whereStatement.getNext();
            }
            return true;
        }
    };

    String errorMsg;

    EnumCheckList(String msg) {
        errorMsg = msg;
    }

    public abstract <T> boolean check(Flux<T> flux) throws Exception;


    public void throwException() throws Exception {
        throw new Exception(errorMsg);
    }


}
