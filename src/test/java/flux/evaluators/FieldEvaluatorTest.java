package flux.evaluators;

import flux.entities.ClassToEvaluate;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static flux.utils.StringUtil.show;

public class FieldEvaluatorTest {

    @Test
    public void init() {
        FieldEvaluator<ClassToEvaluate> ev = new FieldEvaluator<>(ClassToEvaluate::new);

        Assertions.assertEquals(ev.findQueryField(ClassToEvaluate::getString).returnName(), "c.string");
        Assertions.assertEquals(ev.findQueryField(ClassToEvaluate::getMovie).returnName(), "c.movie");
        Assertions.assertEquals(ev.findQueryField(ClassToEvaluate::getInteger).returnName(), "c.integer");
        Assertions.assertEquals(ev.findQueryField(ClassToEvaluate::getGenre).returnName(), "c.genre");
        Assertions.assertEquals(ev.findQueryField(e -> e.getGenre().getName()).returnName(), "g.name");
        Assertions.assertEquals(ev.findQueryField(e -> e.getGenre().getMovies()).returnName(), "c.genre");
        Assertions.assertEquals(ev.findQueryField(e -> e.getGenre().getMovies().size()).returnName(), "c.genre");
        Assertions.assertEquals(ev.findQueryField(e -> e.getMovie().getGenre()).returnName(), "m.genre");
    }
}