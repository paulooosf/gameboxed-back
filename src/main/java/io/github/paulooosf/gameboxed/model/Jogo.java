package io.github.paulooosf.gameboxed.model;

import io.github.paulooosf.gameboxed.dto.JogoEntradaDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JOGO")
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jog_cd_id")
    private Long id;

    @Column(name = "jog_txt_nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "jog_txt_descricao", length = 500, nullable = false)
    private String descricao;

    @Column(name = "jog_txt_empresa", length = 50, nullable = false)
    private String empresa;

    @Column(name = "jog_int_ano", nullable = false)
    private Integer ano;

    @Column(name = "jog_dp_nota")
    private Double nota;

    @Column(name = "jog_int_quantidade_avaliacoes", nullable = false)
    private Integer quantidadeAvaliacoes;

    @Column(name = "jog_txt_link_capa", nullable = false)
    private String linkCapa;

    @Column(name = "jog_txt_link_banner", nullable = false)
    private String linkBanner;

    @Column(name = "jog_txt_link_trailer")
    private String linkTrailer;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Jogo() {
    }

    public Jogo(Long id, String nome, String descricao, String empresa, Integer ano, String linkCapa, String linkBanner,
                String linkTrailer) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.empresa = empresa;
        this.ano = ano;
        this.nota = 0.0;
        this.quantidadeAvaliacoes = 0;
        this.linkCapa = linkCapa;
        this.linkBanner = linkBanner;
        this.linkTrailer = linkTrailer;
        this.avaliacoes = new ArrayList<>();
    }

    public Jogo(JogoEntradaDTO jogo) {
        this.nome = jogo.nome();
        this.descricao = jogo.descricao();
        this.empresa = jogo.empresa();
        this.ano = jogo.ano();
        this.nota = 0.0;
        this.quantidadeAvaliacoes = 0;
        this.linkCapa = jogo.linkCapa();
        this.linkBanner = jogo.linkBanner();
        this.linkTrailer = jogo.linkTrailer();
        this.avaliacoes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public void setQuantidadeAvaliacoes(Integer quantidadeAvaliacoes) {
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
    }

    public String getLinkCapa() {
        return linkCapa;
    }

    public void setLinkCapa(String linkCapa) {
        this.linkCapa = linkCapa;
    }

    public String getLinkBanner() {
        return linkBanner;
    }

    public void setLinkBanner(String linkBanner) {
        this.linkBanner = linkBanner;
    }

    public String getLinkTrailer() {
        return linkTrailer;
    }

    public void setLinkTrailer(String linkTrailer) {
        this.linkTrailer = linkTrailer;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void editarJogo(Jogo jogo) {
        this.id = jogo.getId();
        this.nome = jogo.getNome();
        this.descricao = jogo.getDescricao();
        this.empresa = jogo.getEmpresa();
        this.ano = jogo.getAno();
        this.nota = jogo.getNota();
        this.quantidadeAvaliacoes = jogo.getQuantidadeAvaliacoes();
        this.linkCapa = jogo.getLinkCapa();
        this.linkBanner = jogo.getLinkBanner();
        this.linkTrailer = jogo.getLinkTrailer();
    }

    public void atualizarNota(Double nota) {
        this.nota = (this.nota * this.quantidadeAvaliacoes + nota) / (this.quantidadeAvaliacoes + 1);
        this.quantidadeAvaliacoes++;
    }
}
