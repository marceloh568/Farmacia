package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FACTORY.ConexaoFactory;
import MODEL.Usuario;

public class UsuarioDAO {
	
	public void salvar(Usuario u) throws SQLException {

		String sql = "INSERT INTO usuario (descricao_usuario, cpf_usuario, telefone_usuario, data_nascimento_usuario, senha_usuario) "
				+ "VALUES (?, ?, ?, ?, ?)";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, u.getDescricao());
		ps.setString(2, u.getCpf());
		ps.setString(3, u.getTelefone());
		ps.setString(4, u.getDataNascimento());
		ps.setString(5, u.getSenha());

		ps.executeUpdate();
	}

	public ArrayList<Usuario> listar() throws SQLException {

		String sql = "SELECT id_usuario, descricao_usuario, cpf_usuario, telefone_usuario, data_nascimento_usuario, senha_usuario "
				+ "FROM usuario ORDER BY descricao_usuario ASC";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario u = new Usuario();
			u.setId(rs.getLong("id_usuario"));
			u.setDescricao(rs.getString("descricao_usuario"));
			u.setCpf(rs.getString("cpf_usuario"));
			u.setTelefone(rs.getString("telefone_usuario"));
			u.setDataNascimento(rs.getString("data_nascimento_usuario"));
			u.setSenha(rs.getString("senha_usuario"));

			lista.add(u);
		}

		return lista;	
		
	}

	public void excluir(Usuario u) throws SQLException {

		String sql = "DELETE FROM usuario " + "WHERE id_usuario = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setLong(1, u.getId());

		ps.executeUpdate();
	}

	public void editar(Usuario u) throws SQLException {

		String sql = "UPDATE usuario "
				+ "SET descricao_usuario = ?, cpf_usuario = ?, "
				+ "telefone_usuario = ?, data_nascimento_usuario = ?, senha_usuario = ? "
				+ "WHERE id_usuario = ?";

		Connection conexao = ConexaoFactory.con();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, u.getDescricao());
		ps.setString(2, u.getCpf());
		ps.setString(3, u.getTelefone());
		ps.setString(4, u.getDataNascimento());
		ps.setString(5, u.getSenha());
		ps.setLong(6, u.getId());

		ps.executeUpdate();
	}
	
	public String autenticar(String cpf, String senha) throws SQLException{
		
		String sql = "SELECT cpf_usuario, senha_usuario FROM usuario WHERE "
		+ "cpf_usuario = ? AND senha_usuario = ?";
		
		Connection conexao = ConexaoFactory.con();
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, cpf);
		ps.setString(2, senha);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario u = new Usuario();
			u.setCpf(rs.getString("cpf_usuario"));
			u.setSenha(rs.getString("senha_usuario"));

			lista.add(u);
		}
		
		
		if(lista.size()>0) return "principal.xhtml";
		else return "login.xhtml";	
	}
}
