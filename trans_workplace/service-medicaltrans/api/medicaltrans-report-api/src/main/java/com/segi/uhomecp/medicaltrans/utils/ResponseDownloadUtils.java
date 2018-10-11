package com.segi.uhomecp.medicaltrans.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseDownloadUtils {
	private static Logger log = LoggerFactory.getLogger(ResponseDownloadUtils.class);

	public static void download(HttpServletResponse response, String path) throws Exception {
		File f = new File(path);
		download(response, f, f.getName());
	}
	
	public static void download(HttpServletResponse response, String path, String fileName) throws Exception {
		File f = new File(path);
		download(response, f, fileName);
	}

	public static void download(HttpServletResponse response, File file, String fileName) throws Exception {
		if (!file.exists()) {
			return;
		}
		download(response, new FileInputStream(file), fileName);
	}
	
	public static void downloadAndDelete(HttpServletResponse response, File file, String fileName) throws Exception {
        if (!file.exists()) {
            return;
        }
        download(response, new FileInputStream(file), fileName);
        file.delete();
    }
	
	public static void download(HttpServletResponse response, FileInputStream fis, String fileName) {
		BufferedInputStream br = null;
		OutputStream out = null;
		try {
			if (fis == null) {
				response.sendError(404, "File not found!");
				return;
			}
			br = new BufferedInputStream(fis);
			byte[] buf = new byte[1024];
			int len = 0;

			response.reset();

			String fileName2 = new String(fileName.getBytes(), "ISO8859-1");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName2);

			out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			out.flush();
			br.close();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				log.error("", e);
			}

		}
	}
}
