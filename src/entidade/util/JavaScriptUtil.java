package entidade.util;

import org.primefaces.context.RequestContext;

public class JavaScriptUtil {
	public static void executarJavaScript(String javascript){
		RequestContext.getCurrentInstance().execute(javascript);
		}

		public static void showDialogo(String nomeDoDialogo){
		executarJavaScript("PF('" + nomeDoDialogo + "').show();");
		}

		public static void hideDialogo(String nomeDoDialogo){
		executarJavaScript("PF('" + nomeDoDialogo + "').hide();");
		}

		public static void updateComponent(String idDoComponent){
		RequestContext.getCurrentInstance().update(idDoComponent);
		}

}
