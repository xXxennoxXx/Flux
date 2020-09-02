package flux.fieldholders;

import flux.JoinType;

import java.lang.reflect.Field;

public class JoinFieldHolder extends CustomFieldHolder {

    private JoinType joinType;

    public JoinFieldHolder(FieldHolder fieldHolder, JoinType joinType) {
        super(fieldHolder);
        this.joinType = joinType;
    }

    public String returnName() {

        String s = fieldHolder.getClassChar() + "." + fieldHolder.getMainField().getName();

        s = s.concat(" ");
        String simpleName = fieldHolder.getMainField().getType().getSimpleName();
        String aChar = FieldHolder.getChar(simpleName);
        s = s.concat(aChar);
        return s;
    }

    public String getClassName() {
        return fieldHolder.getClassName();
    }

    public Field getMainField() {
        return fieldHolder.getMainField();
    }

    public JoinType getJoinType() {
        return joinType;
    }
}
