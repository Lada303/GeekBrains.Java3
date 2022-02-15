package Lada303.lesson6;

public class Hw6 {

    public void run() {

    }

    public int[] arrayAfterLast4(int[] arr) {
        int index = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4) {
                index = i + 1;
                break;
            }
            if (i == 0) {
                throw new RuntimeException();
            }
        }
        int[] resultArr = new int[arr.length - index];
        System.arraycopy(arr, index, resultArr, 0, arr.length - index);
        return resultArr;
    }

    public boolean isArrContainsOnly1And4(int[] arr) {
        int x1 = 0;
        int x4 = 0;
        for (int j : arr) {
            if (j != 1 && j != 4) {
                return false;
            }
            if (j == 1) {
                x1++;
            }
            if (j == 4) {
                x4++;
            }
        }
        return x1 != 0 && x4 != 0;
    }

}
