/**
 * A classe SearchPackage recebe o diretorio do projeto daí cria uma lista
 * com os pacotes do projeto.
 */
package systemverilogvocabularyextractor;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author fc.corporation
 */
public class SearchPackage {
    Package[] packages;
    
    public SearchPackage(String diretorio){
        this.setPackages(diretorio);
    }
    public Package[] getPackage(){
        return this.packages;
    }
    /**
     * O método setPackages recebe um argumento que é o diretorio daí através de
     * uma função recursiva varre esse diretório procurando por arquivos com o 
     * final pkg.sv ou pkg.svh salvando o caminho absoluto e o nome do pacote
     * @param diretorio diretório do projeto, sendo este o caminho absoluto
     */
    public void setPackages(String diretorio){
        ArrayList<String> absolutePathPackage = new ArrayList<String>();
        ArrayList<String> namePackage = new ArrayList<String>();
        this.setPackages(diretorio, absolutePathPackage, namePackage);
        this.packages = new Package[absolutePathPackage.size()];
        for(int i=0;i < absolutePathPackage.size();i++){
            this.packages[i] = new Package(namePackage.get(i), absolutePathPackage.get(i));
            this.packages[i].setArquivosDoPacote();
        }
    }
    private void setPackages(String diretorio, ArrayList<String> absolutePathPackage, ArrayList<String> namePackage){
        File dirProject = new File(diretorio);
        File[] aFile = dirProject.listFiles();
        String[] sufixName = {"pkg.sv", "pkg.svh"};
        for(int i=0;i < aFile.length;i++){
            if(aFile[i].getName().endsWith(sufixName[0]) || aFile[i].getName().endsWith(sufixName[1])){
                absolutePathPackage.add(aFile[i].getAbsolutePath());
                namePackage.add(aFile[i].getName());
            }
            else if(aFile[i].isDirectory()){
                this.setPackages(aFile[i].getAbsolutePath(), absolutePathPackage, namePackage);
            }
        }
    }
}