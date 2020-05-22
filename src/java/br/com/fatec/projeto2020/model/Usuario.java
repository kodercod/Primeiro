/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.projeto2020.model;

/**
 *
 * @author jeffersonpasserini
 */
public class Usuario {
    
    private int idLogin;
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private String tipo;
    private int id;

    public Usuario() {
    }

    public Usuario(int idLogin, String nome, String cpf, String login, String senha, String tipo, int id) {
        this.idLogin = idLogin;
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.id = id;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdPessoa(int idPessoa) {
        this.idLogin = idLogin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
