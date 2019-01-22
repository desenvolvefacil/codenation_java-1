/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.codenation.desafio.bean;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Carlao
 */
public class JogadorBean{

    private Long id;//** Identificador do JogadorBean
    private Long idTime;//** Identificador do time
    private String nome;//** Nome do JogadorBean
    private LocalDate dataNascimento;//** Data de nascimento do JogadorBean
    private Integer nivelHabilidade;//** Nível de habilidade do jogador (0 a 100)
    private BigDecimal salario;//** Salário do jogador

    public JogadorBean(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }

    
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the idTime
     */
    public Long getIdTime() {
        return idTime;
    }

    /**
     * @param idTime the idTime to set
     */
    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataNascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the nivelHabilidade
     */
    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    /**
     * @param nivelHabilidade the nivelHabilidade to set
     */
    public void setNivelHabilidade(Integer nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    /**
     * @return the salario
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    
    
    
}
