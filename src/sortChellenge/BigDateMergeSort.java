package sortChellenge;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by yanagawa_keita on 2016/03/02.
 */
public class BigDateMergeSort {
    private static final String INPUT_FILE = "C:\\work\\challenge\\sort_median.txt";
    private static final String OUTPUT_FILE = "C:\\work\\challenge\\sorted_file_test.txt";
    private static final int DATE_KENSU = 100000000;
    private static final int THRESHOLD_VALUE = 100000000;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int thresholdValue = THRESHOLD_VALUE;
        int countSize = 0;

        while (countSize < DATE_KENSU) {
            ArrayList List = makeTarget(thresholdValue);

            long sortStart = System.currentTimeMillis();
            int[] intList = new int[List.size()];
            for (int i = 0; i < List.size(); i++) {
                intList[i] = (int) List.get(i);
            }
            int[] partOfSortResult = mergeSort(intList);
            long sortEnd = System.currentTimeMillis();
            System.out.println("並び替え処理時間は" + (sortEnd - sortStart) + "ms です");

            fileWriter(partOfSortResult);
            thresholdValue = thresholdValue + THRESHOLD_VALUE;

            countSize = countSize + List.size();

        }

        long end = System.currentTimeMillis();
        System.out.println("処理が終了しました。\r\n全体の処理時間は" + (end - start) + "ms です");

    }

    public static ArrayList<Integer> makeTarget(int basis) {
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Integer> List = new ArrayList<>();

        try {
            String counter;

            long start = System.currentTimeMillis();

            fr = new FileReader(INPUT_FILE);
            br = new BufferedReader(fr);

            while ((counter = br.readLine()) != null) {
                int target = Integer.parseInt(counter);
                if ((basis - THRESHOLD_VALUE) < target && target < basis) {
                    List.add(target);
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
        return List;
    }


    public static void fileWriter(int[] List) {
        File file = new File(OUTPUT_FILE);

        long start = System.currentTimeMillis();

        try {
            if (checkBeforeWritefile(file)) {
                PrintWriter pw = null;
                pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE, true)));

                for (int ListA : List) {
                    pw.println(ListA);
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

    private static boolean checkBeforeWritefile(File file) {
        if (file.exists()) {
            if (file.isFile() && file.canWrite()) {
                return true;
            }
        }
        return false;
    }

    public static int[] mergeSort(int[] input) {

        int[] targetArray = input;

        int[] result = new int[targetArray.length];

        Integer middle = (0 + targetArray.length - 1) / 2;
        int[] firstHalfArray = new int[middle + 1];
        int[] laterHalfArray;
        if (0 < targetArray.length) {
            laterHalfArray = new int[targetArray.length - middle - 1];
        } else {
            laterHalfArray = new int[0];
        }

        if (targetArray.length < 2) {
            return targetArray;
        }
        System.arraycopy(targetArray, 0, firstHalfArray, 0, middle + 1);

        System.arraycopy(targetArray, middle + 1, laterHalfArray, 0, targetArray.length - middle - 1);

        firstHalfArray = mergeSort(firstHalfArray);

        laterHalfArray = mergeSort(laterHalfArray);

        int resultIndex = 0;
        int firstHalfIndex = 0;
        int laterHalfIndex = 0;

        for (int i = 0; i < targetArray.length - 1; i++) {
            if (firstHalfIndex < firstHalfArray.length && laterHalfIndex < laterHalfArray.length) {
                if (firstHalfArray[firstHalfIndex] < laterHalfArray[laterHalfIndex]) {
                    result[resultIndex] = firstHalfArray[firstHalfIndex];
                    firstHalfIndex++;
                } else {
                    result[resultIndex] = laterHalfArray[laterHalfIndex];
                    laterHalfIndex++;
                }
                resultIndex++;
            }
        }
        if (firstHalfIndex > laterHalfIndex) {
            for (int j = laterHalfIndex; j < laterHalfArray.length; j++, resultIndex++) {
                result[resultIndex] = laterHalfArray[j];
            }
        } else {
            for (int j = firstHalfIndex; j < firstHalfArray.length; j++, resultIndex++) {
                result[resultIndex] = firstHalfArray[j];
            }
        }
        return result;
    }

}
