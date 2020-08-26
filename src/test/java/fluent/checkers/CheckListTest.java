package fluent.checkers;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

import static utils.StringUtil.show;

public class CheckListTest {

    @Test
    public void methods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CheckList<Integer> checkList = new CheckList<>();

//        Method getMethods = checkList.getClass().getMethod("getMethods");
        Method getMethods = checkList.getClass().getDeclaredMethod("getMethods");
        getMethods.setAccessible(true);
        List<Method> invoke = (List<Method>) getMethods.invoke(checkList);

        for (Method m : invoke)
            show(m.getName());


        Function<String, Integer> f = s -> Integer.parseInt(s);


    }

    static Integer lambda$1(String s) {
        return Integer.parseInt(s);
    }

}


