package controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.UsuarioPersistente;

@WebFilter("/security/index.xhtml")
public class loginAuth implements Filter {
	
    public loginAuth() {

    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = (HttpSession)req.getSession();
		
		UsuarioPersistente usuario = (UsuarioPersistente) session.getAttribute("username");
		
		if(usuario == null) {
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(req.getContextPath() + "/app/home.xhtml");
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
