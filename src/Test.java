import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
