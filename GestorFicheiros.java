package OperacoesPolinomios;

import java.io.*;
import java.util.*;

/** Classe que gere os ficheiros
 */
public abstract class GestorFicheiros 
{
    private static BufferedReader bufferedReader;
    private static LinkedList linhasFicheiro = new LinkedList ();
    
    /** Verifica se existe o ficheiro
     * @param nome Nome do ficheiro
     * @return Verdadeiro se o ficheiro existe, falso se não existe
     */    
    public static boolean existeFicheiro (String nome)
    {
        try
        {
            bufferedReader = new BufferedReader (new FileReader (nome));
            return true;
        }
        catch (FileNotFoundException exception)
        {
            return false;
        }
    }
    
    /** Le o ficheiro
     * @return Verdadeiro se consegue ler o ficheiro, falso se não consegue
     */    
    public static boolean leFicheiro ()
    {
        try
        {
            String linha = bufferedReader.readLine ();
            while (linha != null)
            {
                linhasFicheiro.add (linha);
                linha = bufferedReader.readLine ();   
            }
            return true;
        }
        catch (IOException exception)
        {
            return true;
        }
    }
    
    /** Retorna as linhas do ficheiro
     * @return Linhas do ficheiro
     */    
    public static LinkedList getLinhasFicheiro ()
    {
        return linhasFicheiro;
    }
}