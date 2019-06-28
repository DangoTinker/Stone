package ast;

import java.rmi.AccessException;
import java.util.ArrayList;

import stone.StoneException;

public class Dot extends Postfix{

	public Dot(ArrayList<ASTree> c) {
		super(c);
		// TODO 自动生成的构造函数存根
	}
	public String name() {
		return ((ASTLeaf)child(0)).token().getText();
	}
	@Override
	public Object eval(Environment env, Object value) {
		String member=name();
//		System.out.println("member:"+member+" "+(member instanceof Function));
		
		
		if(value instanceof ClassInfo) {
//			System.out.println("member:"+member);
			if(member.equals("new")) {
				ClassInfo ci=(ClassInfo)value;
				NestedEnv e=new NestedEnv(ci.environment());
				StoneObject so=new StoneObject(e);
				e.putNew("this", so);
				initObject(ci,e);
				return so;
			}
		}
		else if(value instanceof StoneObject) {
//			System.out.println("member:"+member);
			try {
				
				
				
				return ((StoneObject) value).read(member);
			}catch(AccessException e) {
				System.out.println(e.getMessage());
			}
		}
		throw new StoneException("bad member access:"+member,this);
		
	}
	
	private void initObject(ClassInfo ci,Environment env) {
		if(ci.superClass()!=null) {
			initObject(ci.superClass(),env);
		}
		ci.body().eval(env);
	}

}
