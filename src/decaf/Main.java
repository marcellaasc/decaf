package decaf;

import java.io.*;
//import antlr.Token;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import java6035.tools.CLI.*;

class Main {
    public static void main(String[] args) {
        try {
        	CLI.parse (args, new String[0]);

        	InputStream inputStream = args.length == 0 ?
                    System.in : new java.io.FileInputStream(CLI.infile);

        	if (CLI.target == CLI.SCAN)
        	{
        		DecafLexer lexer = new DecafLexer(new ANTLRInputStream(inputStream));
        		Token token;
        		boolean done = false;
        		while (!done)
        		{
        			try
        			{
		        		for (token=lexer.nextToken(); token.getType()!=Token.EOF; token=lexer.nextToken())
		        		{
		        			String type = "";
		        			String text = token.getText();

		        			switch (token.getType())
		        			{
						case DecafLexer.IF:
		        				type = "";
		        				break;
						case DecafLexer.BOOLEAN:
		        				type = "";
		        				break;
						case DecafLexer.CALLOUT:
		        				type = "";
		        				break;
						case DecafLexer.CLASS:
		        				type = "";
		        				break;
						case DecafLexer.ELSE:
		        				type = "";
							break;
						case DecafLexer.INT:
		        				type = "";
		        				break;
						case DecafLexer.RETURN:
		        				type = "";
		        				break;
						case DecafLexer.VOID:
		        				type = "";
		        				break;
						case DecafLexer.FOR:
		        				type = "";
		        				break;
						case DecafLexer.BREAK:
		        				type = "";
		        				break;
						case DecafLexer.CONTINUE:
		        				type = "";
							break;
						case DecafLexer.MINUS:
		        				type = "";
							break;
						case DecafLexer.OP_ART:
		        				type = "";
		        				break;
						case DecafLexer.OP_REL:
		        				type = "";
		        				break;
						case DecafLexer.OP_COMP:
		        				type = "";
							break;
						case DecafLexer.OP_COND:
		        				type = "";
							break;
						case DecafLexer.BOOLEANLIT:
		        				type = " BOOLEANLITERAL";
		        				break;
						case DecafLexer.CHARLITERAL:
		        				type = " CHARLITERAL";
		        				break;
						case DecafLexer.STRING:
		        				type = " STRINGLITERAL";
		        				break;
						case DecafLexer.NUMBER:
		        				type = " INTLITERAL";
		        				break;
						case DecafLexer.HEXLIT:
		        				type = " HEXADECIMAL";
							break;
						case DecafLexer.ID:
		        				type = " IDENTIFIER";
		        				break;
						}
		        			System.out.println (token.getLine() + type + " " + text);
		        		}
		        		done = true;
        			} catch(Exception e) {
        	        	// print the error:
        	            System.out.println(CLI.infile+" "+e);
        	            lexer.skip();
        	        }
        		}
        	}
        	else if (CLI.target == CLI.PARSE || CLI.target == CLI.DEFAULT)
        	{
        	    // Primeiro faz o parsing da cadeia
                DecafLexer lexer = new DecafLexer(new ANTLRInputStream(inputStream));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                DecafParser parser = new DecafParser(tokens);

                // Adiciona as regras semÃ¢nticas
                ParseTree tree = parser.program();

                if (CLI.debug) {
                    // Se estiver no modo debug imprime a Ã¡rvore de parsing
                    // Create Tree View
                    // Source: https://stackoverflow.com/questions/23809005/how-to-display-antlr-tree-gui


                    //show AST in console
                    System.out.println(tree.toStringTree(parser));

                    //show AST in GUI
                    JFrame frame = new JFrame("Antlr AST");
                    JPanel panel = new JPanel();
                    TreeViewer viewr = new TreeViewer(Arrays.asList(
                            parser.getRuleNames()),tree);
                    viewr.setScale(1.5);//scale a little
                    panel.add(viewr);
                    frame.add(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(200,200);
                    frame.setVisible(true);
                }

            }
        	
        } catch(Exception e) {
        	// print the error:
            System.out.println(CLI.infile+" "+e);
        }
    }
}

