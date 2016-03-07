package sortChellenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CheckSortAsc {
    private static final String target_directory = "C:\\work\\challenge\\sorted_file_test_quick.txt";
    public static void main(String[] args){
        long start = System.currentTimeMillis();
//        System.out.println(chechSort(args[0]));
        System.out.println(chechSort(target_directory));
        long end = System.currentTimeMillis();
        System.out.println((end - start)  + "ms");
    }

    private static String chechSort(String target){
        String numberNowStr = null;
        String numberNextStr = null;
        int numberNow;
        int numberNext;
        BufferedReader brTargetFile = null;
        try {
            //maxとlinesの算出処理
            File targetFile = new File(target);
            brTargetFile = new BufferedReader(new FileReader(targetFile));

            numberNowStr = brTargetFile.readLine();
            numberNextStr = brTargetFile.readLine();
            numberNow = Integer.parseInt(numberNowStr);
            numberNext = Integer.parseInt(numberNextStr);

            if(numberNow > numberNext){
                return "昇順でソートされていません。";
            }
            numberNextStr = brTargetFile.readLine();
            while(numberNextStr != null){
                numberNow = numberNext;
                numberNext = Integer.parseInt(numberNextStr);

                if(numberNow > numberNext){
                    return "昇順でソートされていません。";
                }
                numberNextStr = brTargetFile.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                brTargetFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "昇順でソートされています。";
    }
}