package model.entities;

import java.util.Date;
import java.util.Objects;

public class Funcionario {
	private Integer id;
	private String nome;
	private Date birthDate;
	private Double salario;
	
	private Cargo cargo;
	
	public Funcionario() {
	}

	public Funcionario(Integer id, String nome, Date birthDate, Double salario, Cargo cargo) {
		this.id = id;
		this.nome = nome;
		this.birthDate = birthDate;
		this.salario = salario;
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", BirthDate=" + birthDate + ", salario=" + salario + ", cargo=" + cargo + "]";
	}
}
