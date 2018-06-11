package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.VendaInjecao;

public class VendaInjecaoDAO {

	public ArrayList<VendaInjecao> listar() throws SQLException {

		String sql = "SELECT id_venda_injecao, cliente_venda_injecao, usuario_venda_injecao, injecao_venda, quantidade_venda_injecao, valor_total, data_venda "
				+ "FROM venda_injecao ORDER BY valor_total ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<VendaInjecao> lista = new ArrayList<VendaInjecao>();

		while (rs.next()) {
			VendaInjecao i = new VendaInjecao();
			i.setId(rs.getLong("id_venda_injecao"));
			i.setDescricaoCliente(rs.getString("cliente_venda_injecao"));
			i.setDescricaoUsuario(rs.getString("usuario_venda_injecao"));
			i.setDescricaoInjecao(rs.getString("injecao_venda"));
			i.setQuantidade(rs.getLong("quantidade_venda_injecao"));
			i.setValorTotal(rs.getDouble("valor_total"));
			i.setData(rs.getString("data_venda"));

			lista.add(i);
		}

		return lista;
	}
}
