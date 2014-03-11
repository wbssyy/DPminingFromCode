package com.pdmfc.util;

import java.io.File;
import java.util.ArrayList;

public class FileUtil {
	
	private ArrayList<String> filePath = new ArrayList<String>();
	
	public ArrayList<String> getAllJavaFilePath(String dirName) {
		getJavaFile(dirName);
		return filePath;
	}
	
	/**
     * get all Java file 
     * @param dirName
     * @return
     */
	private void getJavaFile(String dirName){ 
    	File dir = new File(dirName);
    	File[]  fileList = dir.listFiles();
    	
    	for (File file: fileList) {
    		String path = file.getAbsolutePath();
    		if (path.endsWith(".java")) {
    			filePath.add(file.getAbsolutePath());
            }
    		else if (file.isDirectory()){
    			getJavaFile(path);
    		}
    	}
    }
    
}