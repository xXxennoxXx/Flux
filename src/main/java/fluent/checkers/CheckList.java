package fluent.checkers;

import fluent.Flux;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckList<T> {

    public CheckList() {
    }

    public boolean test(Flux<T> flux) throws Exception {
        {
            for (Method m : getMethods())
                m.invoke(flux);
//            return true;
        }
        {
            for (EnumCheckList check : EnumCheckList.values())
                check.check(flux);
//            return true;
        }
        return true;
    }

    private List<Method> getMethods() {
        Method[] declaredMethods = CheckList.class.getDeclaredMethods();
//        return (Method[]) Arrays.stream(declaredMethods).filter(m -> !m.getName().equals("test") && !m.getName().equals("getMethods")).toArray();
        List<Method> methods = Arrays.asList(declaredMethods);
        return methods.stream().filter(m -> !m.getName().equals("test") && !m.getName().equals("getMethods") && !m.getName().equals("lambda$getMethods$0")).collect(Collectors.toCollection(ArrayList::new));

    }

    private void test1() {

    }

    private void test2() {

    }
}
