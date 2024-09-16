package Common;


import Utilities.PropertiesLoader;

public class ReadProperties {
    public static final String URL = getPropertyFromEnv("URL");
    public static final String username = getPropertyFromEnv("username");
    public static final String password = getPropertyFromEnv("password");
    public static final String employeeName = getPropertyFromEnv("employeeName");
    public static final String APIURL = getPropertyFromEnv("APIURL");



    private static String getPropertyFromEnv(String propertyName) {
        return System.getProperty(propertyName, PropertiesLoader.readEnvFile(propertyName));
    }



}
