package com.example.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String autor;
    private String idioma;
    private Double numeroDownloads;


    private Integer anoNascimentoAutor;
    private Integer anoFalecimentoAutor;

    public Livro(){}

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.idioma = dadosLivro.idiomas().get(0);
        this.numeroDownloads = dadosLivro.numeroDownloads();

        if(!dadosLivro.autores().isEmpty()){
            this.autor = dadosLivro.autores().get(0).nome();

            this.anoNascimentoAutor = dadosLivro.autores().get(0).anoNascimento();
            this.anoFalecimentoAutor = dadosLivro.autores().get(0).anoFalecimento();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }


    public Integer getAnoNascimentoAutor() {
        return anoNascimentoAutor;
    }

    public void setAnoNascimentoAutor(Integer anoNascimentoAutor) {
        this.anoNascimentoAutor = anoNascimentoAutor;
    }

    public Integer getAnoFalecimentoAutor() {
        return anoFalecimentoAutor;
    }

    public void setAnoFalecimentoAutor(Integer anoFalecimentoAutor) {
        this.anoFalecimentoAutor = anoFalecimentoAutor;
    }

    @Override
    public String toString(){
        return "Livro: " + titulo + " | Autor: " + autor + " | Idioma: " + idioma;
    }
}