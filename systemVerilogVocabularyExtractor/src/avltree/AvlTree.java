/**
 * A classe AvlTree encapsula os dados e os métodos para a estrutura de dados
 * que é a árvore AVL, esta árvore será a árvore "corpo" e sua função é
 * guardar sub-árvores em cada um de seus nós, onde cada uma dessas sub-árvores
 * guardara as palavras da sintax SystemVerilog que iniciem com as mesmas letras
 */
package avltree;

/**
 *
 * @author fc.corporation
 */
public class AvlTree {
    protected Node root;
    
    /**
     * O Construtor vazio da classe 
     */
    public AvlTree(){}
    
   /**
    * O método getRoot retorna a raiz da árvore AVL
    * @return a raiz da árvore
    */
    public Node getRoot(){
        return this.root;
    }
    /**
     * O método public avlInsert recebe dois argumentos um double e um objeto do 
     * tipo AvlTreeSintax, daí chama o método private avlInsert passando
     * sempre a raiz da árvore, ou seja, this.root
     * @param key chave do nó 
     * @param data valor do campo data do nó
     */
    public void avlInsert(double key, AvlTreeSintax data){
        this.root = this.avlInsert(key, data, this.root).getNo();
    }
    /**
     * O método private avlInsert recebe três argumentos que são descritos abaixo
     * o funcionamento deste método é: inserir um novo nó e checar se a árvore
     * está ou não balanceada após a inserção deste nó, caso não esteja é realizado
     * a operação de balanceamento da árvore; Como pode ser observado este método
     * retorna um objeto do tipo NodeTaller o motivo disso é que JAVA não permite
     * que sejam retornados dois valores em um mesmo return daí criamos uma classe
     * que encapsula dois dados que queremos retornar que são NODE e TALLER, 
     * onde seus valores são descritos nos comentários da classe NodeTaller
     * @param key chave de acesso ao nó
     * @param data sub-árvore que será inserida
     * @param root raiz ou sub-raiz da árvore depende da recursividade
     * @return NodeTaller com um boolean(taller) e um Node(no)
     */
    private NodeTaller avlInsert(double key, AvlTreeSintax data, Node root){
        boolean taller = false;
        if(root == null){
            root = new Node(key, data);
            taller = true;
            NodeTaller nodeTaller = new NodeTaller(root, taller);
        }
        else if(key < root.getKey()){
            NodeTaller tempNo = this.avlInsert(key, data, root.left);
            root.left = tempNo.getNo();
            taller = tempNo.getTaller();
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
        else if(key > root.getKey()){
            NodeTaller tempNo = this.avlInsert(key, data, root.right);
            root.right = tempNo.getNo();
            taller = tempNo.getTaller();
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
        return new NodeTaller(root, taller);
    }
    /**
     * O método public removeNode nunca é utilizado neste projeto, porém não apagarei
     * posso precisar desta árvore para projetos futuros!!!
     * @param key chave do nó que será removido da árvore AVl
     */
    public void removeNode(int key){
        this.root = this.removeNode(key, this.root);
    }
    /**
     * O método private removeNode
     * @param key
     * @param root
     * @return 
     */
    private Node removeNode(int key, Node root){
        if(root != null){
            if(root.getKey() > key){
                root.left = this.removeNode(key, root.left);
            }
            else if(root.getKey() < key){
                root.right = this.removeNode(key, root.right);
            }
            //caso1 e caso2 juntos se root.left == null return root.right
            //se root.right == null return root.left
            if(root.getKey() == key){
                if(root.left == null){
                    Node tempNode = root.right;
                    root.right = null;
                    return tempNode;
                }
                else if(root.right == null){
                    Node tempNode = root.left;
                    root.left = null;
                    return tempNode;
                }
                //Caso 3 o nó escolhido escolhido tem dois filhos 
                //falta terminar o caso 3
            }
        }
        return root;
    }
    /**
     * O método public busca recebe um arbumento e chama o método private busca
     * passando a raiz da árvore
     * @param key chave do nó à ser buscado
     * @return uma sub-árvore se encontrado o nó ou null se não
     */
    public AvlTreeSintax busca(double key){
        return this.busca(key, this.root);
    }
    /**
     * O método private busca é quem faz a busca propriamente dito recebe dois
     * argumentos e todo seu funcionamento é RECURSIVO!!!! HEHEHE
     * @param key chave do nó à ser buscado
     * @param root raiz ou sub-raiz depende da recursividade
     * @return uma sub-árvore se encontrado o nó ou null se não
     */
    private AvlTreeSintax busca(double key, Node root){
        AvlTreeSintax data = new AvlTreeSintax();
        if(root != null){
            if(root.getKey() > key){
                data = this.busca(key, root.left);
            }
            else if(root.getKey() < key){
                data = this.busca(key, root.right);
            }
            else return root.getData();
        }
        return data;
    }
    /**
     * O método rotacaoSimplesDireita recebe um argumento que é o pivo, que será 
     * o nó que rotacionára; após a rotação o pivo será o filho à direita de c
     * c é o filho à esquerda de pivo
     * @param pivo nó que rotacionára
     * @return c que é o filho à esquerda de pivo após pivo ter rotacionado e virado filho à direita de c
     */
    private Node rotacaoSimplesDireita(Node pivo){
        Node c = pivo.left;
        pivo.left = c.right;
        c.right = pivo;
        return c;
    }
    /**
     * O método rotacaoSimplesEsquerda é muito similar a rotacaoSimplesDireita
     * diferenciando apenas que em vez de se tornar filho à direita de c tornrár-se
     * filho à esquerda de c
     * @param pivo nó que rotacionará para a esquerda
     * @return  c após a rotação de pivo
     */
    private Node rotacaoSimplesEsquerda(Node pivo){
        Node c = pivo.right;
        pivo.right = c.left;
        c.left = pivo;
        return c;
    }
    /**
     * O método private avlRightBalance recebe um argumento que é pivo e fara um dos dois
     * casos possivéis que este método realiza que é uma rotação simples para a esquerda
     * ou uma rotação dupla primeiro para a direita e depois para a esquerda
     * @param pivo nó que será rotacionado
     * @return pivo após um dos dois casos ter sido executados
     */
    private Node avlRightBalance(Node pivo){
        Node c = pivo.right;
        if(c.bFactor == Node.RIGHT_HIGH){
            pivo.bFactor = Node.EQUAL_HIGH;
            c.bFactor = Node.EQUAL_HIGH;
            pivo = this.rotacaoSimplesEsquerda(pivo);
        }
        else{
            Node grandChild = grandChild(pivo.right, false);
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
     * O método private avlLeftBalance é similar ao método avlRightBalance a diferença
     * é que as outras duas rotações possiveis que conpõem as rotações de uma árvore AVL
     * sendo elas uma rotação simples para a esquerda ou uma rotação dupla 
     * primeiro para a esquerda depois para a direita
     * @param pivo nó que será rotacionado
     * @return pivo após uma das duas situações acima ter sido executada
     */
    private Node avlLeftBalance(Node pivo){
        Node c = pivo.left;
        if(c.bFactor == Node.LEFT_HIGH){
            pivo.bFactor = Node.EQUAL_HIGH;
            c.bFactor = Node.EQUAL_HIGH;
            pivo = this.rotacaoSimplesDireita(pivo);
            return pivo;
        }
        else{
            Node grandChild = grandChild(pivo.left, true);
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
     * O método grandChild retorna o filho de um nó filho, ou seja o neto de 
     * determindado nó dependendo do parâmetro left_right
     * @param filho nó que será retornadao seu filho
     * @param left_right boolean para escolher qual filho será retornado true retorna
     * o filho da direita, false retorna o filho da esquerda
     * @return o nó neto
     */
    // left = false
    // right = true
    public Node grandChild(Node filho, boolean left_right){
        Node grandChild = null;
        if(filho.left != null && left_right == false)
            grandChild =  filho.left;
        else if(filho.right != null && left_right == true)
            grandChild = filho.right;
        return grandChild;
    }
    public void imprime(){
        this.imprime(this.root);
    }
    private void imprime(Node root){
        if(root != null){
            System.out.println(root.getKey());
            this.imprime(root.left);
            this.imprime(root.right);
        }
    }       
}