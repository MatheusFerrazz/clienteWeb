package br.com.gcti.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Dispatch;

import br.com.gcti.model.Cliente;
import br.com.gcti.services.ClienteService;

@WebServlet(urlPatterns = "/cliente")
public class ClienteServlet extends HttpServlet {

	ClienteService clienteService;

	public ClienteServlet() {
		System.out.println("Construindo Servlet...");

	}

	@Override
	public void init() throws ServletException {
		clienteService = new ClienteService();
		System.out.println("Inicializando Servlet");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Chamando o service");
		super.service(req, res);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cliente cli = new Cliente();
		cli.setEmail("");
		int indice = -1;
		String i = req.getParameter("i");
		String acao = req.getParameter("acao");
		if (i != null && i != "" && acao != null && acao != "") {
			if (acao.equals("exc")) {
				indice = Integer.parseInt(i);
				clienteService.excluir(indice);
			} else if (acao.equals("edit")) {
				indice = Integer.parseInt(i);
				cli = ClienteService.buscarPorIndice(indice);
			}
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("cli", cli);
		req.setAttribute("iCli", indice); //Pega a opção do formulário e recupera índice. 
		req.setAttribute("lista", clienteService.getTodosClientes());
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Recebe o email
		String email = req.getParameter("email");
		String i = req.getParameter("i");
		int indice = -1;
		if (i != null && i != "") {
			indice = Integer.parseInt(i);
		}
		// Coloca o email em um objeto cliente
		Cliente cli = new Cliente();
		cli.setEmail(email);

		// Adiciona o objeto cliente na lista de clientes

		clienteService.salvar(indice, cli);
		cli = new Cliente();
		cli.setEmail("");

		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("msg", "Cadastrado com sucesso!");
		req.setAttribute("cli", cli);
		req.setAttribute("iCli", -1);
		req.setAttribute("lista", clienteService.getTodosClientes());
		dispatcher.forward(req, resp);
		// resp.sendRedirect("cliente");
		// resp.setCharacterEncoding("UTF-8");
		// resp.getWriter().print("Chamou pelo método POST enviando o e-mail: " + email
		// + "!");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print("Chamou o método DELETE!");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print("Chamou o método PUT!");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet está sendo destruído!");
		super.destroy();
	}
}
