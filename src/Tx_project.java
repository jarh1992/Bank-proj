package src;
import java.util.ArrayList;
import java.util.Scanner;

public class Tx_project {
    
    private String input;
    private static Scanner leer;
    private Banco banco;
    public static byte Edad_max = 0, Edad_min = 100;

    public Tx_project() {
        this.leer = new Scanner(System.in);
        this.banco = new Banco();
    }
    
    public static void main(String[] args) {
        Tx_project txproj = new Tx_project();
        System.out.println("Banco PB");
        boolean flag = true;
        while (flag){
            System.out.println("Ingrese la opcion a realizar:\n"
                    + "[1] Agregar cliente\n"
                    + "[2] Realizar transaccion\n"
                    + "[3] Ver número de transacciones de un cliente\n"
                    + "[4] Ver transaccion realizada por un cliente\n"
                    + "[5] Ver clientes con retiros > 1M\n"
                    + "[6] Ver cuenta y datos de cliente con mayor edad\n"
                    + "[7] Ver cuenta y datos de cliente con menor edad y saldo > 5M\n");

            txproj.input = leer.next();
            int opcion = Integer.parseInt(txproj.input);
            switch(opcion){
                case 1:
                    txproj.agregarCliente();
                    break;
                case 2:
                    txproj.realizarTx();
                    break;
                case 3:
                    txproj.VerNumTxCli();
                    break;
                case 4:
                    txproj.verTpTxCli();
                    break;
                case 5:
                    txproj.verCliRetMay1M();
                    break;
                case 6:
                    txproj.verCtaDatCliMayEdad();
                    break;
                case 7:
                    txproj.verCteMnoredadMr5M();
                    break;
                default:
                    System.out.println("opcion no valida");
            }
            System.out.println("Desea realizar otra accion?: s/n");
            txproj.input = leer.next();
            if(txproj.input.equals("n")){
                flag = false;
            }
        }
    }
    
    public void agregarCliente(){
        System.out.println("Ingrese identificacion");
        String id = leer.next();
        if (buscarCliente(id)==null){
            System.out.println("Ingrese primer y/o segundo nombre");
            String nom = leer.next();
            System.out.println("Ingrese apellido");
            String apel = leer.next();
            System.out.println("Ingrese direccion");
            String dir = leer.next();
            System.out.println("Ingrese edad");
            this.input = leer.next();
            byte edad = Byte.parseByte(this.input);
            System.out.println("Numero de la cuenta");
            String id_cuenta = leer.next();
            System.out.println("Ingrese saldo de la cuenta");
            this.input = leer.next();
            double saldo = Double.parseDouble(this.input);
            Cuenta c = new Cuenta(id_cuenta, saldo);
            Cliente cli = new Cliente(id, nom, apel, dir, edad, c);
            
            if (this.Edad_max < (byte)cli.getter(5)) {
                this.Edad_max = (byte)cli.getter(5);
            }
            
            if (this.Edad_min > (byte)cli.getter(5)) { 
                this.Edad_min = (byte)cli.getter(5);
            }
            
            this.banco.addCliente(cli);
        }else{
            System.out.println("Cliente existente");
        }
            
    }
    
    public void realizarTx(){
        System.out.println("Ingrese id de cliente");
        String id_cli = leer.next();
        if (buscarCliente(id_cli) != null){
            Cliente cli = buscarCliente(id_cli);
            Cuenta cuenta = (Cuenta) cli.getter(6);
            System.out.println("Ingrese id de tx");
            String id_tx = leer.next();
            System.out.println("Ingrese tipo de tx:\n"
                    + "[1]retiro\n"
                    + "[2]consignacion\n");
            this.input = leer.next();
            String opc = this.input;
            boolean tipo;
            boolean flag = true;
            while (flag){
                System.out.println("Ingrese valor a transar");
                this.input = leer.next();
                double val = Double.parseDouble(this.input);            
                Transaccion tx;
                if (val>0){
                    switch(opc){
                        case "1":
                            if(val<=(double)cuenta.getter(2)){
                                tipo = true;
                                cuenta.setter(2, (double)cuenta.getter(2)-val);
                                tx = new Transaccion(id_tx, (String)cuenta.getter(1), id_cli, tipo, val);
                                this.banco.regTx(tx);
                                flag = false;
                            }
                            break;
                        case "2":
                            tipo = false;
                            cuenta.setter(2, (double)cuenta.getter(2)+val);
                            tx = new Transaccion(id_tx, (String)cuenta.getter(1), id_cli, tipo, val);
                            this.banco.regTx(tx);
                            flag = false;
                            break;
                        default:
                            System.out.println("ocurrió un error");
                    }
                }                
            }
        }else{
            System.out.println("Cliente no existe");
        }
    }
    
