package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.UsuarioDAO;
import MODEL.Usuario;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	// para inserção, alteração e exclusão do fabricante
	private Usuario usuario;
	private String cpfTeste;
	private String senhaTeste;

	// para listagem de fabricantes
	private ArrayList<Usuario> itens;
	private ArrayList<Usuario> itensFiltrados;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Usuario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Usuario> itens) {
		this.itens = itens;
	}

	public ArrayList<Usuario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Usuario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public String getCpfTeste() {
		return cpfTeste;
	}

	public void setCpfTeste(String cpfTeste) {
		this.cpfTeste = cpfTeste;
	}

	public String getSenhaTeste() {
		return senhaTeste;
	}

	public void setSenhaTeste(String senhaTeste) {
		this.senhaTeste = senhaTeste;
	}

	// Anotação para dizer que o método automaticamente será chamado
	// antes da página ser redenrizada
	@PostConstruct
	public void prepararPesquisa() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}

	}

	public void carregarListagem() {

		try {
			UsuarioDAO dao = new UsuarioDAO();

			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {
		usuario = new Usuario();
	}

	public void novoUsuario() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.salvar(usuario);

			itens = dao.listar();

			JSFUtil.adicionarMenssagemSucesso("Usuário salvo com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}

	}

	public void excluir() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(usuario);

			itens = dao.listar();

			JSFUtil.adicionarMenssagemSucesso("Usuário excluído com êxito!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.editar(usuario);
			;

			itens = dao.listar();

			JSFUtil.adicionarMenssagemSucesso("Usuário editado com êxito!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}
	}

	public String chamaAutenticar(){

		String redirecionamento = null;
		
		try {
			
			UsuarioDAO dao = new UsuarioDAO();
			redirecionamento = dao.autenticar(cpfTeste, senhaTeste);
		} catch (SQLException ex) {
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}
		return redirecionamento;
	}
}
