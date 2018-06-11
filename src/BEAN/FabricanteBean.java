package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.FabricanteDAO;
import MODEL.Fabricante;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	// para inser��o, altera��o e exclus�o do fabricante
	private Fabricante fabricante;

	// para listagem de fabricantes
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// Anota��o para dizer que o m�todo automaticamente ser� chamado
	// antes da p�gina ser redenrizada
	@PostConstruct
	public void prepararPesquisa() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			itens = dao.getFabricantes();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}
		
	}
	
	public void carregarListagem() {

		try {
			FabricanteDAO dao = new FabricanteDAO();

			itens = dao.getFabricantes();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novoFabricante() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.addFabricante(fabricante);

			itens = dao.getFabricantes();

			JSFUtil.adicionarMenssagemSucesso("Fabricante salvo com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}

	}

	public void excluir() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.deleteFabricante(fabricante);

			itens = dao.getFabricantes();

			JSFUtil.adicionarMenssagemSucesso("Fabricante exclu�do com �xito!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.updateFabricante(fabricante);

			itens = dao.getFabricantes();

			JSFUtil.adicionarMenssagemSucesso("Fabricante editado com �xito!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}
	}
}
