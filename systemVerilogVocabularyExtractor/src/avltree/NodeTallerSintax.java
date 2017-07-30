/**
 * A classe NodeTallerSintax foi desenvolvida para contornar o obstáculo que JAVA impõem
 * que o retorno de apenas um valor na função AvlTreeSintax.avlInsert faz se necessario
 * retornar dois valores o nó que será inserido e um boolean relativo a altura da
 * árvore para o balanceamento da mesma.
 */ 
package avltree;

/**
 *
 * @author fc.corporation
 */
public class NodeTallerSintax {
    private NodeSintax no;
    private boolean taller;
    
    NodeTallerSintax(NodeSintax no, boolean taller){
        this.no = no;
        this.taller = taller;
    }
    public NodeSintax getNoSintax(){
        return this.no;
    }
    public boolean getTallerSintax(){
        return this.taller;
    }
}