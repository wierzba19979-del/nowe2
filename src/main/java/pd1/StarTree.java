package pd1;

public class StarTree {
    public static void main(String[] args) {
        for (int i = 1, j = 4; i <= 9 && j > 0; i = i + 2, j--) {
            System.out.print(" ".repeat(j));
            System.out.println("*".repeat(i));
        }
    }
}
