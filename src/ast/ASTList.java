package ast;
import java.util.*;

public class ASTList extends ASTree {
	/*
	public ArrayList<ASTree> children;
	public ASTList (ArrayList<ASTree> temp) {
		children=temp;
	}
	

	@Override
	public int numChildren() {
		// TODO 自动生成的方法存根
		return children.size();
	}

	public Iterator<ASTree> iterator(){
		return children.iterator();
	}

	public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append('(');

        String sep = "";

        for (ASTree t: children) {

            builder.append(sep);

            sep = " ";

            builder.append(t.toString());

        }

        return builder.append(')').toString();

    }

    public String location() {

        for (ASTree t: children) {

            String s = t.location();

            if (s != null)

                return s;

        }

        return null;

    }
	@Override
	public ASTree child(int i) {
		// TODO 自动生成的方法存根
		
		return children.get(i);
	}


	@Override
	public Object eval(Environment env) {
		// TODO 自动生成的方法存根
		return null;
	}
	*/
	protected ArrayList<ASTree> children;
    public ASTList(ArrayList<ASTree> list) { children = list; }
    public ASTree child(int i) { return children.get(i); }
    public int numChildren() { return children.size(); }
    public Iterator<ASTree> children() { return children.iterator(); }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        String sep = "";
        for (ASTree t: children) {
            builder.append(sep);
            sep = " ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }
    public String location() {
        for (ASTree t: children) {
            String s = t.location();
            if (s != null)
                return s;
        }
        return null;
    }
    @Override
	public Object eval(Environment env) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
