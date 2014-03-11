/**
 * @author SYY
 * @date 03-03-2014
 */
package com.dpmfc.detector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class InheritanceInfoDetector extends RelationshipDetector{
	
	/**
	 * key:subclass name
	 * value:super class or interface Type name
	 */
	private HashMap<String, ArrayList<String>> inheritInfo;
	
	/**
	 * @return the inheritance information
	 */
	public HashMap<String, ArrayList<String>> getInheritInfo() {
		return inheritInfo;
	}
	
	
	public InheritanceInfoDetector(String projectPath) throws IOException {
		super(projectPath);
	}
	
	@Override
	protected void init() {
		inheritInfo = new HashMap<String, ArrayList<String>>();
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		//if it has super class
		String subClass = node.getName().toString();
		ArrayList<String> sList = new ArrayList<String>();
		
		if (node.getSuperclassType() != null) {
			sList.add(
					getSimpleName(
							node.getSuperclassType()));
		}
		//if it has interface
		for (Object i: node.superInterfaceTypes()) {
			sList.add(
					getSimpleName(
							((Type)i)));
		}
		
		if (sList.size() > 0) {
			inheritInfo.put(subClass, sList);
		}
		
		System.out.println(subClass);
		for (String string : sList) {
			System.out.println("   " + string);
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


	@Override
	public HashMap getAllRelationMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
