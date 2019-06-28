package ast;

import java.util.ArrayList;

public class ClassStmnt extends ASTList{
	

	public ClassStmnt(ArrayList<ASTree> list) {
		super(list);
		// TODO 自动生成的构造函数存根
	}
	
	public String name() {
		return ((ASTLeaf)child(0)).token().getText();
	}
	public ClassBody body() {
		return ((ClassBody)child(numChildren()-1));
	}
	
	public String superClass() {
		if(numChildren()<3) {
			return null;
		}else {
			return ((ASTLeaf)child(1)).token().getText();
		}
	}
	
	
	public Object eval(Environment env) {
		ClassInfo ci=new ClassInfo(this,env);
		env.put(name(), ci);
		return name();
		
	}
	
	
}
