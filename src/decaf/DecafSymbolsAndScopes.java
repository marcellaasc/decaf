package decaf;
import java.util.ArrayList;
import java.util.List;
import org.antlr.symtab.FunctionSymbol;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.LocalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.VariableSymbol;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import decaf.ExceptionList.MainNaoEncontradoException;
import decaf.ExceptionList.VariavelNaoInstanciadaException;
import decaf.ExceptionList.TamanhoNaoValidoException;
import decaf.ExceptionList.NumeroDeArgumentosMetodoInvalidoException;
import decaf.DecafParser.Assing_opContext;
import decaf.DecafParser.ExprContext;
import decaf.DecafParser.Field_declContext;
import decaf.DecafParser.LocationContext;
import decaf.DecafParser.Method_callContext;
import decaf.DecafParser.Method_declContext;
import decaf.DecafParser.StatementContext;
import decaf.DecafParser.Var_declContext;

/**
 * 
 * This class defines basic symbols and scopes for Decaf language
 * 
 */

public class DecafSymbolsAndScopes extends DecafParserBaseListener {

	ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();

	GlobalScope globals;

	Scope currentScope; // define symbols in this scope
	
	List<Method_declContext> listaMetodos = new ArrayList<DecafParser.Method_declContext>();

	
	
	@Override

	public void enterProgram(DecafParser.ProgramContext ctx) {
		
		globals = new GlobalScope(null);
		pushScope(globals);
		System.out.println();
		System.out.println();
		System.out.println();

	}

	@Override

	public void exitProgram(DecafParser.ProgramContext ctx) {
	
		
		System.out.println(globals);
		
		
		//Verifica se tem metodo main
		boolean aux = true;
		for(int i = 0; i < currentScope.getAllSymbols().size(); i++) {
			
			if(currentScope.getAllSymbols().get(i).getName().equals("main")) {
				aux = false;
				break;
			}
		}
		
		if(aux){
			
			try {
				throw new MainNaoEncontradoException();
			} catch (MainNaoEncontradoException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				System.exit(0);
			}
		}
		//verifica se tem metodo main
		
		System.out.println();
		System.out.println();
		System.out.println();

	}

	@Override

	public void enterMethod_decl(DecafParser.Method_declContext ctx) {
	
		// int typeTokenType = ctx.start.getType();

		// DecafSymbol.Type type = this.getType(typeTokenType);

		// push new scope by making new one that points to enclosing scope
		FunctionSymbol function = new FunctionSymbol(ctx.ID().get(0).getText());
		for(int i = 1; i < ctx.ID().size(); i++) {
			function.define(new VariableSymbol(ctx.ID(i).getText()));
		}
		

		// function.setType(type); // Set symbol type

		currentScope.define(function); // Define function in current scope
		
		saveScope(ctx, function);
		
		//salva metodos
		listaMetodos.add(ctx);
		
		System.out.println();
		

		pushScope(function);
		System.out.println();
		System.out.println();
		System.out.println();
	}

	@Override

	public void exitMethod_decl(DecafParser.Method_declContext ctx) {
		
		popScope();
	
		}

	@Override

	public void enterBlock(DecafParser.BlockContext ctx) {
		LocalScope l = new LocalScope(currentScope);
		saveScope(ctx, currentScope);

		// pushScope(l);
		
	}

	@Override

	public void exitBlock(DecafParser.BlockContext ctx) {
		
	}

	@Override

	public void enterField_decl(DecafParser.Field_declContext ctx) {
		
		
		
		//Verifica se e um array
		if(ctx.ECOLC().size() > 0 && ctx.DCOLC().size() > 0) {//se maior que zero e um array
			//verifica se tamanho p array e valido
			if(Integer.parseInt(ctx.INTLITERAL(0).getText()) == 0) {
				try {
					throw new TamanhoNaoValidoException(ctx.ID(0).getText());
				} catch (TamanhoNaoValidoException e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
					System.exit(0);
				}
			}
		}
		
		int typeTokenType = ctx.start.getType();

		VariableSymbol var = new VariableSymbol(ctx.ID().get(0).getText());

		// DecafSymbol.Type type = this.getType(typeTokenType);

		// var.setType(type);
		
		System.out.println(ctx.getText());
		System.out.println(var);

		currentScope.define(var); // Define symbol in current scope
		
		//defineVar(ctx.ID().get(0), ctx.ID().get(0).getSymbol());
		
	}

	@Override

	public void exitField_decl(DecafParser.Field_declContext ctx) {
		String name = ctx.ID().get(0).getSymbol().getText();

	
		
	}
	
	@Override
	public void enterVar_decl(Var_declContext ctx) {
	

		int typeTokenType = ctx.start.getType();
		
		VariableSymbol var = new VariableSymbol(ctx.ID(0).getText());

	

	

		currentScope.define(var); // Define symbol in current scope
	
	}
	
	@Override
	public void exitVar_decl(Var_declContext ctx) {
	
	}
	
	@Override
	public void enterStatement(StatementContext ctx) {
	
	}
	
	@Override
	public void exitStatement(StatementContext ctx) {
	
	}
	
	@Override
	public void enterAssing_op(Assing_opContext ctx) {
	
	}
	
	@Override
	public void exitAssing_op(Assing_opContext ctx) {
		
	}
	
	@Override
	public void enterMethod_call(Method_callContext ctx) {
		
		
		
		
	}
	
	@Override
	public void exitMethod_call(Method_callContext ctx) {
		
	}
	
	@Override
	public void enterLocation(LocationContext ctx) {
	
		
		
		
	}
	
	@Override
	public void exitLocation(LocationContext ctx) {
	
	}
	
	@Override
	public void enterExpr(ExprContext ctx) {
		
	}
	
	@Override
	public void exitExpr(ExprContext ctx) {
		
	}
	
	

	
	private void pushScope(Scope s) {

		currentScope = s;

		System.out.println("entering: " + currentScope.getName() + ":" + s);

	}

	

	void saveScope(ParserRuleContext ctx, Scope s) {

		scopes.put(ctx, s);

	}

	

	private void popScope() {

		System.out.println("leaving: " + currentScope.getName() + ":" + currentScope);

		currentScope = currentScope.getEnclosingScope();
		
	}

	public static void error(Token t, String msg) {

		System.err.printf("line %d:%d %s\n", t.getLine(), t.getCharPositionInLine(),

				msg);

	}

	

	public static DecafSymbol.Type getType(int tokenType) {

		switch (tokenType) {

		case DecafParser.VOID:
			return DecafSymbol.Type.tVOID;

		case DecafParser.INTLITERAL:
			return DecafSymbol.Type.tINT;

		}

		return DecafSymbol.Type.tINVALID;

	}

}
