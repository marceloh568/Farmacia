package TEST;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import DAO.ProdutoDAO;
import MODEL.Categoria;
import MODEL.Fabricante;
import MODEL.Produto;

public class ProdutoDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException{
		
		Produto p = new Produto();
		p.setDescricao("TYLENOL COM 10 COMPRIMIDOS");
		p.setPreco(2.45D);
		p.setQuantidade(13L);
		
		Fabricante f = new Fabricante();
		f.setId(1L);
		p.setFabricante(f);
		
		Categoria c = new Categoria();
		c.setId(1L);
		p.setCategoria(c);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p: lista){
			System.out.println("ID_Produto: " + p.getId());
			System.out.println("Descricao_Prod: " + p.getDescricao());
			System.out.println("Preco: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("ID_Fabricante: " + p.getFabricante().getId());
			System.out.println("Descricao_Fab: " + p.getFabricante().getDescricao());
			System.out.println("ID_Categoria: " + p.getCategoria().getId());
			System.out.println("Descricao_Cat: " + p.getCategoria().getDescricao());
			System.out.println();
		}	
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException{
		Produto p = new Produto();
		p.setId(1L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
	
	@Test
	public void editar() throws SQLException{
		Produto p = new Produto();
		p.setId(2L);
		
		p.setDescricao("Cataflan");
		p.setPreco(15.30D);
		p.setQuantidade(7L);
		
		Fabricante f = new Fabricante();
		f.setId(2L);
		
		Categoria c = new Categoria();
		c.setId(2L);
		
		p.setFabricante(f);
		p.setCategoria(c);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
	}
}
