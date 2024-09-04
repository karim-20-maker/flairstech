package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties properties;
    private static  String getPropertyValue(String key , File file){
        properties = new Properties();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            properties.load(bufferedReader);
        }catch (FileNotFoundException fileExc){
            fileExc.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }
    public static String readEnvFile(String key){
        File file = new File("src/config/env.properties");
        return getPropertyValue(key,file);
    }

}
