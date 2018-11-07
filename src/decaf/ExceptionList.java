package decaf;

@SuppressWarnings("serial")
public class ExceptionList extends Exception {
	private String msg;
	
	public ExceptionList(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
	
	public static class VariavelNaoInstanciadaException extends ExceptionList{
		public VariavelNaoInstanciadaException(String var) {
			super("Variavel '"+var+"' nao instanciada.");
		}
	}
	
	public static class MainNaoEncontradoException extends ExceptionList{
		public MainNaoEncontradoException() {
			super("Metodo Main nao encontrado.");
		}
	}
	
	public static class TamanhoNaoValidoException extends ExceptionList{
		public TamanhoNaoValidoException(String var) {
			super("Para array '"+var+"' tamanho tem que ser maior que 0.");
		}
	}
	
	public static class NumeroDeArgumentosMetodoInvalidoException extends ExceptionList{
		public NumeroDeArgumentosMetodoInvalidoException(String nomeMetodo, String msg) {
			super("Numero de Argumentos para metodo '"+nomeMetodo+"' e "+msg+" que o instanciado");
		}
	}
	

}
