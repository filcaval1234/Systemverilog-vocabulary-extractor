/**
 * A classe NodeSintax encapsula os dados necessarios para um nó que gaurda somente
 * sintax de SystemVerilog
 */
package avltree;

/**
 *
 * @author fc.corporation
 */
public class NodeSintax{
    private String dataSintax;
    NodeSintax left;
    NodeSintax right;
    int bFactor;
    static public final int LEFT_HIGH = 1;
    static public final int EQUAL_HIGH = 0;
    static public final int RIGHT_HIGH = -1;
    
    /**
     * O construtor da classe recebe um argumento para inicializar o campo data
     * @param dataSintax palavra reservada que será salva
     */
    NodeSintax(String dataSintax){
        this.dataSintax = dataSintax;
    }
    public void setLeft(NodeSintax l){
        this.left = l;
    }
    public void setRight(NodeSintax r){
        this.right = r;
    }
    /**
     * O método getDataSintax retorna a palavra salva no campo data
     * @return uma String que é a plavra reservada
     */
    public String getDataSintax(){
        return this.dataSintax;
    } 
}