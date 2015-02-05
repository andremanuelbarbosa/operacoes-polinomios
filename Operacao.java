package OperacoesPolinomios;

import java.util.*;

/** Classe representante das operacões
 */
public class Operacao
{
    private String operacao;
    private String nome;
    private String igual;
    private String resto;
    private String letrasMaiusculas[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private String letrasMinusculas[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private String operandos[] = {"+","-","*","/"};
        
    /** Cria um nova operacao
     * @param operacao Expressao correspondente a operacao
     */    
    public Operacao (String operacao)
    {
        this.operacao = operacao;
    }
    
    /** Retorna o nome da operacao
     * @return Nome da operacao
     */    
    public String getNome ()
    {
        return this.nome;
    }
    
    /** Retorna o resto da operacao
     * @return Resto da operacao
     */    
    public String getResto ()
    {
        return this.resto;
    }

    /** Verifica se é operacao
     * @return Verdadeiro se for operacao, falso se não for
     */    
    public boolean isOperacao ()
    {
        try
        {
            StringTokenizer stringTokenizer = new StringTokenizer (operacao, " ");
            this.nome = stringTokenizer.nextToken ().toString (); 
            this.igual = stringTokenizer.nextToken ().toString ();
            
            int cont = 0;
            for (int i = 0; i < 26; i++) if (! (nome.equals (letrasMaiusculas[i]))) cont++;
            
            if ((cont == 26) || (! (igual.equals ("=")))) return false;
            else
            {
                resto = operacao.substring (4, operacao.length());
                
                try 
                {
                    StringTokenizer stringTokenizerResto = new StringTokenizer (resto, " ");
                    int numTokensResto = stringTokenizerResto.countTokens ();
                    
                    if ((numTokensResto % 2) == 0) resto = resto.substring (2, resto.length ());
                    
                    LinkedList partes = new LinkedList ();
                    for (int j = 0; j < numTokensResto; j++) partes.add (stringTokenizerResto.nextToken ().toString ());

                    for (int k = 0; k < partes.size () - 1; k++)
                    {
                        String parte = (String) partes.get (k);

                        if (parte.equals ("("))
                        {
                            int aux = 0;
                            for (int l = k + 1; l < partes.size (); l++) 
                            {
                                String parteAux = (String) partes.get (l);
                                if (parteAux.equals (")")) aux++;
                            }
                            if (aux == 0) return false;
                        }
                    }

                    int contGeral = 0;

                    for (int k = 0; k < partes.size (); k++)
                    {
                        String parte = (String) partes.get (k);
                        String polinomio = "";
                        String operacao = "";

                        if (parte.length () > 1) return false;

                        int cont1 = 0;
                        for (int l = 0; l < 26; l++) 
                        {
                            if (parte.equals (letrasMinusculas[l])) 
                            {
                                cont1++;
                                polinomio = letrasMinusculas[l];
                            }
                        }

                        if (cont1 == 0)
                        {
                            int cont2 = 0;
                            for (int m = 0; m < 26; m++) 
                            {
                                if (parte.equals (letrasMaiusculas[m])) 
                                {
                                    cont2++;
                                    operacao = letrasMaiusculas[m];
                                }
                            }

                            if (cont2 == 0)
                            {
                                int cont3 = 0;
                                for (int n = 0; n < 4; n++) if (parte.equals (operandos[n])) 
                                {
                                    cont3++;
                                    contGeral++;
                                }

                                if (cont3 == 0) if (parte.equals ("(") || parte.equals (")")) contGeral++;
                                else return false;
                            }
                            else if (GestorOperacoes.existeOperacao (operacao)) contGeral++;
                        }
                        else if (GestorPolinomios.existePolinomio (polinomio)) contGeral++;
                    }
                    if (contGeral == numTokensResto) 
                    {
                        GestorOperacoes.addOperacao (this);
                        return true;
                    }
                    else return false;
                }
                catch (Exception exception1)
                {
                    return false;   
                }
            }
        }
        catch (Exception exception)
        {
            return false;
        }
        
    }
    
    /** Retorna a string correspondente a operacao
     * @return Expressao da operacao
     */    
    public String toString ()
    {
        return this.nome + " = " + this.resto;
    }
}