package ast;
import java.util.*;

public class WhileStmnt extends ASTList {
    public WhileStmnt(ArrayList<ASTree> c) { super(c); }
    public ASTree condition() { return child(0); }
    public ASTree body() { return child(1); }
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
    
    public Object eval(Environment env) {
    	Object res=0;
    	while(true) {
    		Object c=condition().eval(env);
    		if(c instanceof Integer && ((Integer)c).intValue()==0) {
    			return res;
    		}
    		else {
    			res=body().eval(env);
    		}
    	}
    }
    
}
