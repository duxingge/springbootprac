package com.example.javafunction.service.wandou.execel;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author wangjiaxing
 * @Date 2023/1/28
 */

public class Scan {
    static Map<String,Integer> maps = new HashMap<>();
    static Set<String> ketSet = null;
    static ArrayList<String> headerSortList;
    static final String baseSourceName= "csvs";
    static final String baseOutName= "csvsResult";
    static final String outFileName = "icp3";
//    static Set<String> preLineSets = Sets.newHashSet("LV 24-Segment FS Values[%]","RV 24-Segment FS Values[%]","LV 24-Segment SI Values[%]","RV 24-Segment SI Values[%]","LV 24-Segment ED Values[mm]","RV 24-Segment ED Values[mm]");
static Set<String> preLineSets = Sets.newHashSet("LV 24-Segment FS Values[%]","RV 24-Segment FS Values[%]","LV 24-Segment SI Values[%]","RV 24-Segment SI Values[%]","LV 24-Segment ED Values[mm]","RV 24-Segment ED Values[mm]","LV 24-Segment FS Z-Scores[]","RV 24-Segment FS Z-Scores[]","LV 24-Segment SI Z-Scores[]","RV 24-Segment SI Z-Scores[]","LV 24-Segment ED Z-Scores (AUA)[]","RV 24-Segment ED Z-Scores (AUA)[]");
    static List<String> coreList = Lists.newArrayList("Segment 1","Segment 2","Segment 3","Segment 4","Segment 5","Segment 6","Segment 7","Segment 8","Segment 9","Segment 10","Segment 11","Segment 12","Segment 13","Segment 14","Segment 15","Segment 16","Segment 17","Segment 18","Segment 19","Segment 20","Segment 21","Segment 22","Segment 23","Segment 24");
    static int nameIndex = 0;
    private static final String outFileDic = "/Users/wangjiaxing/study/test-code/wandou/";

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
        //

        maps.put("Mid Cereb Artery.PI_Lt[]",3);
        maps.put("Mid Cereb Artery.PI_Rt[]",3);
        maps.put("Umbilical Art..PI[]",3);
        maps.put("LV_ Global Strain.Lt GLS ZS[]",3);
        maps.put("RV_ Global Strain.Rt GLS ZS[]",3);
        maps.put("LV_ Frac. Area change.Lt FRAC ZS(AUA)[]",3);
        maps.put("RV_ Frac. Area change.Rt FRAC ZS(AUA)[]",3);
        maps.put("LV_ EF.Lt EF ZS(AUA)[]",3);
        maps.put("LV_ SV.Lt SV ZS(AUA)[]",3);
        maps.put("LV_ SV/KG.Lt SV/KG ZS(AUA)[]",3);
        maps.put("LV_ CO.Lt CO ZS(AUA)[]",3);
        maps.put("LV_ CO/KG.Lt CO/KG ZS(AUA)[]",3);
        maps.put("4CV GSI.GSI ZS[]",3);
        maps.put("4CV Trv. Width ED[mm",3);
        maps.put("4CV Trv. Width ED.4CV TW ED ZS(AUA)[]]",3);
        maps.put("4CV Length ED[mm]",3);
        maps.put("4CV Length ED.4CV L ED ZS(AUA)[]",3);
        maps.put("4CV Area (2Dist)[mm淫",3);
        maps.put("LV_ EDV.Lt EDV ZS(AUA)[]",3);
        maps.put("LV_ FreeWall Strain.Lt FWS ZS[]",3);
        maps.put("LV_ Sept.Wall Strain.Lt SWS ZS[]",3);
        maps.put("RV_ Sept.Wall Strain.Rt SWS ZS[]",3);
        maps.put("RV_ FreeWall Strain.Rt FWS ZS[]",3);
        maps.put("LV_ MAPSE septal.MAPSE sept. ZS(AUA)[]",3);
        maps.put("LV_ MAPSE lateral.MAPSE lat. ZS(AUA)[]",3);
        maps.put("RV_ TAPSE septal.TAPSE sept. ZS(AUA)[]",3);
        maps.put("LV_ Basal-Lat.Wall FS.Lt Basal-Lat FS ZS(AUA)[]",3);
        maps.put("RV_ Basal-Lat.Wall FS.Rt Basal-Lat FS ZS(AUA)[]",3);
        maps.put("LV_ Basal-Sep.Wall FS.Lt Basal-Sep FS ZS(AUA)[]",3);
        maps.put("RV_ Basal-Sep.Wall FS.Rt Basal-Sep FS ZS(AUA)[]",3);
        maps.put("LV_ Basal-Apical FS.Lt BASE-AP FS ZS[]",3);
        maps.put("RV_ Basal-Apical FS.Rt BASE-AP FS ZS[]",3);



