package ast;

import java.awt.List;
import java.util.ArrayList;

public class ArrayLiteral extends ASTList{
	public ArrayLiteral (ArrayList<ASTree> list) {
		super(list);
	}
	public int size() {
		return numChildren();
	}
	public Object eval(Environment env) {
		int n=numChildren();
		Object[] res=new Object[n];
		int i=0;
		for(ASTree t:this) {
			res[i++]=t.eval(env);
		}
		return res;
	}
	
}
