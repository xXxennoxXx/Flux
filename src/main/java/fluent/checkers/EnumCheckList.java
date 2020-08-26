package fluent.checkers;

import fluent.Flux;

public enum EnumCheckList {

    Is_Group_By_Contain_Proper_Select("Select contains the fields which are not in the group by statement.") {
        @Override
        public <T> boolean check(Flux<T> flux) {
            return false;
        }
    },
    Is_Where_Field_Have_Same_Type_With_Condition("Value given in the where condition is/are not proper to the field type.") {
        @Override
        public <T> boolean check(Flux<T> flux) {
            return false;
        }
    };

    String errorMsg;

    EnumCheckList(String msg) {
        errorMsg = msg;
    }

    public abstract <T> boolean check(Flux<T> flux) throws Exception;

    public void throwException(String msg) throws Exception {
        throw new Exception(msg);
    }
}
