package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.Cliente;

public class ClienteDAO {
	
	public void salvar(Cliente c) throws SQLException {

		String sql = "INSERT INTO cliente (descricao_cliente, cpf_cliente, telefone_cliente, data_nascimento_cliente) "
				+ "VALUES (?, ?, ?, ?)";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, c.getDescricao());
		ps.setString(2, c.getCpf());
		ps.setString(3, c.getTelefone());
		ps.setString(4, c.getDataNascimento());

		ps.executeUpdate();
	}

	public ArrayList<Cliente> listar() throws SQLException {

		String sql = "SELECT id_cliente, descricao_cliente, cpf_cliente, telefone_cliente, data_nascimento_cliente "
				+ "FROM cliente ORDER BY descricao_cliente ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Cliente> lista = new ArrayList<Cliente>();

		while (rs.next()) {
			Cliente c = new Cliente();
			c.setId(rs.getLong("id_cliente"));
			c.setDescricao(rs.getString("descricao_cliente"));
			c.setCpf(rs.getString("cpf_cliente"));
			c.setTelefone(rs.getString("telefone_cliente"));
			c.setDataNascimento(rs.getString("data_nascimento_cliente"));

			lista.add(c);
		}

		return lista;
	}

	public void excluir(Cliente c) throws SQLException {

		String sql = "DELETE FROM cliente " + "WHERE id_cliente = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, c.getId());

		ps.executeUpdate();
	}

	public void editar(Cliente c) throws SQLException {

		String sql = "UPDATE cliente "
				+ "SET descricao_cliente = ?, cpf_cliente = ?, "
				+ "telefone_cliente = ?, data_nascimento_cliente = ? "
				+ "WHERE id_cliente = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, c.getDescricao());
		ps.setString(2, c.getCpf());
		ps.setString(3, c.getTelefone());
		ps.setString(4, c.getDataNascimento());
		ps.setLong(5, c.getId());

		ps.executeUpdate();
	}
}
