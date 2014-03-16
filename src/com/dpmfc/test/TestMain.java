package com.dpmfc.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.dpmfc.detector.AllRelationshipBuilder;
import com.dpmfc.detector.AssociationInfoDetector;
import com.dpmfc.detector.ClassDetector;
import com.dpmfc.detector.DependencyInfoDetector;
import com.dpmfc.detector.RelationshipDetector;
import com.dpmfc.detector.InheritanceInfoDetector;
import com.dpmfc.util.FileUtil;

public class TestMain {
	
	private static ArrayList<String> allClassList = new ArrayList<String>();
	/**
	 * @param args
	 * @throws Exception 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		long t = System.currentTimeMillis();
		
		FileUtil fileUtil = new FileUtil();
		File[] fileList = fileUtil.getMutiPath("D:\\JOSS-1\\JOSS-1");
		
		
		for (File filePath : fileList) {
			AllRelationshipBuilder relationshipBuilder = new AllRelationshipBuilder();
			relationshipBuilder.buildAllRelationship(filePath.toString());
		}
		
		t = System.currentTimeMillis()-t;
		System.out.println(t);
	}

}
