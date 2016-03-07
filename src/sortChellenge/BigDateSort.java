package sortChellenge;

import java.io.*;

/**
 * Created by yanagawa_keita on 2016/03/02.
 */
public class BigDateSort {
    private static final String INPUT_FILE = "C:\\work\\challenge\\sort_median2.txt";
    private static final String OUTPUT_FILE = "C:\\work\\challenge\\sorted_file_test.txt";
    private static final int START_PREMIN = 0;
    private static final int DATE_KENSU = 1000000000;
    private static final int MAX = 2000000000;
    private static final int MAX_PLUS_ONE = 2000000001;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int preMin = START_PREMIN;
        int i = 0;
        int zanKensu;

        while (preMin < MAX) {
            long oneTimePerStart = System.currentTimeMillis();
            String min = serchMin(preMin);
            if (Integer.parseInt(min) != MAX_PLUS_ONE) {
                fileWriter(min);
            }
            preMin = Integer.parseInt(min);
            long oneTimePerEnd = System.currentTimeMillis();
            i = i + 1;
            zanKensu = DATE_KENSU - i;

/*            if (zanKensu == (DATE_KENSU - 1) || i % 1000 == 0) {
                System.out.println("残りの件数は" + zanKensu + "件です。\r\n処理終了まで、推定" + (oneTimePerEnd - oneTimePerStart) * zanKensu + "ms です");
            }*/

        }

        long end = System.currentTimeMillis();
        System.out.println("処理が終了しました。\r\n全体の処理時間は" + (end - start)  + "ms です");

    }

    public static String serchMin(int preMin) {
        FileReader fr = null;
        BufferedReader br = null;
        int currentMin = 0;

        long start = System.currentTimeMillis();

        try {
            fr = new FileReader(INPUT_FILE);
            br = new BufferedReader(fr);
            String counter;
            currentMin = MAX_PLUS_ONE;

            while ((counter = br.readLine()) != null) {
                int target = Integer.parseInt(counter);
                if (target > preMin && target < currentMin) {
                    currentMin = target;
                }
            }

            long end = System.currentTimeMillis();
            System.out.println("読み込み処理時間は" + (end - start)  + "ms です");

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
        return String.valueOf(currentMin);
    }

    public static void fileWriter(String min) {
        File file = new File(OUTPUT_FILE);

        try {
            if (checkBeforeWritefile(file)) {

                long start = System.currentTimeMillis();

                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE, true)));
                pw.println(min);
                pw.close();

                long end = System.currentTimeMillis();
                System.out.println("書き込み処理時間は" + (end - start)  + "ms です");

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
}
