package com.dpmfc.detector;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dpmfc.util.*;

public class AllRelationshipBuilder {
	
	private RelationshipDetector dependDetector, inheriDetector, associDetector;
	private ClassDetector classDetector;
	private StringBuilder stringBuilder = new StringBuilder();
	
	public void buildAllRelationship(String projectPath) throws Exception{
		
		stringBuilder.append("**********" + projectPath + "**********" + "\n");
		 
		dependDetector = new DependencyInfoDetector(projectPath);
		inheriDetector = new InheritanceInfoDetector(projectPath);
		associDetector = new AssociationInfoDetector(projectPath);
		classDetector = new ClassDetector(projectPath);
		
		System.out.println("==========assciation");
		stringBuilder.append("==========assciation: ");
		printInfo(associDetector);
		
		System.out.println("==========dependency");
		stringBuilder.append("==========dependency: ");
		printInfo(dependDetector);
		
		System.out.println("==========inheritance");
		stringBuilder.append("==========inheritance: ");
		printInfo(inheriDetector);
		
		stringBuilder.append("\n");
		
		OutputUtil.outputToTXT(stringBuilder.toString());
	}
	
	private void printInfo(RelationshipDetector relationship) {
		//count the relationships number
		int relationNum = 0;
		HashMap map = relationship.getAllRelationMap();
		
		map = classDetector.removeJDKClass(map);
		
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			HashSet<String> hashSet = (HashSet<String>)entry.getValue();
			Iterator<String> hashsetIterator = hashSet.iterator();
			
//			System.out.println("-------------------------"+entry.getKey());
//			stringBuilder.append("-------------------------"+entry.getKey()+"\n");
			
			while (hashsetIterator.hasNext()) {
//				System.out.println(relationNum + ". " + hashsetIterator.next());
//				stringBuilder.append(relationNum + ". " + hashsetIterator.next()+"\n");
				hashsetIterator.next();
				relationNum++;
			}
		}
		
		stringBuilder.append(relationNum+"\n");
	}
}
