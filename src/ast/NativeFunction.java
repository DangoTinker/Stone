package ast;
import java.util.*;
import java.lang.reflect.*;
public class NativeFunction {
	private Method method;
	private String name;
	private int numParams;
	
	public NativeFunction(Method m,String n) {
		method=m;
		name=n;
		numParams=m.getParameterTypes().length;
	}
	
	public Object invoke(Object[] args) throws Exception{
		return method.invoke(null, args);
	}
	public int numOfParameters() {
		return numParams;
	}
	
}
