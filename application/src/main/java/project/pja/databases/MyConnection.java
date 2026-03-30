package pja.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;

public class MyConnection {
    
    private static final Properties CONFIG = new Properties();

    static {
        // Try loading application.properties from classpath and common locations
        String[] candidates = new String[] {
            "application.properties",
            "WEB-INF/application.properties",
            "src/main/resources/application.properties",
            "src/main/webapp/WEB-INF/application.properties",
            "application.properties"
        };

        for (String path : candidates) {
            try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
                if (is != null) {
                    CONFIG.load(is);
                    break;
                }
            } catch (Exception e) {
                // ignore and try filesystem paths next
            }
            try (InputStream fis = new FileInputStream(new File(path))) {
                CONFIG.load(fis);
                break;
            } catch (Exception e) {
                // ignore and try next candidate
            }
        }
    }

    // Read env var first, then fallback to application.properties, then default
    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value != null && !value.isEmpty()) return value;
        value = CONFIG.getProperty(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    private static int getEnvOrDefaultInt(String key, int defaultValue) {
        String s = getEnvOrDefault(key, Integer.toString(defaultValue));
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static final String DB_HOST = getEnvOrDefault("DB_HOST", "db");
    private static final int DB_PORT = getEnvOrDefaultInt("DB_PORT", 5432);
    private static final String DB_NAME = getEnvOrDefault("DB_NAME", "newspaper");
    private static final String DB_USER = getEnvOrDefault("DB_USER", "postgres");
    private static final String DB_PASSWORD = getEnvOrDefault("DB_PASSWORD", "postgres");
    
    public static Connection connect() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = String.format("jdbc:postgresql://%s:%d/%s", DB_HOST, DB_PORT, DB_NAME);
        System.out.println("Connecting to: " + url);
        return DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
    }
    
    // Méthode pour connexion personnalisée
    public static Connection connect(String host, int port, String dbName, String user, String password) throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = String.format("jdbc:postgresql://%s:%d/%s", host, port, dbName);
        return DriverManager.getConnection(url, user, password);
    }
}