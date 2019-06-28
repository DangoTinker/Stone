package ast;
import java.util.*;
public class ASTLeaf extends ASTree{
	/*
	public static ArrayList<ASTree> empty=new ArrayList<ASTree>();
	protected Token token;
	public ASTLeaf(Token e) {
		token=e;
	}
	@Override
	public Iterator<ASTree> iterator() {
		// TODO �Զ����ɵķ������
		return empty.iterator();
	}

	@Override
	public ASTree child(int i) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public int numChildren() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public String location() {
		// TODO �Զ����ɵķ������
		return null;
	}
	public Token token() {
		return token;
	}
	@Override
	public Object eval(Environment env) {
		// TODO �Զ����ɵķ������
		return null;
	}
	*/
	public Object eval(Environment env) {
		// TODO �Զ����ɵķ������
		return null;
	}
	private static ArrayList<ASTree> empty = new ArrayList<ASTree>(); 
    protected Token token;
    public ASTLeaf(Token t) { token = t; }
    public ASTree child(int i) { throw new IndexOutOfBoundsException(); }
    public int numChildren() { return 0; }
    public Iterator<ASTree> children() { return empty.iterator(); }
    public String toString() { return token.getText(); }
    public String location() { return "at line " + token.getLineNumber(); }
    public Token token() { return token; }
	
}
