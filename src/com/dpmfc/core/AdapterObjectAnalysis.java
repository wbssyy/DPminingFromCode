package com.dpmfc.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dpmfc.bean.ProjectInfo;
import com.dpmfc.bean.Weight;

public class AdapterObjectAnalysis extends StructureAnalysis{
	
	//weight of each role of the pattern
	private int adapterW = Weight.INHERITANCE_A * Weight.ASSOCIATION_A;	//55;
	private int targetW  = Weight.INHERITANCE_B;	//7;
	private int adapteeW = Weight.ASSOCIATION_B;	//13;
	private static int number = 0;

	@Override
	public void doStructureAnalyze(ProjectInfo projectInfo) {
		
		HashMap weightMap      = projectInfo.getWeightMap();
		HashMap inheritanceMap = projectInfo.getInheritanceMap();
		HashMap associationMap = projectInfo.getAssciationMap();
		
		Iterator iterator = weightMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String adapterName = entry.getKey().toString();
			Integer weight = (Integer)entry.getValue();
			
			if (weight % adapterW == 0) {
				System.out.println("there are some adapter candidates!");
				//this class is a adapter example of candidate
				
				//get target candidates through inheritanceMap
				HashSet inheritanceSet = (HashSet)inheritanceMap.get(adapterName);
				
				//get adaptee candidate through associationMap
				HashSet associationSet = (HashSet)associationMap.get(adapterName);
					
				Object[] inheritanceObjects = null;
				if (inheritanceSet != null) {
					inheritanceObjects = inheritanceSet.toArray();
				}
				Object[] associationObjects = null;
				if (associationSet != null) {
					associationObjects = associationSet.toArray();
				}
				
				
				for (Object inheriObject : associationObjects) {
					for (Object assoObject : inheritanceObjects) {
						String classA = inheriObject.toString();
						String classB = assoObject.toString();
						if (!projectInfo.hasRelation(classA, classB) && !classA.equals(classB)) {
							number++;
							
							System.out.println(number + ". adapter: " + adapterName);
							System.out.println("target: " + classA);
							System.out.println("adaptee: " + classB + "\n");
						}
					}
				}
			}
		}
	}
	
}
