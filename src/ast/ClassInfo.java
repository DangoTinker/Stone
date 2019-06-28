package ast;

import stone.StoneException;

public class ClassInfo {
	private ClassStmnt definition;
	private Environment env;
	private ClassInfo superClass;
	
	public ClassInfo(ClassStmnt d,Environment e) {
		definition=d;
		env=e;
		
		Object o=e.get(d.superClass());
		if(o==null) {
			return;
		}else if(o instanceof ClassInfo){
			superClass=(ClassInfo)o;
		}else{
			throw new StoneException("unknow super class:"+d.superClass(),d);
		}
		
	}
	public String name() {
		return definition.name();
	}
	public Environment environment() {
		return env;
	}
	public ClassInfo superClass()
	{
		return superClass;
	}
	public ClassBody body() {
		return definition.body();
	}
	
	
	
}
