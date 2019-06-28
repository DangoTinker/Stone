package ast;

import java.util.ArrayList;

import stone.StoneException;

public class ArrayRef extends Postfix{
	public ArrayRef(ArrayList<ASTree> c) {
		super(c);
	}
	public ASTree index() {
		return child(0);
	}
	public String toString() {
		return "[" +index()+"]";
	}
	
	public Object eval(Environment env, Object value) {
		// TODO 自动生成的方法存根
		if(value instanceof Object[]) {
			Object index=index().eval(env);
			if(index instanceof Integer) {
				return ((Object[])value)[(Integer)index];
			}
			
		}
		throw new StoneException("bad array access",this);
	}
}
