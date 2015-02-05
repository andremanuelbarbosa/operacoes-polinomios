package OperacoesPolinomios;

import java.util.*;

/** Classe que gere os polinomios
 */
public abstract class GestorPolinomios
{
    private static LinkedList polinomios = new LinkedList ();
    
    /** Adiciona um polinomio
     * @param polinomio Polinomio a adicionar
     */    
    public static void addPolinomio (Polinomio polinomio)
    {
        polinomio.simplificaPolinomio ();
        polinomio.ordenaPolinomio ();
        polinomios.add (polinomio);
    }
    
    /** Verifica se existe o polinomio
     * @param letra Nome do polinomio
     * @return Verdadeiro se o polinomio existe, falso se não existe
     */    
    public static boolean existePolinomio (String letra)
    {
        int cont = 0;
        
        for (int i = 0; i < polinomios.size (); i++)
        {
            Polinomio polinomio = (Polinomio) polinomios.get (i);
            if (polinomio.getNome ().equals (letra)) cont++;
        }
        
        if (cont == 0) return false;
        else 
        {
            if (letra.equals ("x") || letra.equals ("y") || letra.equals ("z")) return false;
            else return true;
        }
    }
    
    /** Retorna um polinomio
     * @param letra Nome do polinomio
     * @return Polinomio
     */    
    public static Polinomio getPolinomio (String letra)
    {
        Polinomio polinomio = new Polinomio ("");
        
        for (int i = 0; i < polinomios.size (); i++)
        {
            Polinomio polinomioAux = (Polinomio) polinomios.get (i);
            if (polinomioAux.getNome ().equals (letra)) polinomio = polinomioAux;
        }
        
        return polinomio;
    }
    
    /** Retorna todos os polinomios
     * @return Todos os polinomios
     */    
    public static LinkedList getPolinomios ()
    {
        return polinomios;
    }
}