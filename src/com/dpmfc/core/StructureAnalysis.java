package com.dpmfc.core;

import java.util.HashMap;

import com.dpmfc.bean.ProjectInfo;
import com.dpmfc.detector.RelationshipDetector;

public abstract class StructureAnalysis {
	
	protected HashMap<String, RelationshipDetector> allRelationMap;
	
	protected StructureAnalysis structureAnalysis;
	
	public void init(HashMap map) {
		allRelationMap = map;
	}
	
	public void setStructureAnalysis(StructureAnalysis temp) {
		structureAnalysis = temp;
	}
	
	public abstract void doStructureAnalyze(ProjectInfo projectInfo);
}
