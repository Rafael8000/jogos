package application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="Nome")
public class Plataforma {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
private String nome;
public String getNome() {
    return nome;
}
public void setNome(String nome) {
    this.nome = nome;
}
private String fornecedor;
public String getFornecedor() {
    return fornecedor;
}
public void setFornecedor(String fornecedor) {
    this.fornecedor = fornecedor;
}
public static boolean isPresent() {
    return false;
}


}
