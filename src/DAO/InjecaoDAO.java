package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.Injecao;
import MODEL.Tipo;

public class InjecaoDAO {

	public void salvar(Injecao i) throws SQLException {

		String sql = "INSERT INTO injecao (descricao_injecao, preco_injecao, id_tipo) "
				+ "VALUES (?, ?, ?)";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, i.getDescricao());
		ps.setDouble(2, i.getPreco());
		ps.setLong(3, i.getTipo().getId());

		ps.executeUpdate();
	}

	public ArrayList<Injecao> listar() throws SQLException {

		String sql = "SELECT i.id_injecao, i.descricao_injecao, "
				+ "i.preco_injecao, " + "t.id_tipo, t.descricao_tipo "
				+ "FROM injecao i "
				+ "INNER JOIN tipo t ON t.id_tipo = i.id_tipo";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Injecao> itens = new ArrayList<Injecao>();

		while (rs.next()) {

			Tipo t = new Tipo();
			t.setId(rs.getLong("t.id_tipo"));
			t.setDescricao(rs.getString("t.descricao_tipo"));

			Injecao i = new Injecao();
			i.setId(rs.getLong("i.id_injecao"));
			i.setDescricao(rs.getString("i.descricao_injecao"));
			i.setPreco(rs.getDouble("i.preco_injecao"));
			i.setTipo(t);
			;

			itens.add(i);
		}

		return itens;
	}

	public void excluir(Injecao i) throws SQLException {

		String sql = "DELETE FROM injecao " + "WHERE id_injecao = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, i.getId());

		ps.executeUpdate();
	}

	public void editar(Injecao i) throws SQLException {

		String sql = "UPDATE injecao " + "SET descricao_injecao = ?, "
				+ "preco_injecao = ?, id_tipo = ? " + "WHERE id_injecao = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, i.getDescricao());
		ps.setDouble(2, i.getPreco());
		ps.setLong(3, i.getTipo().getId());
		ps.setLong(4, i.getId());

		ps.executeUpdate();
	}

	public void vender(Injecao i, Long quantidade, String cliente,
			String usuario, String data) throws SQLException {

		String sql = "INSERT INTO venda_injecao (cliente_venda_injecao, usuario_venda_injecao, injecao_venda, quantidade_venda_injecao, valor_total, data_venda) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		Double valorTotal;
		valorTotal = i.getPreco() * quantidade;

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, cliente);
		ps.setString(2, usuario);
		ps.setString(3, i.getDescricao());
		ps.setLong(4, quantidade);
		ps.setDouble(5, valorTotal);
		ps.setString(6, data);

		ps.executeUpdate();
	}
}
