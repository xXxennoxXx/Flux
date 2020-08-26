package fluent.evaluators;

import entities.Movie;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static utils.StringUtil.show;

public class FieldEvaluatorTest {

    @Test
    public void init() {

        FieldEvaluator<ClassToEvaluate> ev = new FieldEvaluator<>(ClassToEvaluate::new);

        show(
                ev.findQueryField(ClassToEvaluate::getString),
                ev.findQueryField(ClassToEvaluate::getMovie),
                ev.findQueryField(ClassToEvaluate::getInteger),
                ev.findQueryField(ClassToEvaluate::getGenre),
                ev.findQueryField(e -> e.getGenre().getName()),
                ev.findQueryField(e -> e.getGenre().getMovies()),
                ev.findQueryField(e -> e.getGenre().getMovies().size()),
                ev.findQueryField(e -> e.getMovie().getGenre())
//                ev.findQueryField(e->e.)
        );


    }
}