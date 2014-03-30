/**
 * @author SYY
 * @date 03-03-2014
 */
package com.dpmfc.detector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;

import com.dpmfc.test.JavaCode2AST;
import com.dpmfc.util.FileUtil;

public abstract class RelationshipDetector extends ASTVisitor {
	
	protected HashMap relationshipMap;
	
	public RelationshipDetector(String projectPath) throws IOException{
		super();
		init();
		
		FileUtil fileTool = new FileUtil();
		ArrayList<String> filePath = fileTool.getAllJavaFilePath(projectPath);
		
		for (String path: filePath) {
			CompilationUnit compUnit = JavaCode2AST.getCompilationUnit(path);
			compUnit.accept(this);
		}
		
	}
	
	public HashMap getAllRelationMap() {
		return relationshipMap;
	}
	
	public void setAllRelationMap(HashMap map) {
		relationshipMap = map;
	}
	
	protected abstract void init();
}
