package com.zy.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件工具类
 * 
 * @see config.properties
 * 
 * @author Pingan
 *
 * @since  2015年6月21日
 */

public class FileUploadUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	public static final String UPLOADFILE_ERROR_CODE = "400";
	
	public static String doUploadFile(MultipartFile uploadedFile, String childPath){
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("config.properties"));
			
			//本地windows环境
	    	//File catalinaBase = new File(System.getProperty( "catalina.base" )).getAbsoluteFile();
	    	//File dir = new File( catalinaBase, "webapps"+File.separator+"uploads"+File.separator);
	    	
			//生产环境
	    	File projectBasePath = new File(properties.getProperty("location.fileUploadPath")).getAbsoluteFile();
	    	File dir = new File( projectBasePath, childPath);
	        if (!dir.exists())
	            dir.mkdirs();
	        
			String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
			String timeStamp = String.valueOf(System.currentTimeMillis());
	        String newFileName = timeStamp+ "." + extension;
	        File serverFile = new File(dir.getAbsolutePath()+ File.separator + newFileName);
	        
	        //保存到服务器
	        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	        byte[] bytes = uploadedFile.getBytes();
	        stream.write(bytes);
	        stream.close();
	        logger.info("Server File Location="+ serverFile.getAbsolutePath());
	        
	        return properties.getProperty("location.fileUrl") + childPath + "/" + newFileName;
	        
	    } catch (Exception e) {
	    	logger.error("上传文件失败:",e.getMessage());
	    	e.printStackTrace();
	    	return UPLOADFILE_ERROR_CODE;
	    }
	}
	
	
}
