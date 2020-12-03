package github.zjy.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @ClassName PropertiesFileUtil
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/3 下午5:01
 **/
public final class PropertiesFileUtil {
    private PropertiesFileUtil() {
    }

    public static Properties readPropertiesFile(String fileName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        String rpcConfigPath = "";
        if (url != null) {
            rpcConfigPath = url.getPath().concat(fileName);
        }
        Properties properties = new Properties();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(rpcConfigPath), StandardCharsets.UTF_8);
            properties.load(inputStreamReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
