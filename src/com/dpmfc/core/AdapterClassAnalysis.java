package com.dpmfc.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.dpmfc.bean.ProjectInfo;
import com.dpmfc.bean.Weight;

public class AdapterClassAnalysis extends StructureAnalysis {
	
	//weight of each role of the pattern
	private int adapterW = Weight.INHERITANCE_A * Weight.INHERITANCE_A;	//25;
	private int targetW  = Weight.INHERITANCE_B;	//7;
	private int adapteeW = Weight.INHERITANCE_B;	//7;
	private static int number = 0;
	
	@Override
	public void doStructureAnalyze(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub
		HashMap inheritanceMap = projectInfo.getInheritanceMap();
		
		List<String> adapterList = projectInfo.containWeight(adapterW);
		for (String adapter : adapterList) {
			HashSet<String> set = (HashSet)inheritanceMap.get(adapter);
			printResult(adapter, set);
		}
	}

	private void printResult(String adapter, HashSet<String> set) {
		number++;
		System.out.println(number + "adapter: " + adapter);
		System.out.print("adaptee/target: ");
		for (String string : set) {
			System.out.println(string + " ");
		}
		System.out.println();
	}
}
