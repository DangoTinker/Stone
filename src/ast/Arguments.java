package ast;
import java.util.*;

import stone.StoneException;
public class Arguments extends Postfix{
	public Arguments(ArrayList<ASTree> c) {
		super(c);
	}
	public int size() { return numChildren(); }
	public Object eval(Environment callerEnv,Object value) {
//		System.out.println("qwe");
		if((value instanceof Function)) {
			if(!(value instanceof Function))
				throw new StoneException("bad funciton",this);
			
			if(size()!=((Function)value).parameters().size()) {
				throw new StoneException("bad number of arguments",this);
			}
			int i=0;
//			System.out.println("asd");
			Environment newEnv=((Function)value).makeEnv();
			for(ASTree t:this) {
				(((Function)value).parameters()).eval(newEnv,i++,t.eval(callerEnv));	
			}
			return (((Function)value).body()).eval(newEnv);
		}
		else if(value instanceof NativeFunction) {
			NativeFunction func=(NativeFunction)value;
			Object[] args=new Object[func.numOfParameters()];
			int i=0;
			for(ASTree t:this) {
				args[i]=t.eval(callerEnv);
			}
			try {
				return func.invoke(args);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return null;
	
	}
	
}
