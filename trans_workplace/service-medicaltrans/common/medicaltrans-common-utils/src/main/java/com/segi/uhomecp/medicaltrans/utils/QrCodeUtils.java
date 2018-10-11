package com.segi.uhomecp.medicaltrans.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * @description 
 * 二维码工具类
 * 
 * @since 2015年9月6日 上午10:31:39 
 * 
 */
public class QrCodeUtils {
	   
	private static final String CHARSET = "utf-8";  
	private static final String FORMAT_NAME = "JPG";  
	// 二维码尺寸  
	private static final int QRCODE_SIZE = 300;  
	// LOGO宽度  
	private static final int WIDTH = 60;  
	// LOGO高度  
	private static final int HEIGHT = 60;  
   
    private static BufferedImage createImage(String content, String logoImgPath,  
            boolean needCompress) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 
                        : 0xFFFFFFFF);  
            }  
        }  
        if (logoImgPath == null || "".equals(logoImgPath)) {  
            return image;  
        }  
        // 插入图片  
        insertImage(image, logoImgPath, needCompress);  
        return image;  
    }  
   
    /** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param logoImgPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */ 
    private static void insertImage(BufferedImage source, String logoImgPath,  
            boolean needCompress) throws Exception {  
        File file = new File(logoImgPath);  
        if (!file.exists()) {  
            System.err.println(""+logoImgPath+"   该文件不存在！");  
            return;  
        }  
        Image src = ImageIO.read(new File(logoImgPath));  
        int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (needCompress) { // 压缩LOGO  
            if (width > WIDTH) {  
                width = WIDTH;  
            }  
            if (height > HEIGHT) {  
                height = HEIGHT;  
            }  
            Image image = src.getScaledInstance(width, height,  
                    Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            src = image;  
        }  
        // 插入LOGO  
        Graphics2D graph = source.createGraphics();  
        int x = (QRCODE_SIZE - width) / 2;  
        int y = (QRCODE_SIZE - height) / 2;  
        graph.drawImage(src, x, y, width, height, null);  
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
        graph.setStroke(new BasicStroke(3f));  
        graph.draw(shape);  
        graph.dispose();  
    }  
   
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param logoImgPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param destImgName           
     *            目标二维码文件名称   
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */ 
    public static void encode(String content, String logoImgPath, String destPath,  String destImgName,
            boolean needCompress) throws Exception {  
        BufferedImage image = createImage(content, logoImgPath,  
                needCompress);  
        mkdirs(destPath);  
        ImageIO.write(image, FORMAT_NAME, new File((destPath.endsWith("/")?destPath+destImgName:destPath+"/"+destImgName)));  
    }  
   

    public static void mkdirs(String destPath) {  
        File file =new File(destPath);      
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)  
        if (!file.exists() && !file.isDirectory()) {  
            file.mkdirs();  
        }  
    }  
   
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param logoImgPath 
     *            LOGO地址 
     * @param destPath 
     *            存储地址 
     * @param destImgName           
     *            目标二维码文件名称   
     * @throws Exception 
     */ 
    public static void encode(String content, String logoImgPath, String destPath,String destImgName)  
            throws Exception {  
        encode(content, logoImgPath, destPath,destImgName ,true);  
    }  
   
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @param destImgName           
     *            目标二维码文件名称   
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */ 
    public static void encode(String content, String destPath ,String destImgName,
            boolean needCompress) throws Exception {  
        encode(content, null, destPath,destImgName, needCompress);  
    }  
   
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @param destImgName           
     *            目标二维码文件名称   
     * @throws Exception 
     */ 
    public static void encode(String content, String destPath,String destImgName) throws Exception {  
        encode(content, null, destPath,destImgName, false);  
    }  
   
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param logoImgPath 
     *            LOGO地址 
     * @param output 
     *            输出流 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */ 
    public static void encode(String content, String logoImgPath,  
            OutputStream output, boolean needCompress) throws Exception {  
        BufferedImage image = createImage(content, logoImgPath,  
                needCompress);  
        ImageIO.write(image, FORMAT_NAME, output);  
    }  
   
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param output 
     *            输出流 
     * @throws Exception 
     */ 
    public static void encode(String content, OutputStream output)  
            throws Exception {  
        encode(content, null, output, false);  
    }  
   
    /** 
     * 解析二维码 
     *  
     * @param file 
     *            二维码图片 
     * @return 
     * @throws Exception 
     */ 
    public static String decode(File file) throws Exception {  
        BufferedImage image;  
        image = ImageIO.read(file);  
        if (image == null) {  
            return null;  
        }  
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(  
                image);  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
        Result result;  
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();  
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);  
        result = new MultiFormatReader().decode(bitmap, hints);  
        String resultStr = result.getText();  
        return resultStr;  
    }  
   
    /** 
     * 解析二维码 
     *  
     * @param path 
     * 二维码图片地址 
     * @return 
     * @throws Exception 
     */ 
    public static String decode(String path) throws Exception {  
        return decode(new File(path));  
    }  
   
    public static void main(String[] args) throws Exception { 
//    	System.out.print(QrCodeUtils.class.getClassLoader().getResource("static").getPath());
        String text = "http://www.baidu.com/s?word=%E7%8E%9B%E9%9B%85&tn=99682755_hao_pg&ie=utf-8&ssl_sample=hao_1";  
        encode(text, "D:/IMG_3514.JPG", "D:/","aa.jpg", true);  
    }  
}
