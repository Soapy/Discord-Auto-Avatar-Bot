package autoavatarbot.util;

import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;

public class LoadingProperties {
    private String token;
    private String userID;
    private String password;
    private int timeToSwitch;

    public LoadingProperties() {
        try {
            File config = new File("config.txt");
            FileInputStream fileIO = new FileInputStream(config);
            Properties properties = new Properties();
            properties.load(fileIO);
            fileIO.close();

            token = properties.getProperty("token");
            userID = properties.getProperty("userID");
            password = properties.getProperty("password");
            try {
                timeToSwitch = Integer.parseInt(properties.getProperty("time_to_switch"));
                if(timeToSwitch < 10) {
                    timeToSwitch = 10;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                timeToSwitch = 10;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return token;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public int getTimeToSwitch() {
        return timeToSwitch;
    }
}
