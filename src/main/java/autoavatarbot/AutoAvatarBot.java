package autoavatarbot;

import autoavatarbot.util.LoadingProperties;
import autoavatarbot.util.Avatar;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import javax.security.auth.login.LoginException;

public class AutoAvatarBot {
    private static JDA jda;
    private static LoadingProperties config;
    private static String password;
    private static long timeToSwitch;

    public AutoAvatarBot() {
        config = new LoadingProperties();
        password = config.getPassword();
        timeToSwitch = config.getTimeToSwitch();
        try {
            jda = new JDABuilder(AccountType.CLIENT).setToken(config.getToken()).buildBlocking();
            new Avatar(timeToSwitch);
            System.out.println("Launched!");
        } catch (LoginException | InterruptedException e) {
            System.out.println("invalid token");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AutoAvatarBot();
    }

    public static JDA getJda() {
        return jda;
    }

    public static LoadingProperties getConfig() {
        return config;
    }

    public static String getPassword() {
        return password;
    }
}
