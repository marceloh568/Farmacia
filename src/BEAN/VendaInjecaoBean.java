package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.VendaInjecaoDAO;
import MODEL.VendaInjecao;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class VendaInjecaoBean {

	private VendaInjecao vendaInjecao;

	private ArrayList<VendaInjecao> itens;
	private ArrayList<VendaInjecao> itensFiltrados;

	public VendaInjecao getVendaInjecao() {
		return vendaInjecao;
	}

	public void setVendaInjecao(VendaInjecao vendaInjecao) {
		this.vendaInjecao = vendaInjecao;
	}

	public ArrayList<VendaInjecao> getItens() {
		return itens;
	}

	public void setItens(ArrayList<VendaInjecao> itens) {
		this.itens = itens;
	}

	public ArrayList<VendaInjecao> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<VendaInjecao> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// Anotação para dizer que o método automaticamente será chamado
	// antes da página ser redenrizada
	@PostConstruct
	public void prepararPesquisa() {

		try {
			VendaInjecaoDAO dao = new VendaInjecaoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
					+ ex.getMessage());
		}

	}

	public void carregarListagem() {

		try {
			VendaInjecaoDAO dao = new VendaInjecaoDAO();

			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
	
}
