package autoavatarbot.util;

public class Avatar {
    public Avatar() {
        run();
    }

    /**
     * Prevents the JVM from exiting and keeps the current daemon thread running
     */
    public void run() {
        AvatarThread thread = new AvatarThread();
        thread.setDaemon(true);
        thread.run();
    }
}
