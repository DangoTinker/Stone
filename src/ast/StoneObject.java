package ast;

import java.rmi.AccessException;

public class StoneObject {
	private String name;
	private Environment env;
	
	public StoneObject(Environment e) {
		env=e;
	}
	
	public Object read(String member) throws AccessException{
//		System.out.println(member+" "+(getEnv(member).get(member) instanceof Function));
		
		return getEnv(member).get(member);
		
	}
	
	public void write(String member,Object value) throws AccessException{
		getEnv(member).putNew(member, value);
	}
	
	private Environment getEnv(String member) throws AccessException{
		Environment e=env.where(member);
		if(e!=null && e==env) {
			return e;
		}
		else {
			throw new AccessException("´íÔÚStoneObject");
		}
	}
	
}
