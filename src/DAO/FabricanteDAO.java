package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.Fabricante;

public class FabricanteDAO {

	public void addFabricante(Fabricante f) throws SQLException {

		String sql = "INSERT INTO fabricante (descricao_fabricante, cnpj_fabricante) "
				+ "VALUES (?,?)";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, f.getDescricao());
		ps.setString(2, f.getCnpj());

		ps.executeUpdate();
	}

	public void deleteFabricante(Fabricante f) throws SQLException {

		String sql = "DELETE FROM fabricante WHERE " + "id_fabricante = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, f.getId());

		ps.executeUpdate();
	};

	public void updateFabricante(Fabricante f) throws SQLException {

		String sql = "UPDATE fabricante SET "
				+ "descricao_fabricante = ?, cnpj_fabricante = ? WHERE id_fabricante = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, f.getDescricao());
		ps.setString(2, f.getCnpj());
		ps.setLong(3, f.getId());

		ps.executeUpdate();
	};

	public Fabricante getFabricanteById(Fabricante f) throws SQLException {

		String sql = "SELECT id_fabricante, descricao_fabricante, cnpj_fabricante "
				+ "FROM fabricante WHERE id_fabricante = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, f.getId());

		ResultSet rs = ps.executeQuery();

		Fabricante retorno = new Fabricante();

		if (rs.next()) {
			retorno.setId(rs.getLong("id_fabricante"));
			retorno.setDescricao(rs.getString("descricao_fabricante"));
			retorno.setCnpj(rs.getString("cnpj_fabricante"));
		}

		return retorno;
	};

	public ArrayList<Fabricante> getFabricanteByDescricao(Fabricante f)
			throws SQLException {

		String sql = "SELECT id_fabricante, descricao_fabricante, cnpj_fabricante "
				+ "FROM fabricante WHERE descricao_fabricante LIKE ? "
				+ "ORDER BY descricao_fabricante ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "%" + f.getDescricao() + "%");
		
		ResultSet rs = ps.executeQuery();
		

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (rs.next()) {
			Fabricante item = new Fabricante();
			item.setId(rs.getLong("id_fabricante"));
			item.setDescricao(rs.getString("descricao_fabricante"));
			item.setCnpj(rs.getString("cnpj_fabricante"));
			lista.add(item);
		}

		return lista;
	}

	public ArrayList<Fabricante> getFabricantes() throws SQLException {

		String sql = "SELECT id_fabricante, descricao_fabricante, cnpj_fabricante "
				+ "FROM fabricante ORDER BY descricao_fabricante ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (rs.next()) {
			Fabricante f = new Fabricante();
			f.setId(rs.getLong("id_fabricante"));
			f.setDescricao(rs.getString("descricao_fabricante"));
			f.setCnpj(rs.getString("cnpj_fabricante"));

			lista.add(f);
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
