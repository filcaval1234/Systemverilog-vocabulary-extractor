/**
 * A classe AvlTreeSintax encapsula os dados e os métodos necessários para uma
 * árvore que salvará a sintax de SystemVerilog tendo métodos praticamente idententicos
 * ao da estrutura AvlTree mudando apenas seus retornos e suas assinaturas.
 */
package avltree;

/**
 *
 * @author fc.corporation
 */
public class AvlTreeSintax{
    private NodeSintax root;
    
    /**
     * O método public avlInsert recebe um argumento que é o dado que será inserido
     * na árvore e chama a função private avlInsert passando a raiz da árvore.
     * @param data dado que será inserido no nó.
     */
    public void avlInsert(String data){
        root = this.avlInsert(data, root).getNoSintax();
    }
    /**
     * O método private avlInsert é muito semelhante ao método da estrutura AvlTree;
     * É quem realmente insere um novo nó na árvore do tipo AvlTreeSintax,
     * ao inserir um novo nó é verificado se a árvore continua balanceada, caso não esteja 
     * é chamada a operação de balanceamento da árvore também retorna um objeto do tipo
     * NodeTaller
     * @param data dado que será inserido
     * @param root raiz ou sub-raiz
     * @return NodeTaller que é um bollean(taller) e o Node(no) que foi inserido na árvore
     */
    private NodeTallerSintax avlInsert(String data, NodeSintax root){
        boolean taller = false;
        if(root == null){
            root = new NodeSintax(data);
            taller = true;
            NodeTallerSintax nodeTaller = new NodeTallerSintax(root, taller);
        }
        else if(root.getDataSintax().compareTo(data) > 0){
            NodeTallerSintax tempNo = this.avlInsert(data, root.left);
            root.left = tempNo.getNoSintax();
            taller = tempNo.getTallerSintax();
            if(taller){
                if(root.bFactor == Node.LEFT_HIGH){
                    root = this.avlLeftBalance(root);
                    taller = false;
                }
                else if(root.bFactor == Node.EQUAL_HIGH){
                    root.bFactor = Node.LEFT_HIGH;
                    taller = true;
                }
                else{
                    root.bFactor = Node.EQUAL_HIGH;
                    taller = false;
                }    
            }
        }
        else if(root.getDataSintax().compareTo(data) < 0){
            NodeTallerSintax tempNo = this.avlInsert(data, root.right);
            root.right = tempNo.getNoSintax();
            taller = tempNo.getTallerSintax();
            if(taller){
                if(root.bFactor == Node.LEFT_HIGH){
                    root.bFactor = Node.EQUAL_HIGH;
                    taller = false;
                }
                else if(root.bFactor == Node.EQUAL_HIGH){
                    root.bFactor = Node.RIGHT_HIGH;
                    taller = true;
                }
                else{
                    root = this.avlRightBalance(root);
                    taller = false;
                }
            }
        }
        return new NodeTallerSintax(root, taller);
    }
    /**
     * O método rotacaoSimplesDireita rotaciona o pivo para a direita
     * @param pivo nó que será rotacionado
     * @return c após a rotação de pivo
     */
    private NodeSintax rotacaoSimplesDireita(NodeSintax pivo){
        NodeSintax c = pivo.left;
        pivo.left = c.right;
        c.right = pivo;
        return c;
    }
    /**
     * O método rotacaoSimplesEsquerda rotaciona o pivo para a esquerda
     * @param pivo nó que será rotacionado
     * @return c após a rotação de pivo
     */
    private NodeSintax rotacaoSimplesEsquerda(NodeSintax pivo){
        NodeSintax c = pivo.right;
        pivo.right = c.left;
        c.left = pivo;
        return c;
    }
    /**
     * O método avlRightBalance realiza uma rotação simples para a direita ou 
     * uma rotação dupla primeiro para a direita e depois para a esquerda.
     * @param pivo nó que será rotacionado
     * @return pivo após a rotação
     */
    private NodeSintax avlRightBalance(NodeSintax pivo){
        NodeSintax c = pivo.right;
        if(c.bFactor == Node.RIGHT_HIGH){
            pivo.bFactor = Node.EQUAL_HIGH;
            c.bFactor = Node.EQUAL_HIGH;
            pivo = this.rotacaoSimplesEsquerda(pivo);
        }
        else{
            NodeSintax grandChild = grandChild(pivo.right, false);
            if(grandChild.bFactor == Node.LEFT_HIGH){
                pivo.bFactor = Node.EQUAL_HIGH;
                c.bFactor = Node.RIGHT_HIGH;
            }
            else if(grandChild.bFactor == Node.EQUAL_HIGH){
                pivo.bFactor = Node.EQUAL_HIGH;
                c.bFactor = Node.EQUAL_HIGH;
            }
            else { //grandChild == right
                pivo.bFactor = Node.LEFT_HIGH;
                c.bFactor = Node.EQUAL_HIGH;
            }
            pivo.right = this.rotacaoSimplesDireita(pivo.right);
            pivo = this.rotacaoSimplesEsquerda(pivo);
        }
        return pivo;
    }
    /**
     * O método avlLeftBalance realiza uma rotação simples para a esquerda ou uma
     * rotação dupla primeiro para a esquerda e depois para a direita
     * @param pivo nó que será rotacionado
     * @return pivo após ser rotacionado
     */
    protected NodeSintax avlLeftBalance(NodeSintax pivo){
        NodeSintax c = pivo.left;
        if(c.bFactor == Node.LEFT_HIGH){
            pivo.bFactor = Node.EQUAL_HIGH;
            c.bFactor = Node.EQUAL_HIGH;
            pivo = this.rotacaoSimplesDireita(pivo);
            return pivo;
        }
        else{
            NodeSintax grandChild = grandChild(pivo.left, true);
            if(grandChild.bFactor == Node.LEFT_HIGH){
                pivo.bFactor = Node.RIGHT_HIGH;
                c.bFactor = Node.EQUAL_HIGH;
                pivo = this.rotacaoSimplesEsquerda(pivo);
            }
            else if(grandChild.bFactor == Node.EQUAL_HIGH){
                pivo.bFactor = Node.EQUAL_HIGH;
                c.bFactor = Node.EQUAL_HIGH;
            }
            else {//grandChild == Node.RIGHT_HIGH
                pivo.bFactor = Node.EQUAL_HIGH;
                c.bFactor = Node.LEFT_HIGH;
            }
            grandChild.bFactor = Node.EQUAL_HIGH;
            pivo.left = this.rotacaoSimplesEsquerda(pivo.left);
            pivo = this.rotacaoSimplesDireita(pivo);
        }
        return pivo;
    }
    /**
     * O método grandChild retorna o neto de determindado nó passando seu filho
     * @param filho filho do nó avô
     * @param left_right se true retorna o nó á direita se false retorna o nó a esquerda
     * @return o nó neto
     */
    // left = false
    // right = true
    public NodeSintax grandChild(NodeSintax filho, boolean left_right){
        NodeSintax grandChild = null;
        if(filho.left != null && left_right == false)
            grandChild =  filho.left;
        else if(filho.right != null && left_right == true)
            grandChild = filho.right;
        return grandChild;
    }
    
    /**
     * O método public busca recebe um argumento(data) e chama o método private busca
     * e passa data e a raiz da árvore
     * @param Data palavra que será buscada
     * @return true se a palavra estiver na árvore false se nao estiver
     */
    public boolean busca(String Data){
        return this.busca(Data, root);
    }
    /**
     * O método private busca recebe dois arguementos e faz a busca propriamente dito
     * retornando true se a palavra estiver na árvore ou false caso não esteja
     * @param word palavra que será perquisada na árvore
     * @param root raiz ou sub-raiz da árvore
     * @return true true se a palavra estiver na árvore false se nao estiver
     */
    public boolean busca(String word, NodeSintax root){
        boolean data = false;
        if(root != null){
            if(root.getDataSintax().compareTo(word) > 0){
                data = this.busca(word, root.left);
            }
            else if(root.getDataSintax().compareTo(word) < 0){
                data = this.busca(word, root.right);
            }
            else return true;
        }
        return data;
    }
    public void imprime(){
        this.imprime(this.root);
    }
    private void imprime(NodeSintax root){
        if(root != null){
            this.imprime(root.left);
            System.out.println(root.getDataSintax());
            this.imprime(root.right);
        }
    }          
}