package com.dpmfc.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dpmfc.bean.ProjectInfo;
import com.dpmfc.bean.Weight;
import com.dpmfc.detector.RelationshipDetector;
import com.dpmfc.util.OutputUtil;

public class WeightCalculator {

	private HashMap<String, Integer> weightMap = new HashMap<String, Integer>();
	private StringBuilder result = new StringBuilder();
	
	public void calculateWeight(ProjectInfo projectInfo) {
		HashMap<String, HashSet<String>> dependencyMap = projectInfo.getDependencyMap();
		Iterator iterator = dependencyMap.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String className = entry.getKey().toString();
			HashSet<String> set = (HashSet<String>)entry.getValue();
			
			for (String destination : set) {
				// about source class
				if(weightMap.containsKey(className)) {
					if (weightMap.get(className) % (Weight.DEPENDENCY_A*Weight.DEPENDENCY_A) != 0) {
						Integer num = weightMap.get(className);
						num *= Weight.DEPENDENCY_A;
						weightMap.put(className, num);
						result.append("dependency++++++++++++++++     "+num +'\n');
					}
				} else {
					Integer temp = Weight.DEPENDENCY_A;
					weightMap.put(className, temp);
					result.append("dependency       else++++++++++++++++     "+temp +'\n');
				}
				
				System.out.println("2.dependency: " + className + "------>" + destination);
				
				// about destination class
				if (weightMap.containsKey(destination)) {
					if (weightMap.get(destination) % (Weight.DEPENDENCY_B*Weight.DEPENDENCY_B) != 0) {
						Integer num = weightMap.get(destination);
						num *= Weight.DEPENDENCY_B;
						weightMap.put(destination, num);
					}
				} else {
					Integer temp = Weight.DEPENDENCY_B;
					weightMap.put(destination, temp);
				}
			}
		}
		
		HashMap<String, HashSet<String>> inheritanceMap = projectInfo.getInheritanceMap();
		iterator = inheritanceMap.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String className = entry.getKey().toString();
			HashSet<String> set = (HashSet<String>)entry.getValue();
			
			for (String destination : set) {
				// about source class
				if(weightMap.containsKey(className)) {
					if (weightMap.get(className) % (Weight.INHERITANCE_A*Weight.INHERITANCE_A) != 0) {
						Integer num = weightMap.get(className);
						num *= Weight.INHERITANCE_A;
						weightMap.put(className, num);
					}
				} else {
					Integer temp = Weight.INHERITANCE_A;
					weightMap.put(className, temp);
				}
				
				System.out.println("5.inheritance: " + className + "------>" + destination);
				
				// about destination class
				if (weightMap.containsKey(destination)) {
					if (weightMap.get(destination) % (Weight.INHERITANCE_B*Weight.INHERITANCE_B) != 0) {
						Integer num = weightMap.get(destination);
						num *= Weight.INHERITANCE_B;
						weightMap.put(destination, num);
					}
				} else {
					Integer temp = Weight.INHERITANCE_B;
					weightMap.put(destination, temp);
				}
			}
		}
		
		HashMap<String, HashSet<String>> associationMap = projectInfo.getAssciationMap();
		iterator = associationMap.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String className = entry.getKey().toString();
			HashSet<String> set = (HashSet<String>)entry.getValue();
			
			for (String destination : set) {
				// about source class
				if(weightMap.containsKey(className)) {
					if (weightMap.get(className) % (Weight.ASSOCIATION_A*Weight.ASSOCIATION_A) != 0) {
						Integer num = weightMap.get(className);
						num *= Weight.ASSOCIATION_A;
						weightMap.put(className, num);
					}
				} else {
					Integer temp = Weight.ASSOCIATION_A;
					weightMap.put(className, temp);
				}
				
				System.out.println("11.association: " + className + "------>" + destination);
				
				// about destination class
				if (weightMap.containsKey(destination)) {
					if (weightMap.get(destination) % (Weight.ASSOCIATION_B*Weight.ASSOCIATION_B) != 0) {
						Integer num = weightMap.get(destination);
						num *= Weight.ASSOCIATION_B;
						weightMap.put(destination, num);
					}
				} else {
					Integer temp = Weight.ASSOCIATION_B;
					weightMap.put(destination, temp);
				}
			}
		}
		
		try {
			OutputUtil.outputToTXT(result.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printWeightMap(){
		
		Iterator iterator = weightMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			String classNameString = entry.getKey().toString();
			String weightString = entry.getValue().toString();
			
			System.out.println(classNameString + ": " + weightString);
		}
		
	}
	
	public HashMap getWeightMap() {
		return weightMap;
	}
	
}
