package ast;

public interface Environment {
	public void put(String name,Object o) ;
	public Object get(String name);
	void putNew(String name,Object value);
	void setOuter(Environment e);
	Environment where(String name);
	
}
