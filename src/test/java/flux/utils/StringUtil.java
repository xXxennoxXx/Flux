package flux.utils;

public class StringUtil {

    public static void show(Object... args) {
        for (Object o : args)
            System.out.println(o.toString());
    }

}
