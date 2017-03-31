package com.mars;

import java.io.File;
import java.util.ArrayList;

public class BaseFormateName {

	public static ArrayList<File> getFileArray(String path) {
		File file = new File(path);
		File[] tempList = file.listFiles();
		ArrayList<File> fileArray = new ArrayList<File>();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				fileArray.add(tempList[i]);
			}
		}
		return fileArray;
	}
	
}
