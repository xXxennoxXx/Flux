import entities.Movie;
import fluent.Context;

import java.time.LocalDate;

import static utils.StringUtil.show;

public class Test {

    @org.junit.Test
    public void testJavaLang() {
        show(Integer.class.getName(),
                String.class.getName(),
                Short.class.getName(),
                Float.class.getName(),
                Long.class.getName(),
                LocalDate.class.getName(),
                Context.class.getName()
        );
    }

    @org.junit.Test
    public void nullTest() {
        TestClass testClass = new TestClass();
        show("Nic");
    }

    @org.junit.Test
    public void enumTest() {
        show(EnumTest.A.toString());
        EnumTest.A.setName("B");
        show(EnumTest.A.toString());
    }

    class TestClass {
        Long aLong;
        String string;
        Short aShort;
        Float aFloat;
        LocalDate localDate;
    }

    enum EnumTest {
        A("A");
        String name;

        EnumTest(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "A: " + name;
        }
    }
}
