package io.github.paulooosf.gameboxed.model;

import io.github.paulooosf.gameboxed.Enum.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_cd_id")
    private Long id;

    @Column(name = "usu_txt_usuario", length = 25, unique = true, nullable = false)
    private String usuario;

    @Column(name = "usu_txt_email", length = 70, nullable = false)
    private String email;

    @Column(name = "usu_txt_senha", length = 25, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "usu_txt_role", length = 25, nullable = false)
    private Role role;

    public Usuario() {
    }

    public Usuario(Long id, String usuario, String email, String senha, Role role) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
