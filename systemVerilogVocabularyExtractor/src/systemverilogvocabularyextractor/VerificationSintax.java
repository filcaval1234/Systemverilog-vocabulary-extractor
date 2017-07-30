/**
 * The class VerificationSintax encapsulate the data necessary
 * for the class responsable by all sintax system verilog both for verification
 * and design rtl
 */
package systemverilogvocabularyextractor;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import avltree.*;

/**
 *
 * @author fc.corporation
 */
public class VerificationSintax {
    static private final String WORDSKEYSVALUES = "`abcdefghijklmnopqrstuvwx_01";
    private AvlTree avlBody;

    VerificationSintax() {
        this.avlBody = new AvlTree();
    }
    /**
     * O método setWordsKeys lê do arquivo keysWordsSystemverilog.txt todas as palavras
     * reservadas e monta uma lista com as mesmas
     * @return uma lista de Strings que são as palavras reservadas da linguagem
     */
    public String[] setWordsKeys(){
        final int QUANTWORDSKEYS = 222;
        String[] wordsKeys = new String[QUANTWORDSKEYS];
        int i = 0;
        try {
            FileReader file = new FileReader("C:\\Users\\fc.corporation\\Documents\\NetBeansProjects\\systemVerilogVocabularyExtractor\\wordKeys\\keysWordsSystemverilog.txt");
            BufferedReader arq = new BufferedReader(file);
            while(arq.ready()){
                wordsKeys[i] = arq.readLine();
                i++;
            }
            arq.close();
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        return wordsKeys;
    }
    /**
     * O método setAvlTreeSintax monta primeiramente sub-árvores onde cada sub-árvore dessas
     * é relativo à uma letra do alfabeto, ou seja, para todas as palavras que começam com a
     * mesma letra são agrupadas em uma sub-árvore, no caso ele monta 25 sub-árvores
     * já que não há palavras que iniciam com a letra z na sintax System verilog,
     * Após montar as sub-árvores, cada uma delas viraram um nó do atributo da classe
     * avlBody que também é uma árvore cujo dado é uma dessas sub-árvores
     * @param wordsKeys lista com as palavras reservadas da linguagem
     */
    public void setAvlTreeSintax(String[] wordsKeys){
        int j=0;
        AvlTreeSintax localAvlTreeSintax = new AvlTreeSintax();
        for(int i=0;i < wordsKeys.length;i++){
            if(wordsKeys[i].charAt(0) ==  WORDSKEYSVALUES.charAt(j)){
                String data = wordsKeys[i];
                localAvlTreeSintax.avlInsert(data);
            }
            else{
                this.avlBody.avlInsert(j, localAvlTreeSintax);
                ++j;--i;
                localAvlTreeSintax = new AvlTreeSintax();
            }
        }
        this.avlBody.avlInsert(j, localAvlTreeSintax);
    }
    /**
     * O método systemVerilogSintax retorna true se a palavra passado como argumento pertence à
     * avl montada com as palavras reservadas, retorna false se a palavra passada
     * não esta contida na árvore
     * @param wordKey palavra passada que será analisada se está ou não contida na linguagem
     * @return true se a palvra estiver na árvore, ou seja, é uma palavra reservada, ou false não sendo palavra reservada
     */
    public boolean sytemVerilogSintax(String wordKey){
        this.setAvlTreeSintax(this.setWordsKeys());
        return this.avlBody.busca(WORDSKEYSVALUES.indexOf(wordKey.charAt(0))).busca(wordKey);
    }
    public boolean UVMsintax(String word){
        return word.contains("uvm");
    }
    /*public static void main(String[] args) {
        String nome = "uvmParent";
        System.out.println(nome.contains("uvm"));
    }*/
}