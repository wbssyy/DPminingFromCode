/**
 * @author SYY
 * @date 03-03-2014
 */
package com.dpmfc.detector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class InheritanceInfoDetector extends RelationshipDetector{
	
	/**
	 * key:subclass name
	 * value:super class or interface Type name
	 */
	private HashSet<String> inheriSet;
	
	/**
	 * @return the inheritance information
	 */
	public HashMap<String, ArrayList<String>> getInheritInfo() {
		return allRelationMap;
	}
	
	
	public InheritanceInfoDetector(String projectPath) throws IOException {
		super(projectPath);
	}
	
	@Override
	protected void init() {
		allRelationMap = new HashMap<String, HashSet<String>>();
	}
	
	@Override
	public HashMap getAllRelationMap() {
		return allRelationMap;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		
		String subClass = node.getName().toString();
		inheriSet = new HashSet<String>();
		
		//if it has super class
		if (node.getSuperclassType() != null) {
			inheriSet.add(
					getSimpleName(
							node.getSuperclassType()));
		}
		
		//if it has interface
		for (Object i: node.superInterfaceTypes()) {
			inheriSet.add(
					getSimpleName(
							((Type)i)));
		}
		
		if (inheriSet.size() > 0) {
			allRelationMap.put(subClass, inheriSet);
		}
		
		return true;
	}
	
	private String getSimpleName(Type node) {
		String superClass = node.toString();
		
		//Type<?>
		if (node.isParameterizedType()) {
			ParameterizedType type = (ParameterizedType) node;
			superClass = getSimpleName(type.getType());
		}
		
		return superClass;
	}
}
