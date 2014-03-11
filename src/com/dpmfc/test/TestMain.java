package com.dpmfc.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.dpmfc.detector.AssociationInfoDetector;
import com.dpmfc.detector.DependencyInfoDetector;
import com.dpmfc.detector.RelationshipDetector;
import com.dpmfc.detector.InheritanceInfoDetector;

public class TestMain {
	/**
	 * @param args
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String projectPath = "D:\\Su yuyi\\百度云同步盘\\工作\\WorkSpace 2013-12\\source2XMI\\src";
		long t = System.currentTimeMillis();
		
		RelationshipDetector dependDetector, inheriDetector, associDetector;
		dependDetector = new DependencyInfoDetector(projectPath);
		inheriDetector = new InheritanceInfoDetector(projectPath);
		associDetector = new AssociationInfoDetector(projectPath);
		
		HashMap map = associDetector.getAllRelationMap();
		
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			System.out.println("-------------------------"+entry.getKey());
			HashSet<String> hashSet = (HashSet<String>)entry.getValue();
			Iterator<String> hashsetIterator = hashSet.iterator();
			while (hashsetIterator.hasNext()) {
				System.out.println(hashsetIterator.next());
			}
		}
		
		t = System.currentTimeMillis()-t;
		System.out.println(t);
	}

}
