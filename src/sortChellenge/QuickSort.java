package sortChellenge;

/**
 * Created by yanagawa_keita on 2016/03/02.
 */
public class QuickSort {

    /*
     * 軸要素の選択
     * 順に見て、最初に見つかった異なる2つの要素のうち、
     * 大きいほうの番号を返します。
     * 全部同じ要素の場合は -1 を返します。
     */
    static int pivot(int[] List, int i, int j) {
        int k = i + 1;
        while (k <= j && List[i] == List[k]) k++;
        if (k > j) return -1;
        if (List[i] >= List[k]) return i;
        return k;
    }

    /*
     * パーティション分割
     * a[i]～a[j]の間で、x を軸として分割します。
     * x より小さい要素は前に、大きい要素はうしろに来ます。
     * 大きい要素の開始番号を返します。
     */
    static int partition(int[] List, int i, int j, int pivot) {
        int serchedIndexI = i, serchidIndexJ = j;

        // 検索が交差するまで繰り返します
        while (serchedIndexI <= serchidIndexJ) {

            // 軸要素以上のデータを探します
            while (serchedIndexI <= j && List[serchedIndexI] < pivot) {
                serchedIndexI++;
            }

            // 軸要素未満のデータを探します
            while (serchidIndexJ >= i && List[serchidIndexJ] >= pivot) {
                serchidIndexJ--;
            }

            if (serchedIndexI > serchidIndexJ) {
                break;
            }
            int tmp = List[serchedIndexI];
            List[serchedIndexI] = List[serchidIndexJ];
            List[serchidIndexJ] = tmp;
            serchedIndexI++;
            serchidIndexJ--;
        }
        return serchedIndexI;
    }

    /*
     * クイックソート（再帰用）
     * 配列aの、a[i]からa[j]を並べ替えます。
     */
    public static void quickSort(int[] List, int i, int j) {
        if (i == j) return;
        int p = pivot(List, i, j);
        if (p != -1) {
            int k = partition(List, i, j, List[p]);
            quickSort(List, i, k - 1);
            quickSort(List, k, j);
        }
    }

    /*
     * ソート
     */
    public static void sort(int[] List) {


        quickSort(List, 0, List.length - 1);
    }

}