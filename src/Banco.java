package src;

import java.util.ArrayList;

/**
 *
 * @author Luis Rodriguez
 */
public class Banco {
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Transaccion> txs;

    public Banco() {
        this.clientes = new ArrayList<>();
        this.txs = new ArrayList<>();
    }
    
    /**
     *
     * @param attr
     * @return Object
     */
    public ArrayList getter(int attr){
        switch(attr){
            case 1:
                return this.clientes;//1
            case 2:
                return this.txs;// 2
            default:
                return null;
        }
    }
    
    /**
     *
     * @param cliente
     */
    public void addCliente(Cliente cliente){
        this.clientes.add(cliente);
    }
    
    /**
     *
     * @param tx
     */
    public void regTx(Transaccion tx){
        this.txs.add(tx);
    }
}
