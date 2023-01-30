package com.example.javafunction.service.wandou.execel;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

/**
 * @Author wangjiaxing
 * @Date 2023/1/28
 */

public class Scan {
    static Map<String,Integer> maps = new HashMap<>();
    static Set<String> ketSet = null;
    static ArrayList<String> sortList;
//    static final String scanPath= "classpath:csvserror";
    static final String scanPath= "classpath:csvs";
    static final String outFileName = "normal";
    static Set<String> preLineSets = Sets.newHashSet("LV 24-Segment FS Values[%]","RV 24-Segment FS Values[%]","LV 24-Segment SI Values[%]","RV 24-Segment SI Values[%]","LV 24-Segment ED Values[mm]","RV 24-Segment ED Values[mm]");
    static List<String> coreList = Lists.newArrayList("Segment 1;","Segment 2;","Segment 3;","Segment 4;","Segment 5;","Segment 6;","Segment 7;","Segment 8;","Segment 9;","Segment 10;","Segment 11;","Segment 12;","Segment 13;","Segment 14;","Segment 15;","Segment 16;","Segment 17;","Segment 18;","Segment 19;","Segment 20;","Segment 21;","Segment 22;","Segment 23;","Segment 24;");
    static int nameIndex = 0;
    static {
        maps.put("Name",3);
        maps.put("Patient",3);
        maps.put("Exam Date",3);
        maps.put("GA (clin)[w+d]",3);
        maps.put("OB_AC_HADLOCK;AC (Hadlock)[cm]",3);
        maps.put("OB_BPD_HADLOCK;BPD (Hadlock)[cm]",3);
        maps.put("OB_EFW;EFW (Hadlock)[]",3);
        maps.put("OB_FL_HADLOCK;FL (Hadlock)[cm]",3);
        maps.put("OB_HC_HADLOCK;HC (Hadlock)[cm]",3);
        maps.put("OB_ZSCORE_VENTR_GLS.GLS_L;LV_ Global Strain[%]",3);
        maps.put("OB_ZSCORE_VENTR_GLS.GLS_R;RV_ Global Strain[%]",3);
        maps.put("OB_ZSCORE_VENTR_FRAC.FRAC_L;LV_ Frac. Area change[%]",3);
        maps.put("OB_ZSCORE_VENTR_FRAC.FRAC_R;RV_ Frac. Area change[%]",3);
        maps.put("OB_ZSCORE_VENTR_EF.EF_L;LV_ EF[%]",3);
        maps.put("OB_ZSCORE_VENTR_SV.SV_L;LV_ SV[ml]",3);
        maps.put("OB_ZSCORE_VENTR_SV_KG.SV_KG_L;LV_ SV/KG[ml/kg]",3);
        maps.put("OB_ZSCORE_VENTR_CO.CO_L;LV_ CO[ml/min]",3);
        maps.put("OB_ZSCORE_VENTR_CO_KG.CO_KG_L;LV_ CO/KG[ml/min/kg]",3);
        maps.put("LV_ ED Area",3);
        maps.put("LV_ ED Length",3);
        maps.put("LV_ ES Area",3);
        maps.put("LV_ ES Length",3);
        maps.put("LV_ EDV[ml]",3);
        maps.put("LV_ ESV[ml]",3);
        maps.put("RV_ ED Area",3);
        maps.put("RV_ ED Length",3);
        maps.put("RV_ ES Area",3);
        maps.put("RV_ ES Length",3);
        maps.put("OB_ZSCORE_GSI.GSI; 4CV GSI[]",3);
        maps.put("RV_ FreeWall Strain[%]",3);
        maps.put("LV_ MAPSE septal[mm]",3);
        maps.put("LV_ MAPSE lateral[mm]",3);
        maps.put("RV_ TAPSE lateral[mm]",3);
        maps.put("LV_ FreeWall Strain[%]",3);
        maps.put("LV_ Sept.Wall Strain[%]",3);
        maps.put("RV_ Sept.Wall Strain[%]",3);
        maps.put("LV_ Basal-Lat.Wall FS[%]",3);
        maps.put("RV_ Basal-Lat.Wall FS[%]",3);
        maps.put("LV_ Basal-Sep.Wall FS[%]",3);
        maps.put("RV_ Basal-Sep.Wall FS[%]",3);
        maps.put("LV_ Basal-Apical FS[%]",3);
        maps.put("RV_ Basal-Apical FS[%]",3);
        maps.put("LV_ Global Strain.Lt GLS %[%]",3);
        maps.put("RV_ Global Strain.Rt GLS %[%]",3);
        maps.put("RV_ FreeWall Strain.Rt FWS %[%]",3);
        maps.put("LV_ FreeWall Strain.Lt FWS %[%]",3);
        maps.put("LV_ Sept.Wall Strain.Lt SWS %[%]",3);
        maps.put("RV_ Sept.Wall Strain.Rt SWS %[%]",3);



        ketSet = maps.keySet();
        sortList = Lists.newArrayList(ketSet);
        sortList.sort(String::compareTo);
        List<String> sortPreLineSets = Lists.newArrayList(preLineSets);
        sortPreLineSets.sort(String::compareTo);
        for (String preLine : sortPreLineSets) {
            for (String core : coreList) {
                maps.put(String.format("%s %s",preLine,core),3);
                sortList.add(String.format("%s %s",preLine,core));
            }
        }

        sortList.remove("Name");
        sortList.add(0,"Name");
        nameIndex = 0;
    }

