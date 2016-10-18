package com.razorthink.model.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {

	public static String getDirectoryName(String installationLocation, String condition) {
		String directory = "";
		File[] root = new File(installationLocation).listFiles();
		if (root != null && root.length > 0) {
			for (File file : root) {
				if (file.isDirectory() && file.getName().toLowerCase().contains(condition.toLowerCase())) {
					directory = file.getName();
					break;
				}
			}
		}
		return directory;

	}

	public static String getFileContents(File file) {

		try {

			String line = null;
			String contents = "";

			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {

				contents += line + "\n";

			}
			reader.close();
			return contents;

		} catch (Exception e) {
			return null;

		}

	}

	public static InputStream getFileInputStream(String fileName) throws Exception {
		InputStream is = FileUtils.class.getResourceAsStream("/" + fileName);
		if (is == null) {
			throw new Exception();
		}
		return is;
	}

	public static void appendToFile(String filePath, String line) {
		try {
			new File(filePath.substring(0, filePath.lastIndexOf("/"))).mkdirs();
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
			out.println(line);
			out.close();
		} catch (IOException e) {
			new Exception(e);
		}

	}

	public static String readFromFile(String filePath, Integer numLines) {

		BufferedReader in = null;
		String line = "";

		StringBuffer sb = new StringBuffer();
		try {
			in = new BufferedReader(new FileReader(filePath));
			try {
				int count = 0;
				while ((numLines == null || count < numLines) && (line = in.readLine()) != null) {
					sb.append(line + "\n");
					count++;
				}

				in.close();
			} catch (IOException e) {
				new Exception(e);
			}
		} catch (FileNotFoundException e) {
			new Exception(e);
		}

		return sb.toString();
	}

	public static String readContentAsString(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		return new String(data, "UTF-8");
	}

	public static void unZip(ZipInputStream zipInputStream, String outputFolder) {
		try {
			ZipEntry zipEntry = zipInputStream.getNextEntry();

			File folder = new File(outputFolder);
			if (!folder.isDirectory()) {
				folder.mkdir();
			}
			if (zipEntry == null) {
				return;
			}
			while (zipEntry != null) {

				String newFilePath = outputFolder + zipEntry.getName();
				if (!zipEntry.isDirectory()) {
					extractZipFile(zipInputStream, newFilePath);
				} else {
					new File(newFilePath).mkdir();
				}

				zipEntry = zipInputStream.getNextEntry();
			}

			zipInputStream.closeEntry();
			zipInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			new Exception(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void extractZipFile(ZipInputStream zipInputStream, String filePath) {
		try {
			File inputFile = new File(filePath);
			String parentFilePath = inputFile.getParent();
			if (!(new File(parentFilePath).isDirectory()))
				new File(parentFilePath).mkdirs();

			FileOutputStream fos = new FileOutputStream(inputFile);
			byte[] buffer = new byte[1024];

			int length;
			while ((length = zipInputStream.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}

			fos.close();
		} catch (FileNotFoundException e) {
			      new Exception(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
