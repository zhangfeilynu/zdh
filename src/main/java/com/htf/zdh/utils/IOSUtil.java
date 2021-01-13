package com.htf.zdh.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOSUtil {

	private static final Logger logger = LoggerFactory.getLogger(IOSUtil.class);

	public static void main(String[] args) {
		 String path = "E:\\htf\\upload\\";

		 createPlist(path, "xxxy", "com.htf.mclient.Uat35","现金宝",
		 "http://10.50.16.230/sit/ios/5.5/HTF_5.50_201909041100.ipa");
		 createHtml(path, "xxxx", "sss");
		// encryption("sgshhg\\\\\\///// ****%%%%$$$$ 飒飒看");
//		String str = "1.12.0";
//		if (testNum(str)) {
//			logger.info("正确");
//		} else {
//			logger.info("错误");
//		}

	}

	public static void createPlist(String path, String fileName, String bundleId, String appName, String url) {
		logger.info("==========开始创建plist文件");
		String plistFile = fileName + ".plist";
		String plistPath = path + plistFile;
		File file = new File(plistPath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		String plist = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
//				+ "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n"
//				+ "<plist version=\"1.0\">\n" + "<dict>\n" + "<key>items</key>\n" + "<array>\n" + "<dict>\n"
//				+ "<key>assets</key>\n" + "<array>\n" + "<dict>\n" + "<key>kind</key>\n"
//				+ "<string>software-package</string>\n" + "<key>url</key>\n"
//				// 参数url配置ipa文件的下载路径，参数的值在下面一行设置
//				+ "<string>" + url + "</string>\n" + "</dict>\n" + "</array>\n" + "<key>metadata</key>\n" + "<dict>\n"
//				+ "<key>bundle-identifier</key>\n"
//				// 参数bundle-identifier是开发者账号用户名，可以为空或任意，区别在于安装的过程中有无图标和进度，参数的值在下面一行设置
//				+ "<string>" + bundleId + "</string>\n" + "<key>kind</key>\n" + "<string>software</string>\n"
//				+ "<key>title</key>\n" + "<string>" + appName + "</string>\n" + "</dict>\n" + "</dict>\n" + "</array>\n"
//				+ "</dict>\n" + "</plist>";

		String plist = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n"
				+ "<plist version=\"1.0\">\n" + "<dict>\n" + "<key>items</key>\n" + "<array>\n" + "<dict>\n"
				+ "<key>assets</key>\n" + "<array>\n" + "<dict>\n" + "<key>kind</key>\n"
				+ "<string>software-package</string>\n" + "<key>url</key>\n"
				// 参数url配置ipa文件的下载路径，参数的值在下面一行设置
				+ "<string>" + url + "</string>\n" + "</dict>\n" +
				"<dict>\n"+"<key>kind</key>\n"+"<string>display-image</string>\n"+"<key>url</key>\n"+"<string>http://10.50.16.230/57.png</string>\n"+"<dict>\n"+
				"<dict>\n"+"<key>kind</key>\n"+"<string>full-size-image</string>\n"+"<key>url</key>\n"+"<string>http://10.50.16.230/512.png</string>\n"+"<dict>\n"+
				"</array>\n" + "<key>metadata</key>\n" + "<dict>\n"
				+ "<key>bundle-identifier</key>\n"
				// 参数bundle-identifier是开发者账号用户名，可以为空或任意，区别在于安装的过程中有无图标和进度，参数的值在下面一行设置
				+ "<string>" + bundleId + "</string>\n" + "<key>kind</key>\n" + "<string>software</string>\n"
				+ "<key>title</key>\n" + "<string>" + appName + "</string>\n" + "</dict>\n" + "</dict>\n" + "</array>\n"
				+ "</dict>\n" + "</plist>";
		FileOutputStream output = null;
		OutputStreamWriter writer = null;
		try {
			output = new FileOutputStream(file);
			writer = new OutputStreamWriter(output, "UTF-8");
			writer.write(plist);
			// writer.close();
			// output.close();
		} catch (Exception e) {
			logger.error("==========创建plist文件异常：" + e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}

		}
		logger.info("==========成功创建plist文件");

	}

	public static void createHtml(String path, String fileName, String url) {

		logger.info("==========开始创建html文件");
		String plistFile = fileName + ".html";
		String plistPath = path + plistFile;
		File file = new File(plistPath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String plist = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
				+ "<title>下载</title>\n" + "<script type=\"text/javascript\">\n" + "var url = '" + url + "';\n"
				+ "window.location.href = \"itms-services://?action=download-manifest&url=\" + url;\n" + "</script>\n"
				+ "</head>\n" + "<body></body>\n" + "</html>";

		FileOutputStream output = null;
		OutputStreamWriter writer = null;
		try {
			output = new FileOutputStream(file);
			writer = new OutputStreamWriter(output, "UTF-8");
			writer.write(plist);
			// writer.close();
			// output.close();
		} catch (Exception e) {
			logger.error("==========创建html文件异常：" + e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}

		}
		logger.info("==========成功创建html文件");

	}

	public static String encryption(String plain) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			// re_md5 = buf.toString();// 32位加密
			// logger.info("32位：" + re_md5);
			// logger.info("16位:" + buf.toString().substring(8, 24));
			re_md5 = buf.toString().substring(8, 24);// 16位加密

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

	public static boolean testStr(String str) {// 判断是纯字母
		String regEx = "[^a-zA-Z]"; //
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		boolean result = m.find();
		return !result;
	}

	public static boolean testNum(String str) {// 判断是正数，含小数
		String regEx = "^\\d+(\\.\\d+)?$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		boolean result = m.find();
		return result;
	}

}
