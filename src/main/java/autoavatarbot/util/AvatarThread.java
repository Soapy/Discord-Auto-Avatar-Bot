package autoavatarbot.util;

import net.dv8tion.jda.core.entities.Icon;
import autoavatarbot.AutoAvatarBot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class AvatarThread extends Thread{
    private File folder;
    private ArrayList<String> files;
    private List<String> imageExtensions;
    private int counter;
    private int index;

    public AvatarThread(long period) {
        folder = new File("assets/images/");
        files = new ArrayList<>();
        imageExtensions = Arrays.asList("jpg", "jpeg", "png");
        counter = Objects.requireNonNull(folder.listFiles()).length;
        index = 0;
        executeThread(period);
    }

    public void executeThread(long period) {
        try {
            counter = Objects.requireNonNull(folder.listFiles()).length;
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
                        File avatar = new File(files.get(index));
                        System.out.println("Changed avatar to " + avatar.getName());
                        AutoAvatarBot.getJda().getSelfUser().getManager()
                                .setAvatar(Icon.from(avatar), AutoAvatarBot.getPassword()).queue();
                        if(index == counter) {
                            index = 0;
                        } else {
                            ++index;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(changeAvatarTask, 100, 1000 * 60 * period);
        } catch (NullPointerException e) {
            e.printStackTrace();
            this.interrupt();
        }
    }
}
