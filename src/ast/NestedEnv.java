package ast;
import java.util.*;
public class NestedEnv implements Environment{

	private HashMap<String,Object> values;
	private Environment outer;
	
	public NestedEnv() {
		values=new HashMap<String,Object>();
	}
	public NestedEnv(Environment o) {
		values=new HashMap<String,Object>();
		outer=o;
	}
	
	
	
	@Override
	public void put(String name, Object o) {
		// TODO 自动生成的方法存根
		Environment e=where(name);
		if(e==null)
			e=this;
		
		e.putNew(name, o);
		
	}

	@Override
	public Object get(String name) {
		// TODO 自动生成的方法存根
		Object v=values.get(name);
		
		if(v==null && outer!=null) {
			return outer.get(name);
					
		}
			return v;
	}

	public void putNew(String name,Object o) {
		values.put(name, o);
		
	}
	
	public Environment where(String name) {
		if(values.get(name)!=null) {
			return this;
		}
		else if(outer==null){
			return null;
		}
		else {
			return outer.where(name);
		}
	}
	
	public void setOuter(Environment e) {
		outer=e;
	}
	
}