    public static void main(String[] args) throws IOException {
//        Set<String> keys = Sets.newHashSet("Patient","Name","Exam Date","GA (clin)[w+d]","OB_AC_HADLOCK;AC (Hadlock)[cm]",
//                "OB_BPD_HADLOCK;BPD (Hadlock)[cm]","OB_EFW;EFW (Hadlock)[]","OB_FL_HADLOCK;FL (Hadlock)[cm]","OB_HC_HADLOCK;HC (Hadlock)[cm]","OB_ZSCORE_VENTR_GLS.GLS_L;LV_ Global Strain[%]",
//                "OB_ZSCORE_VENTR_GLS.GLS_R;RV_ Global Strain[%]","OB_ZSCORE_VENTR_FRAC.FRAC_L;LV_ Frac. Area change[%]","OB_ZSCORE_VENTR_FRAC.FRAC_R;RV_ Frac. Area change[%]","OB_ZSCORE_VENTR_EF.EF_L;LV_ EF[%]","OB_ZSCORE_VENTR_SV.SV_L;LV_ SV[ml]",
//                "OB_ZSCORE_VENTR_SV_KG.SV_KG_L;LV_ SV/KG[ml/kg]","OB_ZSCORE_VENTR_CO.CO_L;LV_ CO[ml/min]","OB_ZSCORE_VENTR_CO_KG.CO_KG_L;LV_ CO/KG[ml/min/kg]");
//        File outfile = new File("JayChou.txt");
//        PrintWriter out = new PrintWriter(outfile, "UTF-8");
        List<Object> exportData = new ArrayList<Object>();
        sortList.stream().forEach(it->{
          exportData.add(it);
        });
        List<List<Object>> outputDatalist = new ArrayList<List<Object>>();


        File file = ResourceUtils.getFile(scanPath);
//        System.out.println(JsonUtil.toString(file.list()));
        String preLineText = "";
        for (File f : file.listFiles()) {
            Map<String,String> fileValues = new HashMap<>();

            String line = "";
            String SplitBy = ";";
            String[] lineArr;
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                while ((line = br.readLine()) != null) {
                    lineArr = line.split(SplitBy);
                    String key = findKey(line, ketSet,preLineText);
                    if(StringUtils.isNotBlank(key)) {
//                        System.out.println();
//                        out.println(String.format("  %s :  %s ",key,describe(key,lineArr[maps.get(key)-1])));
                        List<Object> data=new ArrayList<Object>();
                        data.add(key);
                        int index = maps.get(key)==null?2:maps.get(key) - 1;
                        if(lineArr.length>index) {
//                            data.add(describe(key,lineArr.length>index?lineArr[maps.get(key)-1]:""));
                            fileValues.put(key,describe(key,lineArr.length>index?lineArr[index]:""));
                        }else {
                            System.out.println("error column key "+ key +" line "+ line+  " file "+ f.getName());
                        }
                    }
                    preLineText = updatePreLine(line,preLineText);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            addToOut(outputDatalist,fileValues);
            sortList(outputDatalist);
        }
//        out.close();
        String path = "/Users/wangjiaxing/IdeaProjects/springbootprac";
        String fileName = outFileName;

        File file1 = createCSVFile(exportData, outputDatalist, path, fileName);
        String fileName2 = file1.getName();
        System.out.println("文件名称：" + fileName2);
    }

    private static String updatePreLine(String currentLine,String preLine) {
        if(StringUtils.isBlank(currentLine) || (!preLineHitSet(currentLine)) && currentLine.contains("Segment ")) {
            return preLine;
        }
        return currentLine;
    }

    private static void sortList(List<List<Object>> outputDatalist) {
        outputDatalist.sort(Comparator.comparing(Scan::getName));
    }

    private static String getName(List<Object> d1) {
        return (String) d1.get(nameIndex);
    }

    private static void addToOut(List<List<Object>> outputDatalist, Map<String, String> fileValues) {
        List<Object> rows = new ArrayList<>();
        sortList.stream().forEach(it->{
            String val = fileValues.get(it);
            rows.add(val==null?"":val);
        });
        outputDatalist.add(rows);
    }

    private static String describe(String key, String value) {
        if("Exam Date".equals(key)) {
            return value.split(" ")[0];
        }
        return value;
    }

    private static String findKey(String line, Set<String> keys,String preLine) {
        for (String key : keys) {
            if(line.contains(key)) {
                return key;
            }
            if(line.contains("Segment ") && preLineHitSet(preLine)) {
                String preFix = preLineSets.stream().filter(it -> preLine.contains(it)).findAny().get();
                if(key.contains(line.split(";")[1]+";") && key.contains(preFix)) {
                        return key;
                    }
                }
            }
        return null;
    }

    private static boolean preLineHitSet(String preLine) {
        for (String preLineSet : preLineSets) {
            if(preLine.contains(preLineSet)) {
                return true;
            }
        }
        return false;
    }


    /**
     * CSV文件生成方法
     * @param head 文件头
     * @param dataList 数据列表
     * @param outPutPath 文件输出路径
     * @param filename 文件名
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GBK"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

//    public static void main(String[] args) {
//        List<Object> exportData = new ArrayList<Object>();
//        exportData.add("第一列");
//        exportData.add("第二列");
//        exportData.add("第三列");
//        List<List<Object>> datalist = new ArrayList<List<Object>>();
//        List<Object> data=new ArrayList<Object>();
//        data.add("111");
//        data.add("222");
//        data.add("333");
//        List<Object> data1=new ArrayList<Object>();
//        data1.add("444");
//        data1.add("555");
//        data1.add("\t2020-09-16 01:15:16\t");
//        datalist.add(data);
//        datalist.add(data1);
//        String path = "~/ttt/";
//        String fileName = "文件导出";
//
//        File file = createCSVFile(exportData, datalist, path, fileName);
//        String fileName2 = file.getName();
//        System.out.println("文件名称：" + fileName2);
//    }

        class SortItem {
            String filed;
            int column;

            public String getFiled() {
                return filed;
            }

            public void setFiled(String filed) {
                this.filed = filed;
            }

            public int getColumn() {
                return column;
            }

            public void setColumn(int column) {
                this.column = column;
            }
        }
}





