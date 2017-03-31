package com.mars;

import java.io.File;

public class Utils {

	public static void renameFile(File oriFile, INameRules rule) {
		String path = oriFile.getParentFile().getAbsolutePath();
		String oriName = oriFile.getName();
		String newName = rule.returnFullName(oriFile);
		if (newName == null) {
			System.err.println(newName + "Fail...");
		} else if (!oriName.equals(newName)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(path + File.separator + oriName);
			File newfile = new File(path + File.separator + newName);
			if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				System.out.println(newName + "已经存在相同名字，重命名失败！");
			else {
				System.out.println(newName + "重命名成功！");
				oldfile.renameTo(newfile);
			}
		}else{
			System.err.println( "名字相同，不处理...");
		}
	}
	
	public static String getFileType(File oriFile){
		try{
			String fileName = oriFile.getName();
			return  fileName.substring(fileName.lastIndexOf(".") + 1);
		}catch(Exception e){
			System.err.println( "无法获取后缀名");
			return "";
		}
	}

}
