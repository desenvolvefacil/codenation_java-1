/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.codenation.desafio.bean;

import java.time.LocalDate;

/**
 *
 * @author Carlao
 */
public class TimeBean {

    private long id;//* Identificador do time
    private String nome;//* Nome do TimeBean
    private LocalDate dataCriacao;//* Data de criação do time
    private String corUniformePrincipal;//* Cor do uniforme principal do time
    private String corUniformeSecundario;//* Cor do uniforme secundário do time
    private Long idCapitao;

    public TimeBean(long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
    }

    
    
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
     * @return the dataCriacao
     */
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @param dataCriacao the dataCriacao to set
     */
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return the corUniformePrincipal
     */
    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    /**
     * @param corUniformePrincipal the corUniformePrincipal to set
     */
    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    /**
     * @return the corUniformeSecundario
     */
    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    /**
     * @param corUniformeSecundario the corUniformeSecundario to set
     */
    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public Long getCapitao() {
        return idCapitao;
    }

    public void setCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }

}
