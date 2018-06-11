package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.VendaProdutoDAO;
import MODEL.VendaProduto;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class VendaProdutoBean {

			private VendaProduto vendaProduto;

			private ArrayList<VendaProduto> itens;
			private ArrayList<VendaProduto> itensFiltrados;

			public VendaProduto getVendaProduto() {
				return vendaProduto;
			}

			public void setVendaProduto(VendaProduto vendaProduto) {
				this.vendaProduto = vendaProduto;
			}

			public ArrayList<VendaProduto> getItens() {
				return itens;
			}

			public void setItens(ArrayList<VendaProduto> itens) {
				this.itens = itens;
			}

			public ArrayList<VendaProduto> getItensFiltrados() {
				return itensFiltrados;
			}

			public void setItensFiltrados(ArrayList<VendaProduto> itensFiltrados) {
				this.itensFiltrados = itensFiltrados;
			}

			// Anotação para dizer que o método automaticamente será chamado
			// antes da página ser redenrizada
			@PostConstruct
			public void prepararPesquisa() {

				try {
					VendaProdutoDAO dao = new VendaProdutoDAO();
					itens = dao.listar();
				} catch (SQLException ex) {
					ex.printStackTrace();
					JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
							+ ex.getMessage());
				}

			}

			public void carregarListagem() {

				try {
					VendaProdutoDAO dao = new VendaProdutoDAO();

					itens = dao.listar();
				} catch (SQLException ex) {
					ex.printStackTrace();
					JSFUtil.adicionarMenssagemErro(ex.getMessage());
				}
			}
	
}
