package src;

/**
 *
 * @author Luis Rodriguez
 */
public class Cliente {
    private String id;    //1
    private String nom; //2
    private String apel;    //3
    private String dir; //4
    private byte edad;  //5 
    private Cuenta cuenta;  //6
    
    /**
     *
     * @param id
     * @param nom
     * @param apel
     * @param dir
     * @param edad
     * @param cuenta
     */
    public Cliente(String id, String nom, String apel, String dir, byte edad, Cuenta cuenta){
        this.id = id;
        this.nom = nom;
        this.apel = apel;
        this.dir = dir;
        this.edad = edad;
        this.cuenta = cuenta;
    }

    /**
     *
     * @param attr
     * @return Object
     */
    public Object getter(int attr) {
        switch(attr){
            case 1:
                return this.id;
            case 2:
                return this.nom;
            case 3:
                return this.apel;
            case 4:
                return this.dir;
            case 5:
                return this.edad;
            case 6:
                return this.cuenta;
            default:
                return "ocurrió un error";
        }        
    }

    /**
     *
     * @param attr
     * @param val
     */
    public void setter(int attr, Object val) {
        switch(attr){
            case 1:
                this.id = (String)val;
                break;
            case 2:
                this.nom = (String)val;
                break;
            case 3:
                this.apel = (String)val;
                break;
            case 4:
                this.dir = (String)val;
                break;
            case 5:
                this.edad = (byte)val;
                break;
            case 6:
                this.cuenta = (Cuenta)val;
                break;
            default:
                System.out.println("ocurrió un error");
        }
    }    
}
