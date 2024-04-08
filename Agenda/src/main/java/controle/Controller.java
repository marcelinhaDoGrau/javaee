package controle;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/lista", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();

	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Teste de conexão
		/* dao.testeConexao(); */
		String requisicao = request.getServletPath();
		System.out.println(requisicao);
		if (requisicao.equals("/main")) {
			contatos(request, response);
		} else if (requisicao.equals("/insert")) {
			novoContato(request, response);
		} else if (requisicao.equals("/lista")) {
			listaContatos(request, response);
		} else if (requisicao.equals("/select")) {
			selecionarContato(request, response);
		} else if (requisicao.equals("/update")) {
			editarContato(request, response);
		} else if (requisicao.equals("/delete")) {
			removerContato(request, response);
		} else if (requisicao.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");
		// Criando um objeto que iá receber os dados da classe JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhamento do objeto lista ao documento agenda.js
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// teste de recebimento de dados do formulário novo.html
		
//		 System.out.println(request.getParameter("nome"));
//		 System.out.println(request.getParameter("fone"));
//		 System.out.println(request.getParameter("email"));
		
		// setar os atributos da Classe JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// Incovar o método inserirContato passando o objeto
		dao.inserirContatos(contato);
		// redirecionar para a página agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Lista contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listaContatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("listaContatos.jsp");
	}

	/**
	 * Selecionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebiemnto do id de contato que será editado
		String Idcon = request.getParameter("idcon");
		// System.out.println(idcon);
		contato.setIdcon(Integer.parseInt(Idcon));
		// Executar o método selecionar contato pelo DAO
		JavaBeans contatoSelet = dao.selecaoContato(contato);
		
		// Setar os atributos ao formulário com o conteúdo da Classe JavaBeans
		request.setAttribute("Idcon", contatoSelet.getIdcon());
		request.setAttribute("Nome", contatoSelet.getNome());
		request.setAttribute("Fone", contatoSelet.getFone());
		request.setAttribute("Email", contatoSelet.getEmail());
		
		// Encaminhar ao documento Editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento para edição
		/*
		 * System.out.println(request.getParameter("Idcon"));
		 * System.out.println(request.getParameter("Nome"));
		 * System.out.println(request.getParameter("Fone"));
		 * System.out.println(request.getParameter("Email"));
		 */

		// Setar os atributos do JavaBeans
		contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Executar o método alterarContato da classe DAO
		dao.editarContato(contato);
		response.sendRedirect("main");
	}


	// teste de recebimento de lista
	/*
	 * for (int i = 0; i < lista.size(); i++) {
	 * System.out.println(lista.get(i).getIdcon());
	 * System.out.println(lista.get(i).getNome());
	 * System.out.println(lista.get(i).getFone());
	 * System.out.println(lista.get(i).getEmail()) }
	 */


	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Idcon = request.getParameter("idcon");
		System.out.println(Idcon);
		contato.setIdcon(Integer.parseInt(Idcon));

		dao.removerContato(contato);
		response.sendRedirect("main");
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Gerar Relatório em PDF
		Document documento = new Document();
		try {
			// Tipo de conteúdo
			response.setContentType("application/pdf");
			// Nome do documento
			response.addHeader("Content-Disposition", "inline: filename="+"contatos.pdf");
			// Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de Contatos: "));
			// Listagem de contato
			documento.add(new Paragraph(" "));
			// Criar uma tabela no pdf
			PdfPTable tabela = new PdfPTable(3);
			// Cabecalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for(int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			documento.close();
		}
	}

}
