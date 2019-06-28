package ast;
import java.util.*;
public class DefStmnt extends ASTList{

	public DefStmnt(ArrayList<ASTree> temp) {
		super(temp);
		// TODO 自动生成的构造函数存根
	}
	
	 public String name() { return ((ASTLeaf)child(0)).token().getText(); }
	    public ParameterList parameters() { return (ParameterList)child(1); }
	    public BlockStmnt body() { return (BlockStmnt)child(2); } 
	    public String toString() {
	        return "(def " + name() + " " + parameters() + " " + body() + ")";
	    } 
	
	public Object eval(Environment env) {
		env.putNew(name(), new Function(parameters(),body(),env));
		return name();
	}
	
	
}
