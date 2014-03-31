package com.dpmfc.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.dpmfc.bean.ProjectInfo;
import com.dpmfc.bean.Weight;

public class CompositeAnalysis extends StructureAnalysis {

	//weight of each role of the pattern
	private int componentW = Weight.INHERITANCE_B * Weight.INHERITANCE_B * Weight.ASSOCIATION_B; //637;
	private int compositeW = Weight.INHERITANCE_A * Weight.ASSOCIATION_A; //55;
	private int leafW      = Weight.INHERITANCE_A; //5;
	private static int number = 0;
	
	@Override
	public void doStructureAnalyze(ProjectInfo projectInfo) {
		
		HashMap inheritanceMap = projectInfo.getInheritanceMap();
		
		HashMap<Integer, List<String>> specialWeightMap = projectInfo.getContainWClassMap();
		List<String> componentList = specialWeightMap.get(componentW);
		List<String> compositeList = specialWeightMap.get(compositeW);
		
		for (String composite : compositeList) {
			for (String component : componentList) {
				HashSet<String> set = (HashSet)inheritanceMap.get(composite);
				if (set.contains(component)) {
					printResult(component, composite, set);
				}
			}
		}
	}
	
	private void printResult(String component, String composite, HashSet<String> set) {
		number++;
		System.out.println(number + ". component: " + component);
		System.out.println("composite: " + composite);
		System.out.print("leaf: ");
		for (String leaf : set) {
			System.out.print(leaf + " ");
		}
		System.out.println();
	}

}
