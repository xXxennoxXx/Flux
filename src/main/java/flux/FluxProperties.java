package flux;

import flux.evaluators.FieldEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FluxProperties {
    private static final Logger logger = LoggerFactory.getLogger(FieldEvaluator.class);
    private final static String MAIN_PATH = "flux.";
    public final static String QUERY_DEPTH = "query_depth";

    private final static Properties appProps;

    static {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
            logger.debug("Wczytano poprawnie plik konfiguracyjny.");
        } catch (IOException e) {
            logger.info("Brak pliku konfiguracyjnego.");
        }
    }

    public static String getProperties(String key) {
        return appProps.getProperty(MAIN_PATH + key);
    }


}
