package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.Tipo;

public class TipoDAO {
	
	public void addTipo(Tipo t) throws SQLException {

		String sql = "INSERT INTO tipo (descricao_tipo) "
				+ "VALUES (?)";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, t.getDescricao());

		ps.executeUpdate();
	}

	public void deleteTipo(Tipo t) throws SQLException {

		String sql = "DELETE FROM tipo WHERE " + "id_tipo = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, t.getId());

		ps.executeUpdate();
	};

	public void updateTipo(Tipo t) throws SQLException {

		String sql = "UPDATE tipo SET "
				+ "descricao_tipo = ? WHERE id_tipo = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, t.getDescricao());
		ps.setLong(2, t.getId());

		ps.executeUpdate();
	};

	public Tipo getTipoById(Tipo t) throws SQLException {

		String sql = "SELECT id_tipo, descricao_tipo "
				+ "FROM tipo WHERE id_tipo = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, t.getId());

		ResultSet rs = ps.executeQuery();

		Tipo retorno = new Tipo();

		if (rs.next()) {
			retorno.setId(rs.getLong("id_tipo"));
			retorno.setDescricao(rs.getString("descricao_tipo"));
		}

		return retorno;
	};

	public ArrayList<Tipo> getTipoByDescricao(Tipo t)
			throws SQLException {

		String sql = "SELECT id_tipo, descricao_tipo "
				+ "FROM tipo WHERE descricao_tipo LIKE ? "
				+ "ORDER BY descricao_tipo ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "%" + t.getDescricao() + "%");
		
		ResultSet rs = ps.executeQuery();
		

		ArrayList<Tipo> lista = new ArrayList<Tipo>();

		while (rs.next()) {
			Tipo item = new Tipo();
			item.setId(rs.getLong("id_tipo"));
			item.setDescricao(rs.getString("descricao_tipo"));

			lista.add(item);
		}

		return lista;
	}

	public ArrayList<Tipo> getTipos() throws SQLException {

		String sql = "SELECT id_tipo, descricao_tipo "
				+ "FROM tipo ORDER BY descricao_tipo ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Tipo> lista = new ArrayList<Tipo>();

		while (rs.next()) {
			Tipo t = new Tipo();
			t.setId(rs.getLong("id_tipo"));
			t.setDescricao(rs.getString("descricao_tipo"));

			lista.add(t);
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
