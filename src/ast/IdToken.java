package ast;

public class IdToken extends Token{
	private String text;
	
	public IdToken(int line,String t) {
		super(line);
		text=t;
	}
	public boolean isIdentifier() { return true; }

    public String getText() { return text; }
}
