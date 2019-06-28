package ast;
import java.rmi.AccessException;
import java.util.*;

import stone.StoneException;

public class BinaryExpr extends ASTList {
    public BinaryExpr(ArrayList<ASTree> c) { super(c); }
    public ASTree left() { return child(0); }
    public String operator() {
        return ((ASTLeaf)child(1)).token().getText();
    }
    public ASTree right() { return child(2); }
    public Object eval(Environment env) {
    	String op=operator();
    	if("=".equals(op)) {
    		Object right=right().eval(env);
    		return computeAssign(env,right);
    	}
    	else {
    		Object left=left().eval(env);
    		Object right=right().eval(env);
    		
    		return computeOp(left,op,right);
    	}
    }
    
    public Object computeAssign(Environment env,Object rvalue) {
    	ASTree left=left();
    	if(left instanceof Name) {
    		env.put(((Name)left).name(), rvalue);
    		return rvalue;
    	}
    	else if(left instanceof PrimaryExpr) {
    		PrimaryExpr p=(PrimaryExpr)left;
    		if(p.hasPostfix(0)&&p.postfix(0) instanceof Dot) {
    			Object t=((PrimaryExpr)left).evalSubExpr(env, 1);
    			if(t instanceof StoneObject) {
    				return setField((StoneObject)t,(Dot)p.postfix(0),rvalue);
    			}
    		}
    		if(p.hasPostfix(0)&&p.postfix(0) instanceof ArrayRef) {
    			Object a=((PrimaryExpr) left).postfix(0);
    			if(a instanceof Object[] ){
    				ArrayRef aref=(ArrayRef)p.postfix(0);
    				Object index=(aref.index()).eval(env);
    				if(index instanceof Integer) {
    					((Object[])a)[(Integer)index]=rvalue;
    					return rvalue;
    				}
    				
    			}
    			throw new StoneException("bad array access",this);
    		}
    	}
    	else {
    		throw new StoneException("bad assignment",this);
    		
    	}
    	return null;
    	
    }
    
    
    private Object setField(StoneObject obj,Dot expr,Object rvalue) {
    	String name=expr.name();
    	try {
    		obj.write(name, rvalue);
    		return rvalue;
    	}catch(AccessException e) {
    		throw new StoneException("bad member access"+location()+":"+name);
    	}
    }
    
    
    public Object computeOp(Object left,String op,Object right) {
    	
    	
    	if(left instanceof Integer && right instanceof Integer) {		
    		return computeNumber((Integer)left,op,(Integer)right);    		
    	}
    	else {
    		return computeString((String)left,op,(String)right);
    	}
    	
    	
    }
    
    private Object computeString(String left,String op,String right) {
    	if(op.equals("+")) {
    		return left+right;
    	}
    	else if(op.equals("==")) {
    		if(left==null) {
    			return right.equals(null)?1:0;
    		}
    		else {
    			return left.equals(right)?1:0;
    		}
    	}
    	else {
    		throw new StoneException("bad type",this);
    	}
    }
    
    private Object computeNumber(Integer left,String op,Integer right) {
    	
    	int a=left.intValue();
    	int b=right.intValue();
    	if(op.equals("+")) {
//    		System.out.println(a+" "+b);
    		return a+b;
    	}
    	else if(op.equals("-")) {
    		return a-b;
    	}
    	else if(op.equals("*")) {
    		return a*b;
    	}
    	else if(op.equals("/")) {
    		return a/b;
    	}
    	else if(op.equals("%")) {
    		return a%b;
    	}
    	else if(op.equals("==")) {
    		return a==b?1:0;
    	}
    	else if(op.equals(">")) {
    		return a>b?1:0;
    	}
    	else if(op.equals("<")) {
//    		System.out.println(a+" "+b);
//    		if(a<b) {
//    			System.out.println("yes");
//    		}
    		return a<b?1:0;
    	}
    	else {
    		throw new StoneException("bad operator",this);
    	}
    	
    	
    	
    }
    
    
}
