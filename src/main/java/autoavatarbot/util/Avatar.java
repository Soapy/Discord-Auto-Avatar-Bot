package autoavatarbot.util;

public class Avatar {
    public Avatar(long period) {
        run(period);
    }

    public void run(long period) {
        AvatarThread thread = new AvatarThread(period);
        thread.setDaemon(true);
        thread.run();
    }
}
