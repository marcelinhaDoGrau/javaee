package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	// Modo de Conexão
	// Pâmetros para Conexão

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "backend@24";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println("Deu Error na Conesão");
			return null;
		}

	}

	/**
	 * Inserir contatos.
	 *
	 * @param contato the contato
	 */
	// CRUD - CREATE
	public void inserirContatos(JavaBeans contato) {
		String create = "insert into contatos(nome, fone, email) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a consulta (query) para execução no Banco de Dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo dos atributos da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query (Ctrl + Enter)
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println("Erro na Insert" + e);
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	// CRUD -> READ
	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variáveis de apoio que recebem os dados do banco
				int idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD UPDATE
	/**
	 * Selecao contato.
	 *
	 * @param contato the contato
	 * @return the java beans
	 */
	// Selecionar contato
	public JavaBeans selecaoContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			
			JavaBeans select = new JavaBeans();
			
			// Laço while para receber dados do BD e enviar para a classe JavaBeans
			while (rs.next()) {
				// setar as variáveis do JavaBeans
				select.setIdcon(rs.getInt(1));
				select.setNome(rs.getString(2));
				select.setFone(rs.getString(3));
				select.setEmail(rs.getString(4));
			}
			
			return select;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Editar contato.
	 *
	 * @param contato the contato
	 */
	public void editarContato(JavaBeans contato) {
		String atualizar = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(atualizar);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Remover contato.
	 *
	 * @param contato the contato
	 */
	public void removerContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Teste de conexão
	/*
	 * public void testeConexao() { try { Connection con = conectar();
	 * System.out.println(con); con.close(); }catch (Exception e) {
	 * System.out.println(e); } }
	 */
}
