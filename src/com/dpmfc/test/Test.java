package com.dpmfc.test;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;


public class Test extends ASTVisitor{
	
	private ArrayList<String> arrayList = new ArrayList<String>();

	@Override
	public boolean visit(MethodInvocation node) {
		// TODO Auto-generated method stub
		Expression ex = node.getExpression();
		System.out.println(ex.toString());
		
		return super.visit(node);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		J2AST j2ast = new J2AST();
		CompilationUnit compilationUnit = j2ast.getCompilationUnit("D:\\Su yuyi\\source2XMI\\source2XMI\\src\\util\\OutputUtil.java");
		
		Test test = new Test();
		compilationUnit.accept(test);
		
	}
}
