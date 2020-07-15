package fluent;

import entities.Movie;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static utils.StringUtil.show;

public class DbSet<T> extends HashSet<T> {

    private String query;
    private Where<T> w1;
    private Object o1;
    private Supplier<T> supplier;
    private T t;

    public DbSet(Supplier<T> supplier) {
        this.query = "Query: ";
        this.supplier = supplier;
    }


    public DbSet<T> where(Where<T> w) {
        w1 = w;
        return this;
    }

    public DbSet<T> eq(Object o) {

        o1 = o;
        return this;
    }

    //Wyciagnac wartosc predicate.test ze srodka?
    public DbSet<T> whereOld(Predicate<T> predicate) {
        query.concat(predicate.toString());
//        predicate.getClass().
        return this;
    }


    public String toQuery() {
        t = supplier.get();
        Field[] declaredFields = t.getClass().getDeclaredFields();

        Field declaredField = declaredFields[0];
        show(declaredField.getName(),
                declaredField.hashCode(),
                declaredField.toString(),
                declaredField.getAnnotatedType(),
                declaredField.getDeclaringClass(),
                declaredField.getGenericType(),
                declaredField.isAccessible(),
                declaredField.getModifiers(),
                "-------------------");

        Object apply = w1.apply(t);


//        show(apply.hashCode(),
//                declaredField.hashCode(),
//        "-------------------");
        boolean found = false;
        String name = "";
        int m, n;
        try {

            for (Field field : declaredFields) {
                declaredField = field;
                declaredField.setAccessible(true);
                Object o = declaredField.get(t);
                if (o == null) {
                    declaredField.set(t, declaredField.getType().newInstance());
                    try {
                        m = w1.apply(t).hashCode();
                        set(declaredField);
                        n = w1.apply(t).hashCode();
                        if (m != n) {
                            name =
                                    declaredField.getName();
                            found = true;
                        } else if (!found) {
                            name = declaredField.getName();
                            found = true;
                        }
                    } catch (NullPointerException e) {
                    }

                } else {
                    try {
                        m = w1.apply(t).hashCode();
                        set(declaredField);
                        n = w1.apply(t).hashCode();
                        if (m != n) {
                            name =
                                    declaredField.getName();
                            found = true;
                        }
                    } catch (NullPointerException e) {
                    }
                }
            }
            show("-----------------",
                    "Query: " + name + " : " + o1.toString(),
                    "-----------------");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        int length = t
                .getClass()
                .getName()
                .split("\\.").length;
        show(Arrays.toString(
                t.getClass()
                        .getName()
                        .split("\\.")
        ));

        StringBuilder sb = new StringBuilder();
        sb
                .append("SELECT")
                .append(" * ")
                .append("FROM ")
                .append(t
                        .getClass()
                        .getName()
                        .split("\\.")[length - 1]
                )
                .append("\n")
                .append("WHERE")
                .append(" ")
                .append(name)
                .append(" = ")
                .append(o1.toString());
//.split(".")
//                        [t
//                        .getClass()
//                        .getName()
//                        .split(".")
//                        .length - 1])

        return sb.toString();
    }

    interface Where<T> {
        public Object apply(T t);
    }

    private String set(Field field) throws IllegalAccessException {
        String type = field.getType().toString();
        if (type.equals("int")) {
            field.set(t, 1);
            return "int";
        } else if (type.equals("byte")) {
            field.set(t, (byte) 1);
            return "byte";
        } else if (type.equals("short")) {
            field.set(t, (short) 1);
            return "short";
        } else if (type.equals("long")) {
            field.set(t, (long) 1);
            return "long";
        } else if (type.equals("float")) {
            field.set(t, (float) 1);
            return "float";
        } else if (type.equals("double")) {
            field.set(t, (double) 1);
            return "double";
        } else if (type.equals("boolean")) {
            field.set(t, true);
            return "boolean";
        } else if (type.equals("char")) {
            field.set(t, (char) 1);
            return "char";
        }
        return "null";

    }

}
