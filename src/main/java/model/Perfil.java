package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    @JoinColumn(name="EMPRESASPERFIL")
    private List<Empresa> empresas=new ArrayList<>();
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
    public void addEmpresa(Empresa e){
        this.empresas.add(e);
    }


    public void updateEmpresa(Empresa e) {
        for (Empresa emp:empresas)
            if (e.getCuit()==emp.getCuit()){
                emp.setValores(e);
            }
    }
}