    public void VerNumTxCli(){
        System.out.println("Ingrese id de cliente");
        this.input = leer.next();
        int aux = 0;
        ArrayList<Transaccion> atx = this.banco.getter(2);
        for (Transaccion atx1 : atx) {
            if (atx1.getter(3).equals(this.input)) {
                aux+=1;
            }
        }
        Cliente cli = buscarCliente(this.input);
        System.out.println("El cliente "+cli.getter(2)+" "+cli.getter(3)+" hizo "+ aux + " transacciones");
    }
    
    public void verTpTxCli(){
        System.out.println("Ingrese id de cliente");
        String id_cli = leer.next();
        if (buscarCliente(id_cli) != null){
            Cliente cli = buscarCliente(id_cli);
            if(buscarTransacionPorCli(id_cli)!= null){
                ArrayList<Transaccion> atx = buscarTransacionPorCli(id_cli);

                int aux = 0;
                int aux2 = 0;
                for (Transaccion atx1 : atx) {
                    if (Boolean.parseBoolean(atx1.getter(4)) == true){
                        aux+=1;
                    }else if (Boolean.parseBoolean(atx1.getter(4)) == false){
                        aux2+=1;
                    }
                }
                System.out.println("Número de retiros: "+aux);
                System.out.println("Número de consignaciones: "+aux2);
            }
        }else{
            System.out.println("Cliente no existe");
        }         
    }
    
    public void verCliRetMay1M() {
        ArrayList<Transaccion> atx = this.banco.getter(2);
        //ArrayList<Transaccion> atxm1M = new ArrayList<>();
        String id = "";
        for (Transaccion atx1 : atx) {
            if(Double.parseDouble(atx1.getter(5)) >= 1000000){
                id = atx1.getter(3);
                System.out.println(buscarCliente(id).getter(2)+" "
                        + buscarCliente(id).getter(3));
            }
        }
    }
    
    public void verCtaDatCliMayEdad(){
        
        boolean pass = false;
        ArrayList<Cliente> clis = this.banco.getter(1); // saca cliente
        Cuenta cuenta;
        System.out.println("...:::DATOS DEL CLIENTE DE MAYOR EDAD:::...");
        for(Cliente cli1: clis){
            cuenta = (Cuenta)cli1.getter(6);
            if((byte)cli1.getter(5)== Edad_max)
            {
                System.out.println("--- DATOS PERSONALES ---");
                System.out.println("Nombes Y Apellidos: "+cli1.getter(2)+" "+cli1.getter(3)); 
                System.out.println("ID Del Cliente: "+cli1.getter(1));
                System.out.println("Direccion Del Cliente: "+cli1.getter(4));
                System.out.println("Edad Del Cliente: "+cli1.getter(5));
                     System.out.println("Saldo De La Cuenta"+cuenta.getter(2));
                pass = true;
            }
                
        }
        
        if (!pass) {
            System.out.println("NO SE HA INGRESADO NINGUN CLIENTE");
        }
    }
    
    public void verCteMnoredadMr5M(){
        boolean flag = false;
        ArrayList<Cliente> clis = this.banco.getter(1);
          System.out.println("...:::DATOS DEL CLIENTE DE MENOR EDAD Y CON SALDO > 5M:::...");
          Cuenta cuenta;
        for(Cliente cli1: clis){
            cuenta = (Cuenta)cli1.getter(6);
            if((byte)cli1.getter(5)== Edad_min && (double)cuenta.getter(2)>5000000)
            {
                System.out.println("--- DATOS PERSONALES ---");
                System.out.println("Nombes Y Apellidos: "+cli1.getter(2)+" "+cli1.getter(3)); 
                System.out.println("ID Del Cliente: "+cli1.getter(1));
                System.out.println("Direccion Del Cliente: "+cli1.getter(4));
                System.out.println("Edad Del Cliente: "+cli1.getter(5));
                System.out.println("Saldo De La Cuenta"+cuenta.getter(2));
                flag = true;
             
            }
                
        }if (!flag) {
            System.out.println("No hay cliente de menor edad que tenga mas de 5 M");
        }
        
        
    }
    public Cliente buscarCliente(String id){
        ArrayList<Cliente> acli = this.banco.getter(1);
        for (Cliente acli1 : acli) {
            if (acli1.getter(1).equals(id)) {
                return acli1;
            }
        }
        return null;
    }
    
    public Transaccion buscarTransaccion(String id_tx){
        ArrayList<Transaccion> atx = this.banco.getter(2);
        for (Transaccion atx1 : atx) {
            if (atx1.getter(1).equals(id_tx)) {
                return atx1;
            }
        }
        return null;
    }
    
    public ArrayList buscarTransacionPorCli(String id_cli){
        ArrayList<Transaccion> atx = this.banco.getter(2);
        ArrayList<Transaccion> atxn = new ArrayList<>();
        for (Transaccion atx1 : atx) {
            if (atx1.getter(3).equals(id_cli)) {
                atxn.add(atx1);
            }
        }
        if(!atxn.isEmpty())
            return atxn;
        else
            return null;
    }
    
}
