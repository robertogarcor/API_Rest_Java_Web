
package com.rgc.nvrservicesjws.utils;

import com.sun.org.apache.xalan.internal.utils.SecuritySupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.objects.NativeDebug;

/**
 * @author RGC
 * @date 7-jun-2020
 */
public class Utils {
    
    /**
     * Convert Format Date to Datetime for DB
     * @param date object Date
     * @return object String Datetime
     */
    public static String convertFormatDateToDateTime(LocalDate date) {
        try {
            if (date != null) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert Format DateTime DB to Date
     * @param datetime object String
     * @return object Date
     */
    public static Date convertFormatStringDateTimeToDate(String datetime) {
        try {
            if (datetime != null) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static Properties loadDBProperties() {
        final Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(Utils.class.getResourceAsStream("/db.properties")));
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
        
    }
    
    
    
}
