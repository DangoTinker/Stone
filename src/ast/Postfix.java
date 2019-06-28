package ast;
import java.util.*;
public abstract class Postfix extends ASTList{
	public Postfix(ArrayList<ASTree> c) {
		super(c);
	}
	public abstract Object eval(Environment env,Object value) ;
	
}
