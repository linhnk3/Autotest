package ultilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadConfig {
    private static Properties CONFIG = null;
    private static String fSeparator = File.separator;
    private String propertiesFile;


    /*
     * @param -> file :  đưa file cần đọc vào
     * @param -> prokey : là key config trong file là gì cần đọc ,ví dụ có 2 key : qc ,uat ,đưa thằng nào thì nó đọc thằng đó .
     *
     */
    public static String getProperty(String file, String proKey) {
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(System.getProperty("user.dir") + fSeparator + "/src/test/resources" + fSeparator + file);
            Properties props = new Properties();
            props.load(fs);
            String prop = (String) props.get(proKey);
            return prop;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
