package sortChellenge;

/**
 * Created by yanagawa_keita on 2016/03/03.
 */
public class Merge {

    public static void main(String[] args) {

        int[] List = new int[6];
        List[0] = 12;
        List[1] = 4;
        List[2] = 3;
        List[3] = 2;
        List[4] = 1;
        List[5] = 13;
        int[] out= mergeSort(List);

        for (int ListA: out) {
            System.out.println(ListA);
        }

    }

    public static int[] mergeSort(int[] input) {
        // 分割の配列
        int[] array = input;
        // 並び替えた数値を格納する配列。最終的にこの配列を返す。
        int[] b = new int[array.length];

        // 分割する添え字
        int middle = (0 + array.length - 1) / 2;
        // 分割した配列の前半分を格納する配列
        int[] array1 = new int[middle + 1];

        // 分割した配列の後ろ半分を格納する配列
        int[] array2;
        // arrayの要素数が1以上のとき
        if (0 < array.length) {
            array2 = new int[array.length - middle - 1];
            // arrayの要素数が0以下のとき
        } else {
            array2 = new int[0];
        }

        // 要素数が1以下のときは、何もせず返却する
        if (array.length < 2) {
            return array;
        }
        // 前半部分をarray1にコピー
        System.arraycopy(array, 0, array1, 0, middle + 1);

        // 後半部分をarray1にコピー
        System.arraycopy(array, middle + 1, array2, 0, array.length - middle - 1);

        // 前半部分を並び替え（再帰的に）
        array1 = mergeSort(array1);

        // 後半部分を並び替え（再帰的に）
        array2 = mergeSort(array2);

        int bindex = 0;
        int array1index = 0;
        int array2index = 0;

        for (int i = 0; i < array.length - 1; i++) {
            if (array1index < array1.length && array2index < array2.length) {
                if (array1[array1index] < array2[array2index]) {
                    b[bindex] = array1[array1index];
                    array1index++;
                } else {
                    b[bindex] = array2[array2index];
                    array2index++;
                }
                bindex++;
            }
        }
        if (array1index > array2index) {
            for (int j = array2index; j < array2.length; j++, bindex++) {
                b[bindex] = array2[j];
            }
        } else {
            for (int j = array1index; j < array1.length; j++, bindex++) {
                b[bindex] = array1[j];
            }
        }
        return b;
    }
}
