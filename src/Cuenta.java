package src;


public class Cuenta {
    private String id;    //1
    private double saldo;   //2

    public Cuenta(String id, double saldo) {
        this.id = id;
        this.saldo = saldo;
    }
    
    public Object getter(int attr){
        if (attr == 1){
            return this.id;
        }else if (attr == 2){
            return this.saldo;
        }
        return "ocurrió un error";
    }
    
    public void setter(int attr, Object val){
        if (attr == 1){
            this.id = (String)val;
        }else if (attr == 2){
            this.saldo = (double)val;
        }
    }
}
