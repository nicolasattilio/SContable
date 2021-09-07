package model;

import javax.persistence.*;

@Entity
@Table(name="PERFIL")
public class Perfil {
    @Id
    @Column(name="LOGIN")
    private String login;
    @Column(name="PASS")
    private String pass;
    @Column(name="NIVEL")
    private int nivel;

    public Perfil(String login,String pass,int nivel){
        this.login=login;
        this.pass=pass;
        this.nivel=nivel;
    }

    public Perfil() {

    }

    @Override
    public String toString() {
        return "Perfil{" +
                "login='" + login + '\'' +
                ", pass=" + pass +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public int getNivel() {
        return nivel;
    }
}
