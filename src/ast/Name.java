package ast;

import stone.StoneException;

public class Name extends ASTLeaf {
    public Name(Token t) { super(t); }
    public String name() { return token().getText(); }
    
    public Object eval(Environment env) {
//    	System.out.println("zxc");
    	Object value=env.get(name());
    	if(value==null) {
    		throw new StoneException("undefined name:",this);
    	}
    	else {
    		return value;
    	}
    	
    }
    
    
}
