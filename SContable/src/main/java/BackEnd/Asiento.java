package BackEnd;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="asientos")


public class Asiento implements Serializable {
    @Id
    @Column(name="idNumero")
    private int numero;
    @ElementCollection
    @JoinColumn(name="codigoCuenta")
    private List<Cuenta> listaCuentas = new ArrayList<>();
    @Column(name="fecha")
    private Date fecha;
    @ElementCollection
    @MapKeyJoinColumn(name="CodigoValores")
    @CollectionTable(name="cuentaValor", joinColumns=@JoinColumn(name="id_asiento"))
    private Map<Cuenta, Double> valores = new Hashtable<>();
    @Column(name="detalle")
    private String detalle;


    public Asiento() {

    }

    public String toString() {
        return "Asiento N°" + this.numero + "-- Fecha :" + this.fecha.getMonth()+"/"+this.fecha.getYear() +  " " + this.detalle;
    }

    public Asiento(int numero, Date fecha, List<Cuenta> cuentas,String descripcion) {
        this.listaCuentas = cuentas;
        this.numero = numero;
        this.fecha = fecha;
        this.detalle=descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getNumero() {
        return numero;
    }


    public List<Cuenta> getListaCuentas() {
        return new ArrayList<>(this.listaCuentas);
    }


    public void addValores() {
        boolean flag=false;

        while (!flag){
            Scanner input = new Scanner(System.in);
            double valor; //setear el dinero
            int numeroCuenta;//setear el numero de cuenta
            System.out.println("Ingrese el numero de la cuenta: ");
            numeroCuenta = input.nextInt();
            //verificacion de nro de cuenta
            int sumadorCuenta=0;
            for(Cuenta c:listaCuentas){
                sumadorCuenta++;
                if (c.getCodigoCuenta()==numeroCuenta){
                    if(c.isImputable()){
                        System.out.println("Ingrese el saldo: ");
                        valor = input.nextInt();
                        Double value = Double.valueOf(valor);
                        if (!valores.containsKey(c)) {
                            valores.put(c, value);
                            break;
                        } else {
                            Double diferencia = valores.get(c);
                            valores.put(c, diferencia + value);
                            break;
                        }
                    }else{
                        System.out.println("La cuenta no es imputable");
                        break;
                    }
                }else if(sumadorCuenta==listaCuentas.size()) {
                    System.out.println("La cuenta no existe");
                    break;
                }
            }
            if (balance()==0) {
                Scanner condicion = new Scanner(System.in);
                System.out.println("Desea seguir Y/N");
                String cond = condicion.nextLine();
                if(cond.toLowerCase().contains("n")) {
                    flag = true;
                }
            }
        }
    }

    public double balance(){
        double balanceGral=0;
        Imputable str;
        Set<Cuenta> key=valores.keySet();
        Iterator<Cuenta> itr=key.iterator();
        while(itr.hasNext()){
            str= (Imputable) itr.next();
            if(str.getTipo()=='d'){
                balanceGral+=valores.get(str);
            }else{
                balanceGral-=valores.get(str);
            }
        }
        return balanceGral;
    }
    public Map<Cuenta,Double> getListadoLibroDiario(){
        return this.valores;
    }

}
