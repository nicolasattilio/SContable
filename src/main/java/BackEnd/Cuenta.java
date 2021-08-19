package BackEnd;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="CUENTA_TYPE")
@Table(name="CUENTAS")


public abstract class Cuenta {
    @Id
    @Column(name="COD_CUENTA")
    private int codigoCuenta;
    @Column(name="NOMBRE")
    private String nombre;
    @Column(name="IMPUTABLE")
    private boolean isImputable;


    public Cuenta() {

    }

    public Cuenta(int codigo, String nombre,boolean isImputable) {
        this.codigoCuenta=codigo;
        this.nombre=nombre;
        this.isImputable=isImputable;
    }



    public String toString(){
        return "\n |Codigo: "+this.codigoCuenta+" | Nombre: "+this.nombre;

    }

    public boolean isImputable() {
        return isImputable;
    }
    public abstract boolean isAjusta();
    public abstract int cantCuentas();
    public int getCodigoCuenta(){
        return this.codigoCuenta;
    }
    public abstract List<Cuenta> getCuentaImputable();
    public abstract char getTipo();
    public String getNombre(){
        return this.nombre;
    }
    public abstract List<Cuenta> getCuenta();

    protected void setNoImputable(NoImputable noImputable) {
    }
}
