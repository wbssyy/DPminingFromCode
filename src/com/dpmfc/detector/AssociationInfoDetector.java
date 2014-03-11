/**
 * @author SYY
 * @date 03-03-2014
 */
package com.dpmfc.detector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.dpmfc.test.JavaCode2AST;

public class AssociationInfoDetector extends RelationshipDetector{
	
	/**
	 * key:class name
	 * value:associated class name list
	 */
	private HashMap<String, HashSet<String>> acsMap;
	private String className;
	private HashSet<String> acsSet;
	
	/**
	 * @return the association information
	 */
	public HashMap<String, HashSet<String>> getAcsMap() {
		return acsMap;
	}

	public AssociationInfoDetector(String projectPath) throws IOException{
		super(projectPath);
	}
	
	@Override
	protected void init() {
		acsMap = new HashMap<String, HashSet<String>>();
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		className = node.getName().toString();
		acsSet = new HashSet<String>();
		
		for (Object dec : node.bodyDeclarations()) {
			((ASTNode)dec).accept(new FieldVisitor());
		}
		
		//if do associate with others
		if (acsSet.size() > 0) {
			acsMap.put(className, acsSet);
		}
		
		System.out.println(className + ":");
		Iterator<String> iterator = acsSet.iterator();
		while (iterator.hasNext()) {
			System.out.println("  "+iterator.next());
		}
		
		return true;
	}
	
	
	/**
	 * TODO visit every field in the class
	 *
	 */
	class FieldVisitor extends ASTVisitor {
		
		private String typeName;
		
		//pass internal class
		@Override
		public boolean visit(TypeDeclaration node) {
			return false;
		}

		@Override
		public boolean visit(FieldDeclaration node) {
			//if it associated with other
			typeName= "";
					
			getTypeName(node.getType());
			
			if (!typeName.equals("")) {
				acsSet.add(typeName);
			}
			
			return super.visit(node);
		}
		
		private void getTypeName(Type node) {
			//if it's a array , get the component type and continue judgment
			if (node.isArrayType()) {
				ArrayType type = (ArrayType) node;
				getTypeName(type.getComponentType());
			}
			//if it's parameterized, get the argument type and continue judgment
			else if (node.isParameterizedType()) {
				ParameterizedType type = (ParameterizedType) node;
				for (Object o: type.typeArguments()) {
					Type t = (Type)o;
					getTypeName(t);
				}
			}
			//if it's a simple type
			else if (node.isSimpleType()) {
				typeName = node.toString();
			}
		}
	}

}
