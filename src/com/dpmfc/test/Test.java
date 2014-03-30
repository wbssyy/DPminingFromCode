package com.dpmfc.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;


public class Test {
	
	private ArrayList<String> arrayList = new ArrayList<String>();


	public static void main(String[] args) throws Exception {

		
		HashSet<String> set = new HashSet<>();
		set.add("123");
		set.add("456");
		System.out.println(set.contains("123"));
		String tString = "123";
		set.remove(tString);
		
		System.out.println(set.contains(tString));
		
		Test test = new Test();
		
//		File file = new File("D:\\JOSS-1\\JOSS-1");
//		File[] filelist = file.listFiles(); 
//		for (File tempFile : filelist) {
//			System.out.println(tempFile.getName());
//		}
		
		System.out.println(100%80);
	}
}
