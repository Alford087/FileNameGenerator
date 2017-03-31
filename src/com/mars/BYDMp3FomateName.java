package com.mars;

import java.io.File;
import java.util.ArrayList;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class BYDMp3FomateName extends BaseFormateName{

	public static void main(String[] args) {
		run("d:/music/");
	}

	public static void run(String path) {
		ArrayList<File> list = getFileArray(path);
		for (File file : list) {
			Utils.renameFile(file,new MP3Rule());
		}
	}
	
	public static class MP3Rule implements INameRules{

		public String returnFullName(File oriFile) {
			System.out.println( "oriFile name £º "+oriFile.getName());
			String pinyinName = "";
			// StringBuffer pinyinNameSB = new StringBuffer();
			// pinyinNameSB.append("");
			char[] nameChar = oriFile.getName().toCharArray();
			HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
			defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			char targetChar = 0;
			for (int i = 0; i < nameChar.length; i++) {
				if (nameChar[i] > 128) {
					try {
						targetChar=nameChar[i];
						if(isChinese(targetChar)){
							pinyinName += PinyinHelper.toHanyuPinyinStringArray(
									targetChar, defaultFormat)[0];
						}else{
							System.err.println("targetChar is other code , value = "+targetChar+"|");
							return oriFile.getName();
						}
					} catch (Exception e) {
						System.err.println(e.getMessage());
						return oriFile.getName();
					}
				} else {
					pinyinName += nameChar[i];
				}
			}
			return pinyinName;
		}
		
		/**
		 * ¸ù¾ÝUnicode±àÂëÅÐ¶ÏÖÐÎÄºº×ÖºÍ·ûºÅ
		 * @param c
		 * @return
		 */
	    private boolean isChinese(char c) {
	        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
	                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
	                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
	            return true;
	        }
	        return false;
	    }
	}
	
	

}
