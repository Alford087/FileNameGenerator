package com.mars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class FormateImageName extends BaseFormateName {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		doFormate("D:\\Workspaces\\ReactNative\\react-native\\react-native-doc\\image");
	}

	public static void doFormate(String path) {
		ArrayList<File> list = getFileArray(path);
		for (File file : list) {
			Utils.renameFile(file, new ImageNameRule());
		}
	}

	public static class ImageNameRule implements INameRules {

		public String returnFullName(File oriFile) {
			if (oriFile == null) {
				return null;
			}
			String prefix = Utils.getFileType(oriFile);
			FileInputStream fis = null;
			BigInteger bigInt = null;
			try {
				fis = new FileInputStream(oriFile);
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] buffer = new byte[1024];
				int length = -1;
				while ((length = fis.read(buffer, 0, 1024)) != -1) {
					md.update(buffer, 0, length);
				}
				bigInt = new BigInteger(1, md.digest());
				return String.valueOf(bigInt.toString(16)) + "."
						+ prefix;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		}

	}

}
