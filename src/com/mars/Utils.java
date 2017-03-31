package com.mars;

import java.io.File;

public class Utils {

	public static void renameFile(File oriFile, INameRules rule) {
		String path = oriFile.getParentFile().getAbsolutePath();
		String oriName = oriFile.getName();
		String newName = rule.returnFullName(oriFile);
		if (newName == null) {
			System.err.println(newName + "Fail...");
		} else if (!oriName.equals(newName)) {// �µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ����������
			File oldfile = new File(path + File.separator + oriName);
			File newfile = new File(path + File.separator + newName);
			if (newfile.exists())// ���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ��������������
				System.out.println(newName + "�Ѿ�������ͬ���֣�������ʧ�ܣ�");
			else {
				System.out.println(newName + "�������ɹ���");
				oldfile.renameTo(newfile);
			}
		}else{
			System.err.println( "������ͬ��������...");
		}
	}
	
	public static String getFileType(File oriFile){
		try{
			String fileName = oriFile.getName();
			return  fileName.substring(fileName.lastIndexOf(".") + 1);
		}catch(Exception e){
			System.err.println( "�޷���ȡ��׺��");
			return "";
		}
	}

}
