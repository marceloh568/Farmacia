package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.Categoria;
import MODEL.Fabricante;
import MODEL.Produto;

public class ProdutoDAO {

	public void salvar(Produto p) throws SQLException {

		String sql = "INSERT INTO produto (descricao_produto, quantidade_produto, preco_produto, id_fabricante, id_categoria) "
				+ "VALUES (?, ?, ?, ?, ?)";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, p.getDescricao());
		ps.setLong(2, p.getQuantidade());
		ps.setDouble(3, p.getPreco());
		ps.setLong(4, p.getFabricante().getId());
		ps.setLong(5, p.getCategoria().getId());

		ps.executeUpdate();
	}

	public ArrayList<Produto> listar() throws SQLException {

		String sql = "SELECT p.id_produto, p.descricao_produto, "
				+ "p.preco_produto,p.quantidade_produto, "
				+ "f.id_fabricante, f.descricao_fabricante, "
				+ "c.id_categoria, c.descricao_categoria "
				+ "FROM produto p "
				+ "INNER JOIN fabricante f ON f.id_fabricante = p.id_fabricante "
				+ "INNER JOIN categoria c ON c.id_categoria = p.id_categoria";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Produto> itens = new ArrayList<Produto>();

		while (rs.next()) {
			Fabricante f = new Fabricante();
			f.setId(rs.getLong("f.id_fabricante"));
			f.setDescricao(rs.getString("f.descricao_fabricante"));

			Categoria c = new Categoria();
			c.setId(rs.getLong("c.id_categoria"));
			c.setDescricao(rs.getString("c.descricao_categoria"));

			Produto p = new Produto();
			p.setId(rs.getLong("p.id_produto"));
			p.setDescricao(rs.getString("p.descricao_produto"));
			p.setPreco(rs.getDouble("p.preco_produto"));
			p.setQuantidade(rs.getLong("p.quantidade_produto"));
			p.setFabricante(f);
			p.setCategoria(c);

			itens.add(p);
		}

		return itens;
	}

	public void excluir(Produto p) throws SQLException {

		String sql = "DELETE FROM produto " + "WHERE id_produto = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, p.getId());

		ps.executeUpdate();
	}

	public void editar(Produto p) throws SQLException {

		String sql = "UPDATE produto "
				+ "SET descricao_produto = ?, quantidade_produto = ?, "
				+ "preco_produto = ?, id_fabricante = ?, id_categoria = ? "
				+ "WHERE id_produto = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, p.getDescricao());
		ps.setLong(2, p.getQuantidade());
		ps.setDouble(3, p.getPreco());
		ps.setLong(4, p.getFabricante().getId());
		ps.setLong(5, p.getCategoria().getId());
		ps.setLong(6, p.getId());

		ps.executeUpdate();
	}
	
	public void vender(Produto p, Long quantidade, String cliente, String usuario, String data) throws SQLException{
		
		String sql = "UPDATE produto SET quantidade_produto = quantidade_produto - ? "  
				   + "WHERE id_produto = ?";
		
		String sql2 = "INSERT INTO venda_produto (cliente_venda_produto, usuario_venda_produto, produto_venda, quantidade_venda_produto, valor_total, data_venda) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		Double valorTotal;
		valorTotal = p.getPreco() * quantidade;
		
		
		Connection conexao = ConexaoFactory.con();
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setLong(1, quantidade);
		ps.setLong(2, p.getId());
		
		ps.executeUpdate();
		
		//SEGUNDO SQL
		PreparedStatement ps2 = conexao.prepareStatement(sql2);
		
		ps2.setString(1, cliente);
		ps2.setString(2, usuario);
		ps2.setString(3, p.getDescricao());
		ps2.setLong(4, quantidade);
		ps2.setDouble(5, valorTotal);
		ps2.setString(6, data);

		ps2.executeUpdate();
	}

}
