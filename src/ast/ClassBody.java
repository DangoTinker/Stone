package ast;
import java.util.*;
public class ClassBody extends ASTList {

	public ClassBody(ArrayList<ASTree> list) {
		super(list);
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public Object eval(Environment env) {
		for(ASTree t:this) {
			t.eval(env);
		}
		return null;
	}
	
}
