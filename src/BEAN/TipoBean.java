package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.TipoDAO;
import MODEL.Tipo;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class TipoBean {

	// para inserção, alteração e exclusão do tipo
			private Tipo tipo;

			// para listagem de tipos
			private ArrayList<Tipo> itens;
			private ArrayList<Tipo> itensFiltrados;

			public Tipo getTipo() {
				return tipo;
			}

			public void setTipo(Tipo tipo) {
				this.tipo = tipo;
			}

			public ArrayList<Tipo> getItens() {
				return itens;
			}

			public void setItens(ArrayList<Tipo> itens) {
				this.itens = itens;
			}

			public ArrayList<Tipo> getItensFiltrados() {
				return itensFiltrados;
			}

			public void setItensFiltrados(ArrayList<Tipo> itensFiltrados) {
				this.itensFiltrados = itensFiltrados;
			}

			// Anotação para dizer que o método automaticamente será chamado
			// antes da página ser redenrizada
			@PostConstruct
			public void prepararPesquisa() {

				try {
					TipoDAO dao = new TipoDAO();
					itens = dao.getTipos();
				} catch (SQLException ex) {
					ex.printStackTrace();
					JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
							+ ex.getMessage());
				}

			}

			public void carregarListagem() {

				try {
					TipoDAO dao = new TipoDAO();

					itens = dao.getTipos();
				} catch (SQLException ex) {
					ex.printStackTrace();
					JSFUtil.adicionarMenssagemErro(ex.getMessage());
				}
			}
			
			
			public void prepararNovo() {
				tipo = new Tipo();
			}

			public void novoTipo() {
				try {
					TipoDAO dao = new TipoDAO();
					dao.addTipo(tipo);

					itens = dao.getTipos();

					JSFUtil.adicionarMenssagemSucesso("Tipo salvo com sucesso!");
				} catch (SQLException ex) {
					ex.printStackTrace();
					JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
							+ ex.getMessage());
				}

			}

			public void excluir() {

				try {
					TipoDAO dao = new TipoDAO();
					dao.deleteTipo(tipo);

					itens = dao.getTipos();

					JSFUtil.adicionarMenssagemSucesso("Tipo excluído com êxito!");
				} catch (SQLException ex) {
					ex.printStackTrace();
					JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
							+ ex.getMessage());
				}
			}

			public void editar() {

				try {
					TipoDAO dao = new TipoDAO();
					dao.updateTipo(tipo);

					itens = dao.getTipos();

					JSFUtil.adicionarMenssagemSucesso("Tipo editado com êxito!");
				} catch (SQLException ex) {
					ex.printStackTrace();
					JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
							+ ex.getMessage());
				}
			}
	
}
