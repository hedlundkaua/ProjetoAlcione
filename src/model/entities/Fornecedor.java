package model.entities;

import java.util.Objects;

public class Fornecedor {
	private Integer Id;
	private String nome;
	private Integer cnpj;
	private String email;
	
	public Fornecedor() {
		
	}

	public Fornecedor(Integer id, String nome, Integer cnpj, String email) {
		Id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
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

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(Id, other.Id);
	}

	@Override
	public String toString() {
		return "Fornecedor [Id=" + Id + ", nome=" + nome + ", cnpj=" + cnpj + ", email=" + email + "]";
	}
}
