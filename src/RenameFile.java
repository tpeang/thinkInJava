import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class RenameFile {

	private static void reName(String path) {
		/*
		 * File f = new File("d:/old.txt"); String c = f.getParent(); File mm =
		 * new File(c + File.pathSeparator + "new.txt"); if (f.renameTo(mm)) {
		 * System.out.println("修改成功!"); } else { System.out.println("修改失败"); }
		 */

		File root = new File(path);
		File[] files;
		String fileName;
		String parentRoot;
		files = root.listFiles();
		File newFile;

		int count = 0;
		for (File file : files) {
			// File newFile = new File(path+File.pathSeparator+fileName+1);
			// file.getName()
			// 获取文件名
			fileName = file.getName();
			parentRoot = file.getParent();
			// System.out.println(parentRoot+File.separator+fileName);
			// 修改文件名

			fileName = fileName
					.replace(".中英字幕.HR-HDTV.AC3.1024X576.x264", "")
					.replace(".中英字幕.HDTVrip.1024X576", "")
					.replace(".中英字幕.WEB-HR.AC3.1024X576.x264", "")
					.replace(".中英字幕.WEB-HR.AAC.1024X576.x264", "")
					.replace(".576p.WEB-HR.AC3.x264.双语字幕-深影字幕组", "")
					.replace(".576p.HDTV.AC3.x264.双语字幕-深影字幕组", "")
					.replace(".中英字幕.WEBrip.1024X576", "")
					.replace(".中英字幕.HDTVrip.1024X576", "")
					.replace(".中英字幕.HDTVrip.AC3.1024X576", "")
					.replace(".480p.中英字幕.HDTVrip", "")
					.replace(".End.中英字幕.WEBrip.1024X576", "")
					.replace(".Chi_Eng.HR-HDTV.AC3.1024x576.x264-YYeTs人人影视", "")
					.replace("迷失.", "")
					.replace(".Chi_Eng.HR-BluRay.AC3.1024X512.x264-YYeTs人人影视", "")
					.replace(".中英字幕.HR-BluRay.AC3.1024X512.x264", "")
					.replace("2013","")
					.replace("..", "")
					;
			newFile = new File(parentRoot + File.separator + fileName);
			System.out.println(newFile.getName());
			//  file.renameTo(newFile);

			// System.out.println(file.getName());

		}
	}

	public static void insertPackage(String path, String regex, String addPackge) {
		File root = new File(path);
		// String[] fileList = root.list();
		File[] fileList = root.listFiles(new DirFilter(regex));
		System.out.println(fileList.length);
		for (File file : fileList) {

			try {

				BufferedReader in = new BufferedReader(new FileReader(file));

				String s;
				boolean flag = false;
				StringBuffer sb = new StringBuffer();
				BufferedWriter out;
				while ((s = in.readLine()) != null) {

					sb.append(s);
					sb.append("\r\n");

				}

				flag = Pattern.compile("(package)\\s(io;)[\\w\\W]*")
						.matcher(sb).matches();
				System.out.println(flag);
				if (!flag) {
					System.out.println(flag);
					System.out.println(sb);
					out = new BufferedWriter(new FileWriter(
							file.getAbsoluteFile()));
					out.append(addPackge);
					out.append("\r\n");
					out.append(sb);
					out.close();
				}

				/*
				 * System.out.println(sb); out = new BufferedWriter(new
				 * FileWriter(file.getAbsoluteFile())); out.append(addPackge);
				 * out.append("\r\n"); out.append(sb); out.close();
				 */

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		reName("D://UserFiles//视频//tv//纸牌屋//纸牌屋第二季");
		/*
		 * String regex = "\\w*\\.java"; String addPackage = "package io;";
		 * insertPackage("./src/io", regex, addPackage);
		 */
	}

}

class DirFilter implements FilenameFilter {
	private Pattern pattern;

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}

	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
}
