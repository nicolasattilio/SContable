package model;

import javax.persistence.*;

@Entity
@Table(name="PERFIL")
public class Perfil {
    @Id
    @Column(name="ID_PERFIL")
    private int id;
    @Column(name="LOGIN")
    private String login;
    @Column(name="PASS")
    private int pass;

    public Perfil(int id,String login,int pass){
        this.id=id;
        this.login=login;
        this.pass=pass;
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

    public int getPass() {
        return pass;
    }
}
