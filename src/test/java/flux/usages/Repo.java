package flux.usages;

import flux.Flux;
import flux.translators.Dialect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;

public class Repo<T> extends Flux<T> {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public Repo(Dialect dialect, Supplier<T> supplier) {
        super(dialect, supplier);
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return getSessionFactory().openSession();
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }


    @Override
    public List<T> toList() throws Exception {
        try (Session session = getSession()) {
            session.beginTransaction();
            List resultList = session.createQuery(toQuery()).getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }
}
