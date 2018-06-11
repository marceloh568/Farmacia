package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.CategoriaDAO;
import MODEL.Categoria;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class CategoriaBean {

	// para inserção, alteração e exclusão da categoria
		private Categoria categoria;

		// para listagem de categorias
		private ArrayList<Categoria> itens;
		private ArrayList<Categoria> itensFiltrados;

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public ArrayList<Categoria> getItens() {
			return itens;
		}
		
		public void setItens(ArrayList<Categoria> itens) {
			this.itens = itens;
		}
		
		public ArrayList<Categoria> getItensFiltrados() {
			return itensFiltrados;
		}
		
		public void setItensFiltrados(ArrayList<Categoria> itensFiltrados) {
			this.itensFiltrados = itensFiltrados;
		}

		// Anotação para dizer que o método automaticamente será chamado
		// antes da página ser redenrizada
		@PostConstruct
		public void prepararPesquisa() {

			try {
				CategoriaDAO dao = new CategoriaDAO();
				itens = dao.getCategorias();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}

		}

		public void carregarListagem() {

			try {
				CategoriaDAO dao = new CategoriaDAO();

				itens = dao.getCategorias();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro(ex.getMessage());
			}
		}
		
		
		public void prepararNovo() {
			categoria = new Categoria();
		}

		public void novoCategoria() {
			try {
				CategoriaDAO dao = new CategoriaDAO();
				dao.addCategoria(categoria);

				itens = dao.getCategorias();

				JSFUtil.adicionarMenssagemSucesso("Categoria salva com sucesso!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}

		}

		public void excluir() {

			try {
				CategoriaDAO dao = new CategoriaDAO();
				dao.deleteCategoria(categoria);

				itens = dao.getCategorias();

				JSFUtil.adicionarMenssagemSucesso("Categoria excluída com êxito!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}
		}

		public void editar() {

			try {
				CategoriaDAO dao = new CategoriaDAO();
				dao.updateCategoria(categoria);

				itens = dao.getCategorias();

				JSFUtil.adicionarMenssagemSucesso("Categoria editada com êxito!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMenssagemErro("Ocorreram erros: "
						+ ex.getMessage());
			}
		}
	
}
