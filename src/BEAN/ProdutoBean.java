package BEAN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import DAO.CategoriaDAO;
import DAO.ClienteDAO;
import DAO.FabricanteDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import MODEL.Categoria;
import MODEL.Cliente;
import MODEL.Fabricante;
import MODEL.Produto;
import MODEL.Usuario;
import UTIL.JSFUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;
	
	private Produto produto;
	private ArrayList<Fabricante> comboFabricantes;
	private ArrayList<Categoria> comboCategorias;
	
	private ArrayList<Cliente> comboClientes;
	private ArrayList<Usuario> comboUsuarios;
	
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

	private Long quantidadeVenda;
	private String clienteVenda;
	private String usuarioVenda;
	private String dataVenda;
	
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

	//GRÁFICO DO PREÇO (PIZZA)
	private PieChartModel pieModelPreco;
	
	public Long getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public void setQuantidadeVenda(Long quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}

	public PieChartModel getPieModelPreco() {
		return pieModelPreco;
	}

	public void setPieModelPreco(PieChartModel pieModelPreco) {
		this.pieModelPreco = pieModelPreco;
	}

	public void listarPreco(){
		ProdutoDAO dao;
		List<Produto> lista;
		
		try{
			dao = new ProdutoDAO();
            lista = dao.listar();
            graficarPiePreco(lista);
		}catch(Exception e){
			//LÓGICA DE EXCEÇÃO
		}
	}
	
	private void graficarPiePreco(List<Produto> lista) {

        pieModelPreco = new PieChartModel();

        for (Produto pro : lista) {
            pieModelPreco.set(pro.getDescricao(), pro.getPreco());
        }
        pieModelPreco.setTitle("PREÇO(S)");
        pieModelPreco.setLegendPosition("e");
        pieModelPreco.setFill(false);
        pieModelPreco.setShowDataLabels(true);
        pieModelPreco.setDiameter(250);
    }
	
	//GRÁFICO DA QUANTIDADE (PIZZA)

	private PieChartModel pieModelQuantidade;

	public PieChartModel getPieModelQuantidade() {
		return pieModelQuantidade;
	}
	
	public void setPieModelQuantidade(PieChartModel pieModelQuantidade) {
		this.pieModelQuantidade = pieModelQuantidade;
	}
	
	public void listarQuantidade(){
		ProdutoDAO dao;
		List<Produto> lista;
		
		try{
			dao = new ProdutoDAO();
            lista = dao.listar();
            graficarPieQuantidade(lista);
		}catch(Exception e){
			//LÓGICA DE EXCEÇÃO
		}
	}
	
	private void graficarPieQuantidade(List<Produto> lista) {

        pieModelQuantidade = new PieChartModel();

        for (Produto pro : lista) {
        	pieModelQuantidade.set(pro.getDescricao(), pro.getQuantidade());
        }
        pieModelQuantidade.setTitle("QUANTIDADE(S)");
        pieModelQuantidade.setLegendPosition("e");
        pieModelQuantidade.setFill(false);
        pieModelQuantidade.setShowDataLabels(true);
        pieModelQuantidade.setDiameter(250);
    }
	
	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

	public ArrayList<Categoria> getComboCategorias() {
		return comboCategorias;
	}

	public void setComboCategorias(ArrayList<Categoria> comboCategorias) {
		this.comboCategorias = comboCategorias;
	}

	public void carregarListagem() {

		try {
			ProdutoDAO dao = new ProdutoDAO();

			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
	
	//Cria um produto, cria os DAO's necessários para carregar os "Comboboxes"
	public void prepararNovo(){
		try{
		produto = new Produto();
		
		FabricanteDAO dao = new FabricanteDAO();
		comboFabricantes = dao.getFabricantes();
		
		CategoriaDAO cdao = new CategoriaDAO();
		comboCategorias = cdao.getCategorias();
		
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		};
	}
	
	public void novo(){
		try{
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMenssagemSucesso("Produto salvo com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
	
	public void excluir(){
		
		try{
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.excluir(produto);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMenssagemSucesso("Produto excluído com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
	
	//Lista todos os dados nos comboboxes 
	public void prepararEditar(){
		try{
			FabricanteDAO dao = new FabricanteDAO();
			comboFabricantes = dao.getFabricantes();
			
			CategoriaDAO cdao= new CategoriaDAO();
			comboCategorias = cdao.getCategorias();
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
	
	public void prepararVender(){
		try{
			ClienteDAO dao = new ClienteDAO();
			comboClientes = dao.listar();
			//FabricanteDAO dao = new FabricanteDAO();
			//comboFabricantes = dao.getFabricantes();
			
			UsuarioDAO udao = new UsuarioDAO();
			comboUsuarios = udao.listar();
			//CategoriaDAO cdao= new CategoriaDAO();
			//comboCategorias = cdao.getCategorias();
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			
			dao.editar(produto);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMenssagemSucesso("Produto alterado com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
	
	public void vender(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			
			dao.vender(produto, quantidadeVenda, clienteVenda, usuarioVenda, dataVenda);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMenssagemSucesso("Venda realizada com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMenssagemErro(ex.getMessage());
		}
	}
}
