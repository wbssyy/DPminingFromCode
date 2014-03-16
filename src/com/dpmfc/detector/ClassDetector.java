package com.dpmfc.detector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.dpmfc.test.JavaCode2AST;
import com.dpmfc.util.FileUtil;

public class ClassDetector extends ASTVisitor{
	
	private ArrayList<String> allClassList = new ArrayList<>();
	
	public ClassDetector(String projectPath) throws IOException{
		super();
		
		FileUtil fileTool = new FileUtil();
		ArrayList<String> filePath = fileTool.getAllJavaFilePath(projectPath);
		
		for (String path: filePath) {
			CompilationUnit compUnit = JavaCode2AST.getCompilationUnit(path);
			compUnit.accept(this);
		}
		
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		//
		String className = node.getName().toString();
		allClassList.add(className);
		
		return super.visit(node);
	}
	
	public ArrayList<String> getAllClassList() {
		return allClassList;
	}
	
	public HashMap removeJDKClass(HashMap map) {
		
		Iterator iterator = map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			
			Map.Entry entry = (Map.Entry)iterator.next();
			
			HashSet<String> hashSet = (HashSet<String>)entry.getValue();
			Iterator<String> hashsetIterator = hashSet.iterator();
			
			HashSet<String> classSet = new HashSet<String>();
			
			while (hashsetIterator.hasNext()) {
//				System.out.println(hashsetIterator.next());
				String classNmae = hashsetIterator.next();
				
				if (allClassList.contains(classNmae)) {
					classSet.add(classNmae);
				}
			}
			
			map.put(entry.getKey(), classSet);
		}
		
		return map;
	}
	
}
