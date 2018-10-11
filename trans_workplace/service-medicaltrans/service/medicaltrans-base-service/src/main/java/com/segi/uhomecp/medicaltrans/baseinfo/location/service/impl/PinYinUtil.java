package com.segi.uhomecp.medicaltrans.baseinfo.location.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;  
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;  
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;  
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;  
  
public class PinYinUtil {  
    /**  
     * 获取汉字串拼音首字母，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音首字母  
     */   
    public static String getFirstSpell(String chinese) {   
        StringBuffer pybf = new StringBuffer();   
        char[] arr = chinese.toCharArray();   
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
        for (int i = 0; i < arr.length; i++) {   
            if (arr[i] > 128) {   
                try {   
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                    if (temp != null) {   
                        pybf.append(temp[0].charAt(0));   
                    }   
                } catch (BadHanyuPinyinOutputFormatCombination e) {   
                    e.printStackTrace();   
                }   
            } else {   
                pybf.append(arr[i]);   
            }   
        }   
        return pybf.toString().replaceAll("\\W", "").trim();   
    }   
  
    /**  
     * 获取汉字串拼音，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音  
     */   
    public static String getFullSpell(String chinese) {   
        StringBuffer pybf = new StringBuffer();   
        char[] arr = chinese.toCharArray();   
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
        for (int i = 0; i < arr.length; i++) {   
            if (arr[i] > 128) {   
                try {   
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);   
                } catch (BadHanyuPinyinOutputFormatCombination e) {   
                    e.printStackTrace();   
                }   
            } else {   
                pybf.append(arr[i]);   
            }   
        }   
        return pybf.toString();   
    }  
    
    /**
     * 提取字符串中所有的汉字
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String intercept(String str) throws Exception {
        String regex = "[\u4E00-\u9FA5]";//汉字
    	//String regex =  "[^(a-zA-Z0-9\\u4e00-\\u9fa5)]";//字母、数字和中文
        Matcher matcher = Pattern.compile(regex).matcher(str);
 
        StringBuffer sb = new StringBuffer();
 
        while (matcher.find()) {
            sb.append(matcher.group());
        }
 
        return sb.toString();
    }
}  
