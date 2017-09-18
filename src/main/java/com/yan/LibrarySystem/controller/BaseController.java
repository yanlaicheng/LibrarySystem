package com.yan.LibrarySystem.controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yan.LibrarySystem.utils.UserException;


public class BaseController {
	
	
	
	@InitBinder
	// 必须有一个参数WebDataBinder
	public void initBinder(WebDataBinder binder) {
		//System.out.println(binder.getFieldDefaultPrefix());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	 
		binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() { 
				return (getValue() == null) ? "" : getValue().toString();
			} 
			@Override
			public void setAsText(String text) {
				Integer value = null;
				if (null != text && !text.equals("")) {  
						try {
							value = Integer.valueOf(text);
						} catch(Exception ex)  { 
							throw new UserException("数据格式输入不正确！"); 
						}  
				}
				setValue(value);
			} 
		});
	  
		//binder.registerCustomEditor(Integer.class, null,new CustomNumberEditor(Integer.class, null, true));
		
		binder.registerCustomEditor(Float.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() { 
				return (getValue() == null)? "" : getValue().toString();
			} 
			@Override
			public void setAsText(String text)  {
				Float value = null;
				if (null != text && !text.equals("")) {
					try {
						value = Float.valueOf(text);
					} catch (Exception e) { 
						throw new UserException("数据格式输入不正确！"); 
					}
				}
				setValue(value);
			}
		});
	}
 
	/** 
	 * 处理图片文件上传，返回保存的文件名路径
	 * fileKeyName: 图片上传表单key
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */ 
	public String handlePhotoFileUpload(HttpServletRequest request,String fileKeyName) throws IllegalStateException, IOException {
		String fileName = "upload/NoImage.jpg";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
        /**构建图片保存的目录**/    
        String photoBookPathDir = "/resources/upload";     
        /**得到图片保存目录的真实路径**/    
        String photoBookRealPathDir = request.getSession().getServletContext().getRealPath(photoBookPathDir);     
        /**根据真实路径创建目录**/    
        File photoBookSaveFile = new File(photoBookRealPathDir);     
        if(!photoBookSaveFile.exists())     
        	photoBookSaveFile.mkdirs();           
        /**页面控件的文件流**/    
        MultipartFile multipartFile_photoBook = multipartRequest.getFile(fileKeyName);    
        if(!multipartFile_photoBook.isEmpty()) {
        	/**获取文件的后缀**/    
            String suffix = multipartFile_photoBook.getOriginalFilename().substring  
                            (multipartFile_photoBook.getOriginalFilename().lastIndexOf("."));     
            /**使用UUID生成文件名称**/    
            String photoBookFileName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
            //String logImageName = multipartFile.getOriginalFilename();  
            /**拼成完整的文件保存路径加文件**/    
            String photoBookFilePath = photoBookRealPathDir + File.separator  + photoBookFileName;                
            File photoBookFile = new File(photoBookFilePath);          
           
            multipartFile_photoBook.transferTo(photoBookFile);     
            
            fileName = "upload/" + photoBookFileName;
        } 
		
		return fileName;
	}


}