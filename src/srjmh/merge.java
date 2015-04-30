package srjmh;

public class merge {
    public int[] numbers;
    private int[] helper;
    private int number;

    public int[] sortMerge(int[] values) {
        number = values.length;
        numbers = new int[number];
        for (int i = 0; i < values.length; i++) {
            numbers[i] = values[i];
        }
        helper = new int[number];
        mergesort(0, number - 1);
        return numbers;
    }
    
    private void mergesort(int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergesort(low, middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }
    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
   //             inver=inver.subtract(one);
            } else {
                numbers[k] = helper[j];
                j++;
                //inver=inver.add(one);
            }
            k++;
        }
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
    }  
}
