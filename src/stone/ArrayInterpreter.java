package stone;

import ast.Natives;
import ast.NestedEnv;

public class ArrayInterpreter extends ClassInterpreter{
	public static void main(String[] args) throws ParseException {
        run(new ArrayParser(), new Natives().environment(new NestedEnv())); 
    }
}
