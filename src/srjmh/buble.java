package srjmh;

public class buble {
    private int[] mas;
    
    public void swap(int i, int j) {
        int t = mas[i];
        mas[i] = mas[j];
        mas[j] = t;
    }

    public int[] sortBuble(int[] values) {
        mas = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            mas[i] = values[i];
        }
        for (int i = mas.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (mas[j] > mas[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
        return mas;
    }    
}
