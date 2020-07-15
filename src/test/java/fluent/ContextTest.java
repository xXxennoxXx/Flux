package fluent;

import entities.Genre;
import entities.Movie;
import org.junit.Test;

import static utils.StringUtil.show;

public class ContextTest {

    @Test
    public void where() {

        Context context = new Context();
        String s = context.movies
                .whereOld(e -> e.getId() == 1)
                .toQuery();
        show(s);

    }

    @Test
    public void whereTwo() {

        Context context = new Context();
        String s = context.movies
                .where(Movie::getId)
                .eq(15)
                .toQuery();
//        context.movies
//                .where(e -> e.getTitle())
//                .toQuery();
        show(s);

    }


    @Test
    public void isChanging() {
        Movie movie = new Movie();
        int id = movie.getId();
        movie.setId(1);
        int id1 = movie.getId();
        show(id,
                id1);
    }

    @Test
    public void isChangingByWhere() {
        Movie movie = new Movie();

        DbSet.Where<Movie> wh = new DbSet.Where<Movie>() {
            @Override
            public Object apply(Movie o) {
                return movie.getId();
            }
        };

        movie.setId(1);
        Object apply = wh.apply(movie);
        show(movie.getId(),
                apply.toString());
        movie.setId(2);
        show(movie.getId(),
                apply.toString());

    }

    @Test
    public void whatFieldThereAre() {
        DbSet<A> as = new DbSet<>(A::new);

        String s = as
                .where(A::getI)
                .eq(10)
                .toQuery();

        show(s);

    }

}

class A {
    int i, i2;
    short j, j2;
    String s, s2;
    boolean b, b2;
    Genre g1, g2;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public short getJ() {
        return j;
    }

    public void setJ(short j) {
        this.j = j;
    }

    public short getJ2() {
        return j2;
    }

    public void setJ2(short j2) {
        this.j2 = j2;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isB2() {
        return b2;
    }

    public void setB2(boolean b2) {
        this.b2 = b2;
    }

    public Genre getG1() {
        return g1;
    }

    public void setG1(Genre g1) {
        this.g1 = g1;
    }

    public Genre getG2() {
        return g2;
    }

    public void setG2(Genre g2) {
        this.g2 = g2;
    }
}