package src;


public class Transaccion {
    private String id;
    private String num_cuenta;
    private String id_cliente;
    private boolean tipo_tx;    //true:retiro, false:consignacion
    private double val_tx;

    public Transaccion(String id, String num_cuenta, String id_cliente, boolean tipo_tx, double val_tx) {
        this.id = id;
        this.num_cuenta = num_cuenta;
        this.id_cliente = id_cliente;
        this.tipo_tx = tipo_tx;
        this.val_tx = val_tx;
    }
    
    public String getter(int attr) {
        switch(attr){
            case 1:
                return this.id;
            case 2:
                return this.num_cuenta;
            case 3:
                return this.id_cliente;
            case 4:
                return Boolean.toString(this.tipo_tx);
            case 5:
                return Double.toString(this.val_tx);
            default:
                return "no existe atributo";
        }
    }

    public void setter(int attr, String val) {
        switch(attr){
            case 1:
                this.id = val;
            case 2:
                this.num_cuenta = val;
            case 3:
                this.id_cliente = val;
            case 4:
                this.tipo_tx = Boolean.parseBoolean(val);
            case 5:
                this.val_tx = Double.parseDouble(val);
            default:
                System.out.println("no existe atributo");
        }
    }
}
