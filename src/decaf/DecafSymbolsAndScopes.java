package decaf;
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


/**
 * This class defines basic symbols and scopes for Decaf language
 */
public class DecafSymbolsAndScopes extends DecafParserBaseListener {
    ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();
    GlobalScope globals;
    Scope currentScope; // define symbols in this scope

    @Override
    public void enterProgram(DecafParser.ProgramContext ctx) {
        globals = new GlobalScope(null);
        pushScope(globals);
    }

    @Override
    public void exitProgram(DecafParser.ProgramContext ctx) {
        System.out.println(globals);
    }

    @Override
    public void enterMethod_decl(DecafParser.Method_declContext ctx) {
        //String name = ctx.ID().get(0).getText();
        //int typeTokenType = ctx.type().getStart().getType();
        //DecafSymbol.Type type = this.getType(typeTokenType);

        // push new scope by making new one that points to enclosing scope
        FunctionSymbol function = new FunctionSymbol(ctx.ID().get(0).getText());
        for(int i = 1; i < ctx.ID().size(); i++) {
            function.define(new VariableSymbol(ctx.ID(i).getText()));
        }        // function.setType(type); // Set symbol type

        currentScope.define(function); // Define function in current scope
        saveScope(ctx, function);
        pushScope(function);
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
        popScope();
    }

    @Override
    public void enterField_decl(DecafParser.Field_declContext ctx) {
        
        
    
        
        //Verifica se e um array
        if(ctx.LBRACKET().size() > 0 && ctx.RBRACKET().size() > 0) {//se maior que zero e um array
            //verifica se tamanho p array e valido
            if(Integer.parseInt(ctx.int_literal(0).getText()) == 0) {
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
        

        //defineVar(ctx.type(), ctx.ID().getSymbol());
    }

    @Override
    public void exitField_decl(DecafParser.Field_declContext ctx) {
        String name = ctx.ID().get(0).getSymbol().getText();
        //Symbol var = currentScope.resolve(name);
        //if ( var==null ) {
        //    this.error(ctx.ID().getSymbol(), "no such variable: "+name);
        //}
        //if ( var instanceof FunctionSymbol ) {
        //    this.error(ctx.ID().getSymbol(), name+" is not a variable");
        //}
    }

    void defineVar(DecafParser.TypeContext typeCtx, Token nameToken) {
        int typeTokenType = typeCtx.start.getType();
        VariableSymbol var = new VariableSymbol(nameToken.getText());

        // DecafSymbol.Type type = this.getType(typeTokenType);
        // var.setType(type);

        currentScope.define(var); // Define symbol in current scope
    }

    /**
     * Método que atuliza o escopo para o atual e imprime o valor
     *
     * @param s
     */
    private void pushScope(Scope s) {
        currentScope = s;
        System.out.println("entering: "+currentScope.getName()+":"+s);
    }

    /**
     * Método que cria um novo escopo no contexto fornecido
     *
     * @param ctx
     * @param s
     */
    void saveScope(ParserRuleContext ctx, Scope s) {
        scopes.put(ctx, s);
    }

    /**
     * Muda para o contexto superior e atualia o escopo
     */
    private void popScope() {
        System.out.println("leaving: "+currentScope.getName()+":"+currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    public static void error(Token t, String msg) {
        System.err.printf("line %d:%d %s\n", t.getLine(), t.getCharPositionInLine(),
                msg);
    }

    /**
     * Valida tipos encontrados na linguagem para tipos reais
     *
     * @param tokenType
     * @return
     */
    public static DecafSymbol.Type getType(int tokenType) {
        switch ( tokenType ) {
            case DecafParser.VOID :  return DecafSymbol.Type.tVOID;
            case DecafParser.INTLIT :   return DecafSymbol.Type.tINT;
        }
        return DecafSymbol.Type.tINVALID;
    }


}
