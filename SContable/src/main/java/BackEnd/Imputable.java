package BackEnd;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity()
@DiscriminatorValue("I")
@Table(name="IMPUTABLE")


public class Imputable extends Cuenta implements Serializable {
    @Column(name="AJUSTA")
    private boolean ajusta;
    @Column(name="TIPO")
    private char tipo;


    public Imputable(int codigo,String nombre,boolean ajusta, char tipo){
        super(codigo,nombre,true);
        this.ajusta = ajusta;
        this.tipo = tipo;

    }

    public Imputable() {
    }



    public boolean isAjusta() {
        return ajusta;
    }

    public char getTipo() {
        return tipo;
    }

    @Override
    public int cantCuentas() {
        return 1;
    }

    @Override
    public List<Cuenta> getCuentaImputable() {
        List<Cuenta>cuentasImputables=new ArrayList<>();
        cuentasImputables.add(this);
        return cuentasImputables;
    }

    @Override
    public List<Cuenta> getCuenta() {
        List<Cuenta>cuenta=new ArrayList<>();
        cuenta.add(this);
        return cuenta;
    }
}
