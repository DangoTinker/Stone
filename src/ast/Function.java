package ast;
import java.util.*;
public class Function {
	private ParameterList parameters;
	private BlockStmnt body;
	private Environment env;
	
	public Function(ParameterList p,BlockStmnt b,Environment e) {
		parameters=p;
		body=b;
		env=e;
	}
	
	public ParameterList parameters() {
		return parameters;
	}
	
	public BlockStmnt body() {
		return body;
	}
	public Environment makeEnv() {
		return new NestedEnv(env);
	}
	public String toString() {
		return "<fun:"+hashCode()+">";
	}
	
	
}
