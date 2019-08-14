package com.htf.zdh.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static void main(String[] args) {

	}

	public String uploadFile(CommonsMultipartFile file) {

		String path = "F:/htf/upload" + new Date().getTime() + file.getOriginalFilename();
		File newFile = new File(path);
		try {
			file.transferTo(newFile);
		} catch (IllegalStateException | IOException e) {
			logger.error("保存文件出现异常：" + e.getMessage());
		}
		return path;
	}

	public static void uploadFile(byte[] file, String filePath, String fileName) {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath + fileName);
			out.write(file);
			out.flush();

		} catch (Exception e) {
			logger.error("上传文件异常：" + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("文件流关闭异常:" + e.getMessage());
				}
			}
		}

	}

}
