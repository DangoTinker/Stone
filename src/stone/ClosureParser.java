package stone;
import static stone.Parser.rule;

import java.util.*;

import ast.Fun;
public class ClosureParser extends FuncParser{
	public ClosureParser() {
		primary.insertChoice(rule(Fun.class).sep("fun").ast(paramList).ast(block));
		
	}
}
