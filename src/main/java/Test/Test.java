package Test;

import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Test {
    private static EntityManager manager;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SContable"); ;
    public static void main(String [] args){
        manager=emf.createEntityManager();
        Empresa emp=new Empresa(1,"Disney","Calle falsa 123","Springfiled","2292");
        Imputable i1=new Imputable(100001,"Banco Provincia",true,'d');
        Imputable i2=new Imputable(100002,"Banco Provincia",true,'h');
        Imputable i3=new Imputable(100003,"Banco Provincia",true,'d');
        Imputable i4=new Imputable(100004,"Banco Provincia",true,'h');
        NoImputable ni1=new NoImputable(100000,"Caja");
        NoImputable ni2=new NoImputable(200000,"Caja fija");
        ni1.addCuenta(i1);
        ni1.addCuenta(i2);
        ni2.addCuenta(i3);
        ni2.addCuenta(i4);

        emp.addCuenta(ni1);
        emp.addCuenta(ni2);
        Date d1=new Date(2021,05,1);
        Date d3=new Date(3921,06,10);
        Date d2=new Date(2021,10,31);
        Date d4=new Date(3921,9,10);
        Ejercicio ej=new Ejercicio(1,d1,d2);
        Asiento a1=new Asiento(1,d1,emp.getPlanCuentas(),"testttt");
        //a1.addValores();
        Asiento a2=new Asiento(2,d1,emp.getPlanCuentas(),"jeje");
        //a2.addValores();
        ej.addAsiento(a1);
        ej.addAsiento(a2);
        emp.addEjercicio(ej);
        //System.out.println(emp);

        Date enero=new Date(2021,1,1);
        Date febrero=new Date(2021,2,1);
        Date marzo=new Date(2021,3,1);
        Date abril=new Date(2021,4,1);
        Date mayo=new Date(2021,5,1);
        Date junio=new Date(2021,6,1);
        Date julio=new Date(2021,7,1);
        Date agosto=new Date(2021,8,1);
        Date septiembre=new Date(2021,9,1);
        Date octubre=new Date(2021,10,1);
        Date noviembre=new Date(2021,11,1);
        Date diciembre=new Date(2021,12,1);
        MesAjustado ajustado=new MesAjustado();
        ajustado.addValores(enero,1.5);
        ajustado.addValores(febrero,1.7);
        ajustado.addValores(marzo,1.8);
        ajustado.addValores(abril,2.0);
        ajustado.addValores(mayo,1.3);
        ajustado.addValores(junio,1.5);
        ajustado.addValores(julio,1.1);
        ajustado.addValores(agosto,1.6);
        ajustado.addValores(septiembre,1.3);
        ajustado.addValores(octubre,1.75);
        ajustado.addValores(noviembre,1.6);
        ajustado.addValores(diciembre,1.3);


        manager.getTransaction().begin();
        /*manager.persist(i1);
        manager.persist(i2);
        manager.persist(i3);
        manager.persist(i4);
        manager.persist(ni1);
        manager.persist(ni2);
        manager.persist(a1);
        manager.persist(a2);
        manager.persist(ej);
        manager.persist((emp));
        manager.persist(ajustado);*/
        Empresa empr=manager.find(Empresa.class,1);

        System.out.println(empr.getEjercicios());

        //empr.imprimirMayores(empr.gestionMayores(d3,d4,true,i2,ajustado));


        manager.getTransaction().commit();
        manager.close();





    }
}