        ketSet = maps.keySet();
        headerSortList = Lists.newArrayList(ketSet);
        headerSortList.sort(String::compareTo);
        List<String> sortPreLineSets = Lists.newArrayList(preLineSets);
        sortPreLineSets.sort(String::compareTo);
        for (String preLine : sortPreLineSets) {
            for (String core : coreList) {
                maps.put(String.format("%s %s",preLine,core),3);
                headerSortList.add(String.format("%s %s",preLine,core));
            }
        }
        headerSortList.remove("Name");
        headerSortList.add(0,"Name");
        nameIndex = 0;
    }

    public static void main(String[] args) throws IOException {
        renameFile();
        scan();

    }

    private static void scan() throws FileNotFoundException {
        scan(new File(outFileDic+baseSourceName+"/"));
    }


    private static void scan(File scanDic) throws FileNotFoundException {
        if(!scanDic.isDirectory()) {
            return;
        }
        List<File> subDics = new ArrayList();
        List<File> subFiles = new ArrayList();
        for (File file : scanDic.listFiles()) {
            if (file.isDirectory()) {
                subDics.add(file);
            } else {
                subFiles.add(file);
            }
        }
        if(subFiles.size()>0) {
            List<Object> exportData = new ArrayList<Object>();
            headerSortList.stream().forEach(it->{
                exportData.add(it);
            });
            List<List<Object>> outputDatalist = new ArrayList<List<Object>>();

            List<Map<String, String>> allResult = subFiles.stream().map(subFile -> CompletableFuture.supplyAsync(() -> {
                Map<String, String> fileValues = new HashMap<>();
                String preLineText = "";
                String line = "";
                String SplitBy = ";";
                String[] lineArr;
                try (BufferedReader br = new BufferedReader(new FileReader(subFile))) {
                    while ((line = br.readLine()) != null) {
                        lineArr = line.split(SplitBy);
                        String key = findKey(line, ketSet, preLineText);
                        if (StringUtils.isNotBlank(key)) {
                            int index = maps.get(key) == null ? 2 : maps.get(key) - 1;
                            if (lineArr.length > index) {
//                            data.add(describe(key,lineArr.length>index?lineArr[maps.get(key)-1]:""));
                                fileValues.put(key, describe(key, lineArr.length > index ? lineArr[index] : ""));
                            } else {
                                System.out.println("error column key " + key + " line " + line + " file " + subFile.getName());
                            }
                        }
                        preLineText = updatePreLine(line, preLineText);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fileValues;
            })).map(c -> c.join()).collect(Collectors.toList());

            addToOut(outputDatalist,allResult);
            sortList(outputDatalist);
//        out.close();
            String fileName = scanDic.getName();

            File file1 = createCSVFile(exportData, outputDatalist, scanDic.getPath().replace(baseSourceName,baseOutName), fileName);
            String fileName2 = file1.getPath();
            System.out.println("输出文件名称：" + fileName2);
        }


        for (File subDic : subDics) {
            scan(subDic);
        }

    }

    private static void renameFile() throws FileNotFoundException {
        File pfile = ResourceUtils.getFile("classpath:"+baseSourceName);
        File file = new File(outFileDic);
        FileSystemUtils.deleteRecursively(file);
        file.mkdirs();
        renameFile(pfile, outFileDic);
    }

    private static void renameFile(File pfile,String filePath) {
        if(!pfile.isDirectory()) {
            return;
        }
        filePath = filePath + pfile.getName()+"/";
        File dic = new File(filePath);
        if(!dic.exists()) {
            dic.mkdirs();
        }
        List<File> subDics = new ArrayList();
        List<File> subFiles = new ArrayList();
        for (File file : pfile.listFiles()) {
            if (file.isDirectory()) {
                subDics.add(file);
            } else {
                subFiles.add(file);
            }
        }
        if(subFiles.size()>0) {
            for (int i = 0; i < subFiles.size(); i++) {
                File file1 = subFiles.get(i);
                File mm=new File(filePath+pfile.getName()+"_sub"+i+".txt");
                file1.renameTo(mm);
            }
        }

        for (File subDic : subDics) {
            renameFile(subDic,filePath);
        }
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

    private static void addToOut(List<List<Object>> outputDatalist, List<Map<String, String>> allFileValues) {
        allFileValues.parallelStream().forEach(fileValues-> {
            List<Object> rows = new ArrayList<>();
            headerSortList.stream().forEach(it->{
                String val = fileValues.get(it);
                rows.add(val==null?"":val);
            });
            outputDatalist.add(rows);
        });
    }

    private static String describe(String key, String value) {
        if("Exam Date".equals(key)) {
            return value.split(" ")[0];
        }
        return value;
    }

    private static String findKey(String line, Set<String> keys, String preLine) {
        if(StringUtils.isBlank(line)) {
            return null;
        }
        for (String key : keys) {
            if (line.contains(key)) {
                return key;
            }
            if (line.contains("Segment ") && !preLineHitSet(line) && preLineHitSet(preLine)) {
                if (key.equals(String.format("%s %s", preLine.replace(";", ""), line.split(";")[1]))) {
                    return key;
                }
            }
        }
        if(!line.contains("24-Segment")) {
            System.out.println(String.format("current line:  %s  parentLine: %s not found!!", line, preLine));
        }
        return null;
    }

    private static boolean preLineHitSet(String currentLine) {
        return currentLine.contains("24-Segment");
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





