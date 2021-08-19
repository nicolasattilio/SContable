package BackEnd;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity()
@DiscriminatorValue("NI")
@Table(name="NOIMPUTABLE")

public class NoImputable extends Cuenta implements Serializable {
    @ElementCollection
    @JoinColumn(name="codigoCuentaNoImputable")
    private List<Cuenta> cuentas=new ArrayList<>();

    public NoImputable(int codigo, String nombre) {
        super(codigo, nombre,false);
    }

    public NoImputable() {

    }

    @Override
    public boolean isAjusta() {
        return false;
    }

    @Override
    public int cantCuentas() {
        int cantidadSumaCuentas=0;
        for (Cuenta c:cuentas){
            cantidadSumaCuentas+=c.cantCuentas();
        }
        return cantidadSumaCuentas;
    }

    @Override
    public List<Cuenta> getCuentaImputable() {
        List<Cuenta>cuentasImputables=new ArrayList<>();
        for(Cuenta c: cuentas) {
            if (c.isImputable()) {
                cuentasImputables.add(c);
            }else{
                cuentasImputables.addAll(c.getCuentaImputable());
            }
        }
        return cuentasImputables;
    }


    @Override
    public char getTipo() {
        return 0;
    }


    @Override
    public List<Cuenta> getCuenta() {

        List<Cuenta> cuentasAux=new ArrayList<>();
        cuentasAux.add(this);
        for (Cuenta c:cuentas){
            cuentasAux.addAll(c.getCuenta());
        }
        return cuentasAux;
    }

    public void addCuenta(Cuenta c){
        c.setNoImputable(this);
        cuentas.add(c);
    }
}
