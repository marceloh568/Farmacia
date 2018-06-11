package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import DAO.ClienteDAO;
import DAO.InjecaoDAO;
import DAO.TipoDAO;
import DAO.UsuarioDAO;
import MODEL.Cliente;
import MODEL.Injecao;
import MODEL.Tipo;
import MODEL.Usuario;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class InjecaoBean {

	private ArrayList<Injecao> itens;
	private ArrayList<Injecao> itensFiltrados;

	private ArrayList<Cliente> comboClientes;
	private ArrayList<Usuario> comboUsuarios;

	private Long quantidadeVenda;
	private String clienteVenda;
	private String usuarioVenda;
	private String dataVenda;

	public ArrayList<Cliente> getComboClientes() {
		return comboClientes;
	}

	public void setComboClientes(ArrayList<Cliente> comboClientes) {
		this.comboClientes = comboClientes;
	}

	public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}

	public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}

	public Long getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public void setQuantidadeVenda(Long quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}

	public String getClienteVenda() {
		return clienteVenda;
	}

	public void setClienteVenda(String clienteVenda) {
		this.clienteVenda = clienteVenda;
	}

	public String getUsuarioVenda() {
		return usuarioVenda;
	}

	public void setUsuarioVenda(String usuarioVenda) {
		this.usuarioVenda = usuarioVenda;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	// GRÁFICO DO PREÇO (INJEÇÃO)
	private PieChartModel pieModelInjecao;

	public PieChartModel getPieModelInjecao() {
		return pieModelInjecao;
	}

	public void setPieModelInjecao(PieChartModel pieModelInjecao) {
		this.pieModelInjecao = pieModelInjecao;
	}

	public void listarPreco() {
		InjecaoDAO dao;
		List<Injecao> lista;

		try {
			dao = new InjecaoDAO();
			lista = dao.listar();
			graficarPiePreco(lista);
		} catch (Exception e) {
			// LÓGICA DE EXCEÇÃO
		}
	}

	private void graficarPiePreco(List<Injecao> lista) {

		pieModelInjecao = new PieChartModel();

		for (Injecao inj : lista) {
			pieModelInjecao.set(inj.getDescricao(), inj.getPreco());
		}
		pieModelInjecao.setTitle("PREÇO(S)");
		pieModelInjecao.setLegendPosition("e");
		pieModelInjecao.setFill(false);
		pieModelInjecao.setShowDataLabels(true);
		pieModelInjecao.setDiameter(250);
	}

	private Injecao injecao;
	private ArrayList<Tipo> comboTipos;

	public ArrayList<Injecao> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Injecao> itens) {
		this.itens = itens;
	}

	public ArrayList<Injecao> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Injecao> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Injecao getInjecao() {
		return injecao;
	}

	public void setInjecao(Injecao injecao) {
		this.injecao = injecao;
	}

	public ArrayList<Tipo> getComboTipos() {
		return comboTipos;
	}

	public void setComboTipos(ArrayList<Tipo> comboTipos) {
		this.comboTipos = comboTipos;
	}

	public void carregarListagem() {

		try {
			InjecaoDAO dao = new InjecaoDAO();

			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	// Cria uma injeção, cria os DAO's necessários para carregar os "Comboboxes"
	public void prepararNovo() {
		try {
			injecao = new Injecao();

			TipoDAO tdao = new TipoDAO();
			comboTipos = tdao.getTipos();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
		;
	}

	public void novo() {
		try {
			InjecaoDAO dao = new InjecaoDAO();
			dao.salvar(injecao);

			itens = dao.listar();

			JSFUtil.adicionarMenssagemSucesso("Injeção salva com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	public void excluir() {

		try {
			InjecaoDAO dao = new InjecaoDAO();

			dao.excluir(injecao);

			itens = dao.listar();

			JSFUtil.adicionarMenssagemSucesso("Injeção excluída com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	// Lista todos os dados nos comboboxes
	public void prepararEditar() {
		try {

			TipoDAO tdao = new TipoDAO();
			comboTipos = tdao.getTipos();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	public void editar() {
		try {
			InjecaoDAO dao = new InjecaoDAO();

			dao.editar(injecao);

			itens = dao.listar();

			JSFUtil.adicionarMenssagemSucesso("Injeção alterada com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	public void prepararVender() {
		try {
			ClienteDAO dao = new ClienteDAO();
			comboClientes = dao.listar();
			// FabricanteDAO dao = new FabricanteDAO();
			// comboFabricantes = dao.getFabricantes();

			UsuarioDAO udao = new UsuarioDAO();
			comboUsuarios = udao.listar();
			// CategoriaDAO cdao= new CategoriaDAO();
			// comboCategorias = cdao.getCategorias();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

	public void vender() {
		try {
			InjecaoDAO dao = new InjecaoDAO();

			dao.vender(injecao, quantidadeVenda, clienteVenda, usuarioVenda,
					dataVenda);

			itens = dao.listar();

			JSFUtil.adicionarMenssagemSucesso("Venda realizada com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}

}
