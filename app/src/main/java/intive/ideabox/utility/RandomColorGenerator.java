package intive.ideabox.utility;

import java.util.Random;

public class RandomColorGenerator {

    private static RandomColorGenerator instance = null;

    private int colorArray[] = {
            0xffb71c1c,
            0xff880e4f,
            0xff4a148c,
            0xff311b92,
            0xff1a237e,
            0xff0d47a1,
            0xff01579b,
            0xff006064,
            0xff004d40,
            0xff1b5e20,
            0xff33691e,
            0xff827717,
            0xffbf360c,
            0xff3e2723,
            0xff263238
    };

    public synchronized static RandomColorGenerator getInstance() {
        if (instance == null) {
            instance = new RandomColorGenerator();
        }
        return instance;
    }

    public int getRandomColor() {
        return colorArray[new Random().nextInt(colorArray.length)];
    }
}
