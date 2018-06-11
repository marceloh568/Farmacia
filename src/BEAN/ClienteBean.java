package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.ClienteDAO;
import MODEL.Cliente;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class ClienteBean {
	
	// para inser��o, altera��o e exclus�o do fabricante
		private Cliente cliente;

		// para listagem de fabricantes
		private ArrayList<Cliente> itens;
		private ArrayList<Cliente> itensFiltrados;

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public ArrayList<Cliente> getItens() {
			return itens;
		}

		public void setItens(ArrayList<Cliente> itens) {
			this.itens = itens;
		}

		public ArrayList<Cliente> getItensFiltrados() {
			return itensFiltrados;
		}

		public void setItensFiltrados(ArrayList<Cliente> itensFiltrados) {
			this.itensFiltrados = itensFiltrados;
		}

		// Anota��o para dizer que o m�todo automaticamente ser� chamado
		// antes da p�gina ser redenrizada
		@PostConstruct
		public void prepararPesquisa() {

			try {
				ClienteDAO dao = new ClienteDAO();
				itens = dao.listar();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}

		}

		public void carregarListagem() {

			try {
				ClienteDAO dao = new ClienteDAO();

				itens = dao.listar();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro(ex.getMessage());
			}
		}
		
		
		public void prepararNovo() {
			cliente = new Cliente();
		}

		public void novoCliente() {
			try {
				ClienteDAO dao = new ClienteDAO();
				dao.salvar(cliente);

				itens = dao.listar();

				JSFUtil.adicionarMenssagemSucesso("Cliente salvo com sucesso!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}

		}

		public void excluir() {

			try {
				ClienteDAO dao = new ClienteDAO();
				dao.excluir(cliente);

				itens = dao.listar();

				JSFUtil.adicionarMenssagemSucesso("Cliente exclu�do com �xito!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}
		}

		public void editar() {

			try {
				ClienteDAO dao = new ClienteDAO();
				dao.editar(cliente);;

				itens = dao.listar();

				JSFUtil.adicionarMenssagemSucesso("Cliente editado com �xito!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}
		}
	
}
