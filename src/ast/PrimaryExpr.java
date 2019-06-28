package ast;
import java.util.*;

public class PrimaryExpr extends ASTList {
    public PrimaryExpr(ArrayList<ASTree> c) { super(c); }
    public ASTree operand() {
    	return child(0);
    }
    public static ASTree create(ArrayList<ASTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }
    
    public Postfix postfix(int nest) {
//    	System.out.println(((Name)child(numChildren()-nest-1)).name());
    	return (Postfix)child(numChildren()-nest-1);
    }
    public boolean hasPostfix(int nest) {
    	return (numChildren()-nest)>1;
    }
    /*
    public Object eval(Environment env) {
    	Object res=(operand()).eval(env);
    	int n=numChildren();
    	System.out.println("ASDASD"+n);
    	for(int i=n-1;i>=0;i--) {  //BUG HERE
    		
    		if(hasPostfix(i))
    			res=postfix(i).eval(env,res);
    	}
    	return res;
    }*/
    
    public Object eval(Environment env) {
    	return evalSubExpr(env,0);
    }
    
    public Object evalSubExpr(Environment env,int nest) {
    	
    	if(hasPostfix(nest)) {
    		
    		Object res=evalSubExpr(env,nest+1);
    		/*
    		if(postfix(nest) instanceof Dot) {
    			System.out.println(((Dot)postfix(nest)).name());
    		}
    		*/
    		System.out.println("nest:"+nest+" "+(postfix(nest).toString()));
    		return postfix(nest).eval(env,res);
    	}
    	else {
    		return operand().eval(env);
    	}
    }
    
    
    
    
}
