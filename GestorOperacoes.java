package OperacoesPolinomios;

import java.util.*;

/** Classe que gere aas operacões
 */
public abstract class GestorOperacoes
{
    private static LinkedList operacoes = new LinkedList ();
    
    /** Adiciona operacoes
     * @param operacao Operacao a adicionar
     */    
    public static void addOperacao (Operacao operacao)
    {
        operacoes.add (operacao);
    }
    
    /** Verifica se a operacao existe
     * @param letra Nome da operacao
     * @return Verdadeiro se a operacao existe, falso se não existe
     */    
    public static boolean existeOperacao (String letra)
    {
        int cont = 0;
        
        for (int i = 0; i < operacoes.size (); i++)
        {
            Operacao operacao = (Operacao) operacoes.get (i);
            if (operacao.getNome ().equals (letra)) cont++;
        }
        
        if (cont == 0) return false;
        else return true;
    }
    
    /** Retorna uma operacao
     * @param letra Nome da operacao
     * @return Operacao
     */    
    public static Operacao getOperacao (String letra)
    {
        Operacao operacao = new Operacao ("");
        
        for (int i = 0; i < operacoes.size (); i++)
        {
            Operacao operacaoAux = (Operacao) operacoes.get (i);
            if (operacaoAux.getNome ().equals (letra)) operacao = operacaoAux;
        }
        
        return operacao;
    }
    
    /** Retorna todas as operacoes
     * @return Todas as operacoes
     */    
    public static LinkedList getOperacoes ()
    {
        return operacoes;
    }
}