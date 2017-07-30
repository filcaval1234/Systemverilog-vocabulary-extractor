/**
 * A classe variavel emcapsula os dados e os métodos necessarios 
 * para a modelagem de uma variável no código Systemverilog.
 */
package systemverilogvocabularyextractor;

/**
 *
 * @author fc.corporation
 */
public class Variavel {
    String nome;
    String tipo;
    
    /**
     * O construtor da classe recebe dois argumentos e inicializa todos os campos
     * da classe
     * @param tipo tipo da variável
     * @param nome nome atribuido à variável
     */
    public Variavel(String tipo, String nome){
        this.nome = nome;
        this.tipo = tipo;
    }
    public String getNome(){
        return this.nome;
    }
    public String getTipo(){
        return this.tipo;
    }
    public String toString(){
        String variavel = "tipo: "+tipo+" Nome: "+nome+"\n";
        return variavel;
    }
}