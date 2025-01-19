package io.github.paulooosf.gameboxed.model;

import io.github.paulooosf.gameboxed.dto.AvaliacaoEntradaDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ava_cd_id")
    private Long id;

    @Column(name = "ava_txt_comentario", length = 500)
    private String comentario;

    @Column(name = "ava_dp_nota", nullable = false)
    private Double nota;

    @OneToOne
    @JoinColumn(name = "ava_fk_usu", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ava_fk_jog", nullable = false)
    private Jogo jogo;

    public Avaliacao() {
    }

    public Avaliacao(Long id, String comentario, Double nota, Usuario usuario, Jogo jogo) {
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
        this.usuario = usuario;
        this.jogo = jogo;
    }

    public Avaliacao(AvaliacaoEntradaDTO avaliacao) {
        this.comentario = avaliacao.comentario();
        this.nota = avaliacao.nota();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}
