package dw.futebol.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "jogador")

public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod_jogador;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false)
    private Date data;

    public Jogador(String nome, String email, Date data) {
        this.nome = nome;
        this.email = email;
        this.data = data;
    }

    public int getCod_jogador() {
        return cod_jogador;
    }

    public void setCod_jogador(int cod_jogador) {
        this.cod_jogador = cod_jogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Jogador{" + "cod_jogador=" + cod_jogador + ", nome=" + nome + ", email=" + email + ", data=" + data + '}';
    }
}
