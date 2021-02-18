package project.robots;

import java.util.List;
import java.util.Random;

public class Couple {
    int a;
    int b;

    public Couple(int i, int j) {
        a = i;
        b = j;
    }

    /**
     * Randomly select an element based on index
     *
     * @param list The list to choose from
     * @return A randomly selected element of the list
     */
    public static Couple getRandomElement(List<Couple> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
