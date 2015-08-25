package com.zy.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 图片上传工具类、包含图片大小调整
 * 上传地址配置文件：config.properties
 * 
 * @author Pingan
 *
 * @since  2015年6月13日
 */

public class ImageUploadUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageUploadUtil.class);
	public static final int IMAGE_WIDTH_DEFAULT = 500;
	public static final int IMAGE_HEIGHT_DEFAULT = 300;

	//图片上传工具 config.properties 可以修改图片上传地址
	public static String uploadFileHandler(File file,String childPath,String originalName,int width,int height){
        try {
        	if(width==0){
        		width = IMAGE_WIDTH_DEFAULT;
        	}
        	if(height==0){
        		height = IMAGE_HEIGHT_DEFAULT;
        	}
            
        	Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("config.properties"));
        	
        	// 本地开发环境路径
        	//File catalinaBase = new File(System.getProperty( "catalina.base" )).getAbsoluteFile();
            //File projectBasePath = new File(System.getProperty("user.dir")).getAbsoluteFile();
        	
            File projectBasePath = new File(properties.getProperty("location.photoUpload")).getAbsoluteFile();
        	File dir = new File(projectBasePath, childPath);
            if (!dir.exists())
                dir.mkdirs();
            
            String extensionName = originalName.substring(originalName.lastIndexOf(".") + 1);
            String newFileName = String.valueOf(System.currentTimeMillis())+ "." + extensionName;
            File serverFile = new File(dir.getAbsolutePath()+ File.separator + newFileName);
            
            //调整大小
            BufferedImage bufferedImage = createResizedCopy(ImageIO.read(file),width,height,true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, extensionName, baos);
            baos.flush();
            byte[] bytes = baos.toByteArray();
            baos.close();
            
            //保存到服务器
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            logger.info("Server File Location="+ serverFile.getAbsolutePath());
            
            return properties.getProperty("location.photoUrl") + childPath + "/" + newFileName;
            
        } catch (Exception e) {
        	logger.error("上传图片失败:",e.getMessage());
            return "400";
        }
	}
		
	private	static BufferedImage createResizedCopy(BufferedImage originalImage, int scaledWidth, 
			int scaledHeight, boolean preserveAlpha){
    
    	int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    	BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    	Graphics2D g = scaledBI.createGraphics();
    	if (preserveAlpha) {
    		g.setComposite(AlphaComposite.Src);
    	}
    	g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
    	g.dispose();
    	return scaledBI;
	}
}
