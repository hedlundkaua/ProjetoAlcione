package model.entities;

import java.util.Date;
import java.util.Objects;

public class Product {
	private Integer Id;
	private String nome;
	private Integer qntd;
	private Double preco;
	private Date validade; 
	private Fornecedor fornecedor;
	
	public Product() {
		
	}

	public Product(Integer id, String nome, Integer qntd, Double preco, Date validade, Fornecedor fornecedor) {
		Id = id;
		this.nome = nome;
		this.qntd = qntd;
		this.preco = preco;
		this.validade = validade;
		this.fornecedor = fornecedor;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQntd() {
		return qntd;
	}

	public void setQntd(Integer qntd) {
		this.qntd = qntd;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(Id, other.Id);
	}

	@Override
	public String toString() {
		return "Product [Id=" + Id + ", nome=" + nome + ", qntd=" + qntd + ", preco=" + preco + ", validade=" + validade
				+ ", fornecedor=" + fornecedor + "]";
	}
}
