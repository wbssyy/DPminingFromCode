package com.dpmfc.detector;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dpmfc.bean.ProjectInfo;
import com.dpmfc.util.*;

public class AllRelationshipBuilder {
	
	private RelationDetector dependDetector, inheriDetector, associDetector;
	private ClassDetector classDetector;
//	private HashMap<String, RelationshipDetector> allRelationMap = new HashMap<>();
	private ProjectInfo projectInfo;
	private StringBuilder stringBuilder = new StringBuilder();
	
	public void buildAllRelationship(String projectPath) throws Exception{
		
		stringBuilder.append("**********" + projectPath + "**********" + "\n");
		 
		dependDetector = new DependencyInfoDetector(projectPath);
		inheriDetector = new InheritanceInfoDetector(projectPath);
		associDetector = new AssociationInfoDetector(projectPath);
		classDetector = new ClassDetector(projectPath);
		projectInfo = new ProjectInfo();
		
		//remove the classes of JDK
		HashMap depenMap = classDetector.removeJDKClass(dependDetector.getAllRelationMap());
		projectInfo.setDependencyMap(depenMap);
		
		HashMap inherMap = classDetector.removeJDKClass(inheriDetector.getAllRelationMap());
		projectInfo.setInheritanceMap(inherMap);
		
		HashMap assoMap = classDetector.removeJDKClass(associDetector.getAllRelationMap());
		projectInfo.setAssciationMap(assoMap);
		
//		allRelationMap.put("dependency", dependDetector);
//		allRelationMap.put("inheritance", inheriDetector);
//		allRelationMap.put("association", associDetector);
		
		
//		System.out.println("==========assciation");
//		stringBuilder.append("==========assciation: ");
//		printInfo(associDetector);
//		
//		System.out.println("==========dependency");
//		stringBuilder.append("==========dependency: ");
//		printInfo(dependDetector);
//		
//		System.out.println("==========inheritance");
//		stringBuilder.append("==========inheritance: ");
//		printInfo(inheriDetector);
//		
//		stringBuilder.append("\n");
//		
//		OutputUtil.outputToTXT(stringBuilder.toString());
	}
	
	public ProjectInfo getAllRelationInfo() {
		return projectInfo;
	}
	
	private void printInfo(RelationDetector relationship) {
		//count the relationships number
		int relationNum = 0;
		HashMap map = relationship.getAllRelationMap();
		
		map = classDetector.removeJDKClass(map);
		
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			HashSet<String> hashSet = (HashSet<String>)entry.getValue();
			Iterator<String> hashsetIterator = hashSet.iterator();
			
			while (hashsetIterator.hasNext()) {
				hashsetIterator.next();
				relationNum++;
			}
		}
		
		stringBuilder.append(relationNum+"\n");
	}
}
