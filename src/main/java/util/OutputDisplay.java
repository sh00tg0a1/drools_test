package util;

/**
 * Created by cx on 2018/11/21.
 */
public class OutputDisplay {
    public void showText(String text) {
        long time = System.currentTimeMillis();
        System.out.println("[!!Event fired!!] Time: " + time + " - " + text);
    }
}
