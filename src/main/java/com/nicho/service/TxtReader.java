package com.nicho.service;

import java.io.*;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2020/12/30 16:50
 */
public class TxtReader{
    public static  void  TxtRead() throws Exception{
        long start=System.currentTimeMillis();
        String filepath="E:/XX/Data/test_1g.txt";
        File file = new File(filepath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedInputStream fis = new BufferedInputStream(fileInputStream);
        BufferedReader reader = null;// 用5M的缓冲读取文本文件
        try {
            reader = new BufferedReader(new InputStreamReader(fis,"utf-8"),5*1024*1024);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int countTran=0;
        int successState=0;
        int errorState=0;
        int typeCount=0;
        String type="0110";

        String line = "";
    System.out.println("======开始=====");
    	while((line = reader.readLine()) != null){
            //TODO: write your business
            if(!(null!=line&&"".equals(line))){
                String[] dataList = line.split("\\|");
                if(dataList.length>0){
                    if(2 == dataList.length){
                        countTran=Integer.valueOf(dataList[0]);
                    }else if (dataList.length>2){
                        String tranType = dataList[3];
                        if(type.equals(tranType)){
                            typeCount++;
                        }
                        String tranStatus = dataList[4];
                        if("00".equals(tranStatus)||"03".equals(tranStatus)){
                            successState++;
                        }else{
                            errorState++;
                        }
                    }
                }
            }
        }
    	System.out.println("======================");
		System.out.println(+countTran);
		System.out.println(successState);
		System.out.println(errorState);
		System.out.println(typeCount);
		System.out.println("======================");
    	System.out.println("======结束=====");
        long end=System.currentTimeMillis();

		System.out.println(end-start);
    }
}
 


