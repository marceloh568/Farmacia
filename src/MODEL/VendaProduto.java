package MODEL;

public class VendaProduto {
	private Long id;
	private String descricaoCliente;
	private String descricaoUsuario;
	private String descricaoProduto;
	private Long quantidade;
	private Double valorTotal;
	private String data;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricaoCliente() {
		return descricaoCliente;
	}
	public void setDescricaoCliente(String descricaoCliente) {
		this.descricaoCliente = descricaoCliente;
	}
	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}
	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
