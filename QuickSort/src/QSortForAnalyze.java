public class QSortForAnalyze {
    int iterations;
    public void qSort(int l, int r, int[] arr) {
        int pivotInd = (l + r) / 2;
        int pivot = arr[pivotInd];
        int i = l;
        int j = r;
        while (i <= j) {
            iterations++;
            while (arr[i] < pivot) {
                i++;
                iterations++;
            }
            while (arr[j] > pivot){
                j--;
                iterations++;
            }
            if (i <= j) {
                swap(i, j, arr);
                i++;
                j--;
            }
        }
        if (l < j) {
            qSort(l, j, arr);
        }
        if (r > i) {
            qSort(i, r, arr);
        }
    }

    public static void swap(int i, int j, int[] arr) {
        int x = arr[j];
        arr[j] = arr[i];
        arr[i] = x;
    }
}
