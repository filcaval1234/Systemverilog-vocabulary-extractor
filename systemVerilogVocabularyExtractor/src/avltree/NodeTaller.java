/**
 * A classe NodeTaller foi desenvolvida para contornar o obstáculo que JAVA impõem
 * que o retorno de apenas um valor na função AvlTree.avlInsert faz se necessario
 * retornar dois valores o nó que será inserido e um boolean relativo a altura da
 * árvore para o balanceamento da mesma.
 */
package avltree;

/**
 *
 * @author fc.corporation
 */
public class NodeTaller {
    private Node no;
    private boolean taller;
    
    /**
     * O construtor da classe que recebe dois argumentos e inicializa os campos
     * no e taller
     * @param no nó que foi inserido na árvore 
     * @param taller valor usado para o balanceamento da árvore
     */
    NodeTaller(Node no, boolean taller){
        this.no = no;
        this.taller = taller;
    }
    public Node getNo(){
        return this.no;
    }
    public boolean getTaller(){
        return this.taller;
    }
}