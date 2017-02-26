package org.testing.framework.properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class LoadProjectProperties {

    private static final Resource resource = new ClassPathResource("/project.properties");
    private static Properties props;

    public static final String WEBDRIVER_PAGELOAD_MAXTIMEOUT_SEC = "webdriver.pageload.maxtimeout.sec";
    public static final String WEBDRIVER_ELEMENTLOAD_MAXTIMEOUT_SEC = "webdriver.elementload.maxtimeout.sec";
    public static final String ASSERT_TEXT_DELIMITER = "assert.text.delimiter";
    public static final String REPLACE_TEXT_DELIMITER = "replace.text.delimiter";
    public static final String REPLACE_CHARACTER = "replace.character";
    public static final String REPLACE_CHARACTER_2 = "replace.character.2";
//    public static final String AJAX_BUSY_IDENTIFIER = "ajax.busy.identifier";

    public static String getStringProperty(String identifier) throws IOException {
        if (props == null) {
            props = PropertiesLoaderUtils.loadProperties(resource);
        }
        return props.getProperty(identifier);
    }

    public static long getNumberProperty(String identifier) throws IOException {
        if (props == null) {
            props = PropertiesLoaderUtils.loadProperties(resource);
        }
        return Long.parseLong(props.getProperty(identifier));
    }
}
