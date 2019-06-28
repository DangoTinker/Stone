package stone;

import java.io.*;
import java .util.ArrayList;
import java.util.regex.*;

import ast.IdToken;
import ast.NumToken;
import ast.StrToken;
import ast.Token;

public class Lexer {
	public static String regexPat="\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"

          + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
	
	public Pattern pattern=Pattern.compile(regexPat);
	public ArrayList<Token> queue=new ArrayList<Token>();
	public boolean hasMore=true;
	public LineNumberReader reader;
	
	public Lexer(Reader r) {
		reader=new LineNumberReader(r);
	}
	
	protected String toStringLiteral(String s) {

        StringBuilder sb = new StringBuilder();

        int len = s.length() - 1;

        for (int i = 1; i < len; i++) {

            char c = s.charAt(i);

            if (c == '\\' && i + 1 < len) {

                int c2 = s.charAt(i + 1);

                if (c2 == '"' || c2 == '\\')

                    c = s.charAt(++i);

                else if (c2 == 'n') {

                    ++i;

                    c = '\n';

                }

            }

            sb.append(c);

        }

        return sb.toString();

    }
	
	public void addToken(int lineNo,Matcher matcher) {
		String m=matcher.group(1);
		if(m!=null) {
			if(matcher.group(2)==null) {
				Token token;
				if(matcher.group(3)!=null) {
					token=new NumToken(lineNo,Integer.parseInt(m));
				}
				else if(matcher.group(4)!=null) {
					token=new StrToken(lineNo,toStringLiteral(m));
				}
				else {
					token=new IdToken(lineNo,m);
				}
				queue.add(token);
			}
		}
		
	}
	
	
	public void readLine() throws ParseException {
		String line;
		try {
			line=reader.readLine();
		}catch(IOException e) {
			throw new ParseException(e);
		}
		if(line==null) {
			hasMore=false;
			return;
		}
		
		int lineNo=reader.getLineNumber();
		Matcher matcher=pattern.matcher(line);
		matcher.useTransparentBounds(true).useAnchoringBounds(false);
		int pos=0;
		int endPos=line.length();
		while(pos<endPos) {
			matcher.region(pos, endPos);
			if(matcher.lookingAt()) {
				addToken(lineNo,matcher);
				pos=matcher.end();
			}
			else
				throw new ParseException("bad token at line"+lineNo);
			
		}
		
		queue.add(new IdToken(lineNo,Token.EOL));
		
	}
	
	public boolean fillQueue(int i) throws ParseException{
		while(i>=queue.size()) {
			if(hasMore) {
				readLine();
			}
			else {
				return false;
			}
			
		}
		return true;
	}
	
	
	public Token read() throws ParseException{
		if(fillQueue(0)) {
			return queue.remove(0);
		}
		else {
			return Token.EOF;
		}
	}
	
	public Token peek(int i) throws ParseException{
		
		if(fillQueue(i)) {
			return queue.get(i);
		}
		else {
			return Token.EOF;
		}
		
	}
	
	
	
	
	
	
	
	
}
