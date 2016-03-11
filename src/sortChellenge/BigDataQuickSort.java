package sortChellenge;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by yanagawa_keita on 2016/03/02.
 * <p>
 * 大量データのソート処理（クイックソート版）
 */
public class BigDataQuickSort {
    private static final int SORT_DATE_COUNT = 100000000;
    private static final int THRESHOLD_VALUE = 100000000;

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        int thresholdValue = THRESHOLD_VALUE;
        int processCount = 0;

        String inputFilePath = args[0];
        String outputFileDirPath = args[1];

        while (processCount < SORT_DATE_COUNT) {
            ArrayList<Integer> integers = makeSortTarget(thresholdValue, inputFilePath);
            ArrayList sortTarget = integers;

            long sortStart = System.currentTimeMillis();
            int[] result = new int[sortTarget.size()];
            for (int i = 0; i < sortTarget.size(); i++) {
                result[i] = (int) sortTarget.get(i);
            }
            quickSort(result, 0, result.length - 1);
            long sortEnd = System.currentTimeMillis();
            System.out.println("並び替え処理時間は" + (sortEnd - sortStart) + "ms です");

            fileWriter(result, outputFileDirPath);
            thresholdValue = thresholdValue + THRESHOLD_VALUE;
            processCount = processCount + sortTarget.size();

        }

        long end = System.currentTimeMillis();
        System.out.println("処理が終了しました。\r\n全体の処理時間は" + (end - start) + "ms です");

    }

    /**
     * 外部ファイルからから、ソート対象を選び、リストに格納して返す。
     * 外部ファイルのデータ量が大きく、リストに格納できないため、閾値でを使用し選別。
     *
     * @param thresholdValue 　閾値
     * @return result ソート対象
     */
    public static ArrayList<Integer> makeSortTarget(int thresholdValue, String inputFilePath) {
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Integer> result = new ArrayList<>();

        try {
            String counter;
            long start = System.currentTimeMillis();
            fr = new FileReader(inputFilePath);
            br = new BufferedReader(fr);

            while ((counter = br.readLine()) != null) {
                int target = Integer.parseInt(counter);
                if ((thresholdValue - THRESHOLD_VALUE) < target && target < thresholdValue) {
                    result.add(target);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("読み込み処理時間は" + (end - start) + "ms です");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ソートしたデータを外部ファイルに書き込む。
     *
     * @param writingDate ソート済みの書き込みデータ
     */
    public static void fileWriter(int[] writingDate, String outputFilePath) {

        File outputFile = new File(outputFilePath);
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(outputFilePath);

        long start = System.currentTimeMillis();

        try {
            if (checkBeforeWriteFile(file)) {
                PrintWriter pw;
                pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath, true)));

                for (int oneOfWritingDate : writingDate) {
                    pw.println(oneOfWritingDate);
                }
                pw.close();

                long end = System.currentTimeMillis();
                System.out.println("書き込み処理時間は" + (end - start) + "ms です");
            } else {
                System.out.println("ファイルに書き込めません");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * クイックソートを行う。
     *
     * @param target 　ソート対象
     * @param i      　前からの添え字
     * @param j      　後ろからの添え字
     */
    public static void quickSort(int[] target, int i, int j) {

        if (i == j) return;
        int pivotSubscript = pivot(target, i, j);
        if (pivotSubscript != -1) {
            int divisionSubscript = partition(target, i, j, target[pivotSubscript]);
            quickSort(target, i, divisionSubscript - 1);
            quickSort(target, divisionSubscript, j);
        }
    }

    /**
     * 軸となる値を算出。
     *
     * @param target 　ソート対象
     * @param i      前からの添え字
     * @param j      　後ろからの添え字
     * @return pivotSubscript 要素軸の添え字
     */
    private static int pivot(int[] target, int i, int j) {
        int pivotSubscript = i + 1;
        while (pivotSubscript <= j && target[i] == target[pivotSubscript]) pivotSubscript++;
        if (pivotSubscript > j) return -1;
        if (target[i] >= target[pivotSubscript]) return i;
        return pivotSubscript;
    }

    /**
     * 　要素軸を基準に、並び替えと分割を行う。
     *
     * @param target 　ソート対象
     * @param i      　前からの添え字
     * @param j      　後ろからの添え字
     * @param pivot  　要素軸
     * @return searchedIndexI 区切り値
     */
    private static int partition(int[] target, int i, int j, int pivot) {
        int searchedIndexI = i, searchedIndexJ = j;

        while (searchedIndexI <= searchedIndexJ) {

            while (searchedIndexI <= j && target[searchedIndexI] < pivot) {
                searchedIndexI++;
            }

            while (searchedIndexJ >= i && target[searchedIndexJ] >= pivot) {
                searchedIndexJ--;
            }

            if (searchedIndexI > searchedIndexJ) {
                break;
            }
            int tmp = target[searchedIndexI];
            target[searchedIndexI] = target[searchedIndexJ];
            target[searchedIndexJ] = tmp;
            searchedIndexI++;
            searchedIndexJ--;
        }
        return searchedIndexI;
    }

    /**
     * ファイル書き込み可否チェック
     *
     * @param file
     * @return チェック結果
     */
    private static boolean checkBeforeWriteFile(File file) {
        if (file.exists()) {
            if (file.isFile() && file.canWrite()) {
                return true;
            }
        }
        return false;
    }

}

