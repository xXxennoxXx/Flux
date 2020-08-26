package fluent.fieldholders;

import entities.Movie;
import fluent.evaluators.FieldEvaluator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldHolderTest {

    @Test
    public void test(){

        FieldEvaluator<Movie> f = new FieldEvaluator<>(Movie::new);
        FieldHolder queryField = f.findQueryField(e -> e.getId());

        queryField.toString();

    }

}