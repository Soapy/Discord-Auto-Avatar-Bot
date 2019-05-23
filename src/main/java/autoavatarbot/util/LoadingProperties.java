package autoavatarbot.util;

import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;

public class LoadingProperties {
    private String token;
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
            password = properties.getProperty("password");
            //timeToSwitch will always be at minimum 9 in accordance to the rate limits declared in the Discord API
            try {
                timeToSwitch = Integer.parseInt(properties.getProperty("time_to_switch"));
                if(timeToSwitch < 9) {
                    timeToSwitch = 9;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                timeToSwitch = 9;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public int getTimeToSwitch() {
        return timeToSwitch;
    }
}
