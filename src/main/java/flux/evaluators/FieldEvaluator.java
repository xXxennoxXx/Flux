package flux.evaluators;

import flux.FluxProperties;
import flux.fieldholders.FieldHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FieldEvaluator<T> {
    private static final Logger logger = LoggerFactory.getLogger(FieldEvaluator.class);
    private static final String JAVA_PACKAGE_REGEX = "(java.).*";
    private static final int QUERY_DEPTH;
    private Supplier<T> supplier;
    private Function<T, Object> function;
    private T t;

    static {
        String queryDepth = FluxProperties
                .getProperties(
                        FluxProperties.QUERY_DEPTH);
        if (queryDepth != null && !queryDepth.isEmpty())
            QUERY_DEPTH = Integer.parseInt(queryDepth);
        else
            QUERY_DEPTH = 5;
        logger.debug("Query depth: " + QUERY_DEPTH);
    }

    public FieldEvaluator(Supplier<T> supplier) {
        this.supplier = supplier;
        initializeParameterClassAttribute();
    }

    public FieldHolder findQueryField(Function<T, Object> function) {

        this.function = function;
        initializeParameterClassAttribute();
        Field field = null;


        for (Field f : getParameterClassFields()) {
            field = evaluateWhichAttribute(t, f);
            if (field != null)
                return new FieldHolder(t.getClass().getSimpleName(), field);
        }
        for (Field customField : getCustomFieldsInParameterClass(getParameterClassFields())) {
            Field[] fields = customField.getType().getDeclaredFields();
            Object o = null;
            try {
                customField.setAccessible(true);
                o = customField.get(t);
                fields = o.getClass().getDeclaredFields();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (Field innerField : fields) {
                field = evaluateWhichAttribute(o, innerField);
                if (field != null)
                    return new FieldHolder(t.getClass().getSimpleName(), customField, field);
            }
        }
        return null;
    }

    private Field evaluateWhichAttribute(Object o, Field field) {
        field.setAccessible(true);
        trySetFieldToNull(o, field);
        tryInitializeField(o, field);
        try {
            getTargetAttributeHashCode(function);
            return field;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private List<Field> getCustomFieldsInParameterClass(Field... fields) {
        List<Field> customFields = new ArrayList<>();
        for (Field f : fields) {
            if (!isFieldIsJavaType(f))
                customFields.add(f);
        }
        return customFields;
    }

    private boolean isFieldIsJavaType(Field field) {
        return field.getType().getPackage().getName().matches(JAVA_PACKAGE_REGEX);
    }

    private int getTargetAttributeHashCode(Function<T, Object> function) {
        return function.apply(t).hashCode();
    }

    private void tryInitializeField(Object o, Field field) {
        try {
            initializeField(o, field);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void initializeField(Object o, Field field) throws IllegalAccessException, InstantiationException {
        Object instanceOfClassField = getInstanceOfField(field);
        setFieldValue(o, field, instanceOfClassField);
    }

    private void trySetFieldToNull(Object o, Field field) {
        try {
            setFieldValue(o, field, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setFieldValue(Object o, Field field, Object value) throws IllegalAccessException {
        field.set(o, value);
    }

    private Object getInstanceOfField(Field field) throws IllegalAccessException, InstantiationException {

        Class<?> type = field.getType();

        if (Integer.class == type)
            return new Integer(1);
        else if (Long.class == type)
            return new Long(1);
        else if (String.class == type)
            return new String("Ala ma kota.");
        else if (Short.class == type)
            return new Short("1");
        else if (Float.class == type)
            return new Float(1.0);
        else if (Double.class == type)
            return new Double(1.0);
        else if (LocalDate.class == type)
            return LocalDate.now();
        else if (Set.class == type)
            return new HashSet<>();
        else
            return field.getType().newInstance();
    }


    private void initializeParameterClassAttribute() {
        t = supplier.get();
    }

    private Field[] getParameterClassFields() {
        return t.getClass().getDeclaredFields();
    }

    private List<Field> getClassFields(Class<?> c, boolean javaPack) {
        return Arrays.stream(c
                .getDeclaredFields())
                .filter(f -> {
                    if (javaPack)
                        return isFieldIsJavaType(f);
                    return true;
                })
                .filter(f -> !Modifier.isFinal(f.getModifiers()))
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                .collect(Collectors.toList());
    }

}
