package com.gpzuestc.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-2-2
 * @todo: 
 * 
 */
public class FileUtil {
	
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 根据路径读取文件内容
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static byte[] readFile(String path) throws Exception{
		InputStream is = new FileInputStream(new File(path));
		byte[] tempBytes = new byte[is.available()];
		is.read(tempBytes);
		return tempBytes;
	}
	
	/**
	 * 向文件中追加内容
	 * @param toAppendPath
	 * @param appendPath
	 * @throws Exception
	 */
//	public static void appendFile(String toAppendPath, String appendPath) throws Exception {
//        try {
//        	File f = new File(toAppendPath);
//        	if(!f.exists()){ //如果文件不存在则创建
//        		if(!createFile(toAppendPath)){
//        			throw new Exception("create file fail when append file;toAppendpath=" + toAppendPath);
//        		}
//        	}
//            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
//            FileWriter writer = new FileWriter(toAppendPath, true);
//            writer.write(new String(readFile(appendPath)));
//            writer.close();
//        } catch (IOException e) {
//            log.error("error=append file content to another file fail;toAppendPath=" + toAppendPath + ";appendPath=" + appendPath, e);
//            throw e;
//        }
//    }
	
	/**
	 * 创建文件
	 * @param destFileName
	 * @return
	 */
//	public static boolean createFile(String destFileName) {
//        File file = new File(destFileName);
//        if(file.exists()) {
//            log.error("error=this file has been exist;filePath=" + destFileName);
//            return false;
//        }
//        if (destFileName.endsWith(File.separator)) {
//            log.error("error=arg error when create file;filePath=" + destFileName);
//            return false;
//        }
//        //判断目标文件所在的目录是否存在
//        if(!file.getParentFile().exists()) {
//            //如果目标文件所在的目录不存在，则创建父目录
//            if(!file.getParentFile().mkdirs()) {
//            	log.error("error=create file's parent dir fail");
//                return false;
//            }
//        }
//        //创建目标文件
//        try {
//            return file.createNewFile();
//        } catch (IOException e) {
//            log.error("error=create new file fail;path=" + destFileName, e);
//            return false;
//        }
//    }
   
	/**
	 * 创建目录
	 * @param destDirName
	 * @return
	 */
//    public static boolean createDir(String destDirName) {
//        File dir = new File(destDirName);
//        if (dir.exists()) {
//        	log.error("error=this dir has been exist;filePath=" + destDirName);
//            return false;
//        }
//        if (!destDirName.endsWith(File.separator)) {
//            destDirName = destDirName + File.separator;
//        }
//        //创建目录
//        return dir.mkdirs();
//    }
    
    /**
     * 删除文件
     * @param path
     * @return
     */
//    public static boolean deleteFile(String path){
//    	File file = new File(path);
//		if (file.isFile() && file.exists()) {
//			return file.delete();
//		}else{
//			log.error("error=delete file fail;path=" + path + ";isFile=" + file.isFile() + ";exists=" + file.exists());
//			return false;
//		}
//    }
    
    /**
     * 删除目录
     * @param path
     * @return
     */
//    public static boolean deleteDir(String path) {
//    	
//        //如果sPath不以文件分隔符结尾，自动添加文件分隔符   
//        if (!path.endsWith(File.separator)) {   
//            path = path + File.separator;   
//        }   
//        
//        File dir = new File(path);   
//        //如果dir对应的文件不存在，则退出   
//        if (!dir.exists()) {
//        	log.warn("warn=the file or dir to be deleted doesn't exists;path=" + path);
//            return true; 
//        } 
//        
//        if(!dir.isDirectory()){
//        	log.error("error=the file isn't a directory;path=" + path);
//        	return false;
//        }
//        
//        //删除文件夹下的所有文件(包括子目录)   
//        File[] files = dir.listFiles();   
//        for (int i = 0; i < files.length; i++) {  
//            if (files[i].isFile()) {//删除子文件 
//                deleteFile(files[i].getAbsolutePath());
//            }else{//删除子目录   
//                deleteDir(files[i].getAbsolutePath());
//            }   
//        }
//        
//        //删除当前目录   
//        return dir.delete();
//    }  
    
    public static void main(String[] args) {
		File f = new File("/Users/gpzuestc/Documents/workspace/res-pub-plugin/./js1");
		System.out.println(f.isDirectory());
	}
}
