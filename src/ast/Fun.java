package ast;
import java.util.*;
public class Fun extends ASTList{

	public Fun(ArrayList<ASTree> list) {
		super(list);
		
	}
	
	public ParameterList parameters() {
		return (ParameterList)child(0);
	}
	
	public BlockStmnt body() {
		return (BlockStmnt)child(1);
	}
	
	
	public Object eval(Environment env) {
		return new Function(parameters(),body(),env);
	}
	
}
