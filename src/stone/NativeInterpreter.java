package stone;

import ast.Natives;
import ast.NestedEnv;

public class NativeInterpreter extends BasicInterpreter{
	public static void main(String[] args) throws ParseException{
		run(new ClosureParser(),new Natives().environment(new NestedEnv()));
	}
}
