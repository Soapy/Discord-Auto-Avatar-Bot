package autoavatarbot.util;

import net.dv8tion.jda.core.entities.Icon;
import autoavatarbot.AutoAvatarBot;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class AvatarThread extends Thread{
    private File folder;
    private ArrayList<String> files;
    private List<String> imageExtensions;
    private Random rand;

    public AvatarThread() {
        folder = new File("assets/images/");
        files = new ArrayList<>();
        imageExtensions = Arrays.asList("jpg", "jpeg", "png");
        rand = new Random();
        executeThread();
    }

    /**
     * Runs a thread to change the avatar at a time interval determined by the timeToSwitch value present
     * in AutoAvatarBot.java from the config file.
     */
    public void executeThread() {
        try {
            //
            for(File file : Objects.requireNonNull(folder.listFiles())) {
                for (String imageExtension : imageExtensions) {
                    if (file.getName().contains(imageExtension)) {
                        files.add(folder.getPath() + "/" + file.getName());
                    }
                }
            }
            Timer timer = new Timer();
            TimerTask changeAvatarTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        int index = rand.nextInt(files.size());
                        File avatar = new File(files.get(index));
                        System.out.println("Changed avatar to " + avatar.getName());
                        //logins into Discord via the API, and then sends a request to change the avatar
                        AutoAvatarBot.getJda().getSelfUser().getManager()
                                .setAvatar(Icon.from(avatar), AutoAvatarBot.getPassword()).queue();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(changeAvatarTask, 100, 1000 * 60 * AutoAvatarBot.getTimeToSwitch());
        } catch (NullPointerException e) {
            e.printStackTrace();
            this.interrupt();
        }
    }
}
