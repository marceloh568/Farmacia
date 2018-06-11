package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.VendaProduto;

public class VendaProdutoDAO {

	public ArrayList<VendaProduto> listar() throws SQLException {

		String sql = "SELECT id_venda_produto, cliente_venda_produto, usuario_venda_produto, produto_venda, quantidade_venda_produto, valor_total, data_venda "
				+ "FROM venda_produto ORDER BY valor_total ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<VendaProduto> lista = new ArrayList<VendaProduto>();

		while (rs.next()) {
			VendaProduto v = new VendaProduto();
			v.setId(rs.getLong("id_venda_produto"));
			v.setDescricaoCliente(rs.getString("cliente_venda_produto"));
			v.setDescricaoUsuario(rs.getString("usuario_venda_produto"));
			v.setDescricaoProduto(rs.getString("produto_venda"));
			v.setQuantidade(rs.getLong("quantidade_venda_produto"));
			v.setValorTotal(rs.getDouble("valor_total"));
			v.setData(rs.getString("data_venda"));

			lista.add(v);
		}

		return lista;
	}
	
}
