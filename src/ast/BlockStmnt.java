package ast;

import java.util.*;

public class BlockStmnt extends ASTList {
    public BlockStmnt(ArrayList<ASTree> c) { super(c); }
    
    public Object eval(Environment env) {
    	Object res=0;
    	Iterator<ASTree> t=this.iterator();
		while(t.hasNext()) {
    		if(!(t instanceof NullStmnt)) {
    			res=(t.next()).eval(env);   //?????????
    		}
    	}
		return res;
    }
    
}
