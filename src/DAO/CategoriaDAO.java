package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.Categoria;

public class CategoriaDAO {

	public void addCategoria(Categoria c) throws SQLException {

		String sql = "INSERT INTO categoria (descricao_categoria) "
				+ "VALUES (?)";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, c.getDescricao());

		ps.executeUpdate();
	}

	public void deleteCategoria(Categoria c) throws SQLException {

		String sql = "DELETE FROM categoria WHERE " + "id_categoria = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, c.getId());

		ps.executeUpdate();
	};

	public void updateCategoria(Categoria c) throws SQLException {

		String sql = "UPDATE categoria SET "
				+ "descricao_categoria = ? WHERE id_categoria = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, c.getDescricao());
		ps.setLong(2, c.getId());

		ps.executeUpdate();
	};

	public Categoria getCategoriaById(Categoria c) throws SQLException {

		String sql = "SELECT id_categoria, descricao_categoria "
				+ "FROM categoria WHERE id_categoria = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, c.getId());

		ResultSet rs = ps.executeQuery();

		Categoria retorno = new Categoria();

		if (rs.next()) {
			retorno.setId(rs.getLong("id_categoria"));
			retorno.setDescricao(rs.getString("descricao_categoria"));
		}

		return retorno;
	};

	public ArrayList<Categoria> getCategoriaByDescricao(Categoria c)
			throws SQLException {

		String sql = "SELECT id_categoria, descricao_categoria "
				+ "FROM categoria WHERE descricao_categoria LIKE ? "
				+ "ORDER BY descricao_categoria ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "%" + c.getDescricao() + "%");
		
		ResultSet rs = ps.executeQuery();
		

		ArrayList<Categoria> lista = new ArrayList<Categoria>();

		while (rs.next()) {
			Categoria item = new Categoria();
			item.setId(rs.getLong("id_categoria"));
			item.setDescricao(rs.getString("descricao_categoria"));

			lista.add(item);
		}

		return lista;
	}

	public ArrayList<Categoria> getCategorias() throws SQLException {

		String sql = "SELECT id_categoria, descricao_categoria "
				+ "FROM categoria ORDER BY descricao_categoria ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Categoria> lista = new ArrayList<Categoria>();

		while (rs.next()) {
			Categoria c = new Categoria();
			c.setId(rs.getLong("id_categoria"));
			c.setDescricao(rs.getString("descricao_categoria"));

			lista.add(c);
		}

		return lista;
	}

	public static void main(String[] args) {
		
		/*
		 * TESTES
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("Teste1");
		 * Fabricante f2 = new Fabricante(); f2.setDescricao("Teste2");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.addFabricante(f1); fdao.addFabricante(f2);
		 * System.out.println("Salvo com sucesso!"); } catch (SQLException e) {
		 * // TODO Auto-generated catch block
		 * System.out.println("Ocorreu erro!"); e.printStackTrace(); }
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setId(2L);
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setId(1L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.deleteFabricante(f2); fdao.deleteFabricante(f1);
		 * System.out.println("OK!"); } catch (SQLException e) { // TODO
		 * Auto-generated catch block System.out.println("NÃO OK!");
		 * e.printStackTrace(); }
		 * 
		 * Fabricante f3 = new Fabricante(); f3.setId(3L);
		 * f3.setDescricao("TesteUpdate");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.updateFabricante(f3); System.out.println("OK!"); } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * System.out.println("Erro!"); e.printStackTrace(); }
		 * 
		 * 
		 * Fabricante f1 = new Fabricante(); f1.setId(3L);
		 * 
		 * Fabricante f2 = new Fabricante(); f1.setId(4L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { Fabricante f3 = fdao.getFabricanteById(f1);
		 * 
		 * System.out.println("Resultado 1: " + f3);
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * System.out.println("Erro na consulta pelo ID"); e.printStackTrace();
		 * }
		 * 
		 FabricanteDAO fdao = new FabricanteDAO();
		 
		 try { ArrayList<Fabricante> lista = fdao.getFabricantes();
		 
		 for(Fabricante f : lista){ System.out.println("Resultado: " + f); } }
		 catch (SQLException e) { // TODO Auto-generated catch block
		 System.out.println("Erro ao listar"); e.printStackTrace(); }
		 

		Fabricante f1 = new Fabricante();
		f1.setDescricao("2");

		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			ArrayList<Fabricante> lista = fdao.getFabricanteByDescricao(f1);

			for (Fabricante f : lista) {
				System.out.println("Resultado: " + f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO!");
			e.printStackTrace();
		}*/

	}
	
}
