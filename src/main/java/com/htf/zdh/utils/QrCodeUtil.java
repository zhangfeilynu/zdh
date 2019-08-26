package com.htf.zdh.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:url生成二维码
 * @author fei.zhang
 * @date 2019年8月25日
 */
public class QrCodeUtil {

	private static final Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);

	public static void main(String[] args) {
		// String url = "http://www.baidu.com";
		// String path = FileSystemView.getFileSystemView().getHomeDirectory() +
		// File.separator + "testQrcode";
		// String fileName = "temp2.jpg";
		// createQrCode(url, path, fileName);

	}

	public BufferedImage createQrCode(String url) {
		try {
			Map<EncodeHintType, String> hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 100, 100, hints);
			// File file = new File(path, fileName);
			// if (file.exists()
			// || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) &&
			// file.createNewFile())) {
			// writeToFile(bitMatrix, "jpg", file);
			// logger.info("二维码地址：" + file);
			// }
			BufferedImage image = toBufferedImage(bitMatrix);
			return image;

		} catch (Exception e) {
			logger.error("生成二维码失败：" + e.getMessage());
			// e.printStackTrace();
			return null;
		}

	}

	static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
}
