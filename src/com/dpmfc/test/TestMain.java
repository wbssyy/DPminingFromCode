package com.dpmfc.test;

import java.io.File;
import java.util.ArrayList;

import com.dpmfc.bean.ProjectInfo;
import com.dpmfc.core.*;
import com.dpmfc.detector.AllRelationshipBuilder;
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
		File[] fileList = fileUtil.getMutiPath("D:\\test");
		
		
		for (File filePath : fileList) {
			AllRelationshipBuilder relationshipBuilder = new AllRelationshipBuilder();
			relationshipBuilder.buildAllRelationship(filePath.toString());
			
			ProjectInfo projectInfo = relationshipBuilder.getAllRelationInfo();
			
			WeightCalculator calculator = new WeightCalculator();
			calculator.calculateWeight(projectInfo);
			calculator.printWeightMap();
			
			projectInfo.setWeightMap(calculator.getWeightMap());
			
			StructureAnalysis structureAnalysis = new BridgeAnalysis();
			structureAnalysis.doStructureAnalyze(projectInfo);
			
//			StructureAnalysis structureAnalysis1 = new ProxyAnalysis2();
//			structureAnalysis1.doStructureAnalyze(projectInfo);
			
		}
		
		t = System.currentTimeMillis()-t;
		System.out.println(t);
	}

}
