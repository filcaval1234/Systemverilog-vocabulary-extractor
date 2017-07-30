/**
 * A classe Node encapsula os dados necessarios para um nó de uma estrutura de
 * dados que é uma árvore avl
 */
package avltree;

/**
 *
 * @author fc.corporation
 */
public class Node {
    protected double key;
    Node left;
    Node right;
    private AvlTreeSintax data;
    int bFactor;
    static public final int LEFT_HIGH = 1;
    static public final int EQUAL_HIGH = 0;
    static public final int RIGHT_HIGH = -1;
    
    /**
     * O construtor da classe recebe dois argumentos um double e um objeto 
     * do tipo AvlTreeSintax
     * @param key chave de acesso ao nó
     * @param data sub-árvore que será o dado do nó
     */
    Node(double key, AvlTreeSintax data){
        this.data = data;
        this.key = key;
        this.bFactor = EQUAL_HIGH;
    }
    /**
     * O construtor da classe que recebe só um argumento que é a chave de acesso
     * ao nó
     * @param key 
     */
    Node(double key){
        this.key = key;
    }
    public void setLeft(Node l){
        this.left = l;
    }
    public void setRight(Node r){
        this.right = r;
    }
    public double getKey(){
        return this.key;
    }
    public AvlTreeSintax getData(){
        return data;
    }
}