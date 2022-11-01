package org.example.lineNumbersParser;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesServices {
    private static final String nameFileProperties="D:\\MPKPARSERNEW\\untitled\\config.properties";

    private static Properties prop = new Properties();
    private static InputStream input = null;

    private static PropertiesServices singeltonProperties;

    public String getPropertyAsString(String nameProperty){
            return prop.getProperty(nameProperty);
    }

    private PropertiesServices(){
        try {

            input = new FileInputStream(nameFileProperties);
            System.out.println(nameFileProperties);
            prop.load(input);


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }




    public static PropertiesServices getProperties() {
        if(singeltonProperties==null){
            singeltonProperties= new PropertiesServices();
        }
        return singeltonProperties;

    }



}
