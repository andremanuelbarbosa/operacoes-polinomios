package OperacoesPolinomios;

import java.util.*;

/** Classe representante dos polinomios
 */
public class Polinomio
{
    private String polinomio;
    private String nome;
    private String igual;
    private String resto;
    private int grau;
    private LinkedList partes = new LinkedList ();
    private LinkedList partesMais = new LinkedList ();
    private LinkedList partesMenos = new LinkedList ();
    private String letrasMinusculas[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    
    /** Cria um novo polinomio com a expressão correspondente
     * @param polinomio Expressão do polinomio
     */    
    public Polinomio (String polinomio)
    {
        this.polinomio = polinomio;
    }
    
    /** Retorna o nome do polinomio
     * @return Nome do polinomio
     */    
    public String getNome ()
    {
        return this.nome;
    }
    
    /** Retorna o grau do polinomio
     * @return Grau do polinomio
     */    
    public int getGrau ()
    {
        return this.grau;
    }
    
    /** Retorna as partes do polinomio
     * @return Partes do polinomio
     */    
    public LinkedList getPartes ()
    {
        return this.partes;
    }
    
    /** Altera as partes do polinomio
     * @param partes Partes do polinomio
     */    
    public void setPartes (LinkedList partes)
    {
        this.partes = partes;
    }
    
    /** Retorna o resto do polinomio
     * @return Resto do polinomio
     */    
    public String getResto ()
    {
        return this.resto;   
    }
    
    /** Actualiza o resto do polinomio
     */    
    public void actualizaResto ()
    {
        String newResto = "";
        for (int i = 0; i < this.partes.size (); i++)
        {
            Parte parte = (Parte) partes.get (i);
            newResto += parte.toString () + " ";
        }
        this.resto = newResto;
    }
    
    /** Verifica se é polinomio
     * @return Verdadeiro se o valor lido é polinomio, falso se não é
     */    
    public boolean isPolinomio ()
    {
        try
        {
            StringTokenizer stringTokenizer = new StringTokenizer (polinomio, " ");
            this.nome = stringTokenizer.nextToken ().toString ();
            this.igual = stringTokenizer.nextToken ().toString ();
            
            int cont = 0;
            for (int i = 0; i < 26; i++) if (! (nome.equals (letrasMinusculas[i]))) cont++;
            
            if ((cont == 26) || (! (igual.equals ("=")))) return false;
            else
            {
                this.resto = polinomio.substring (4, polinomio.length());
                try
                {
                    StringTokenizer stringTokenizerResto = new StringTokenizer (resto, " ");
                    String tokenResto1 = stringTokenizerResto.nextToken ().toString ();
                    if ((tokenResto1.equals ("+")) || (tokenResto1.equals ("-")))
                    {
                        try
                        {
                            StringTokenizer stringTokenizerRestoMais = new StringTokenizer (resto, "+");
                            int numTokensMais = stringTokenizerRestoMais.countTokens ();
                            for (int j = 0; j < numTokensMais; j++)
                            {
                                String parteMais = stringTokenizerRestoMais.nextToken ().toString ();
                                char aux[] = parteMais.toCharArray ();
                                int cont1 = 0;
                                for (int k = 0; k < parteMais.length (); k++)
                                {
                                    if (aux[k] == '-')
                                    {
                                        try 
                                        {
                                            StringTokenizer stringTokenizerRestoMenos = new StringTokenizer (parteMais, "-");
                                            int numTokensMenos = stringTokenizerRestoMenos.countTokens ();
                                            if (aux[0] == '-')
                                            {
                                                StringTokenizer stringTokenizerRestoMenosAux = new StringTokenizer (stringTokenizerRestoMenos.nextToken ().toString (), " ");
                                                String parteMenosAux = "";
                                                int numTokensRestoMenosAux = stringTokenizerRestoMenosAux.countTokens ();
                                                for (int l = 0; l < numTokensRestoMenosAux; l++) parteMenosAux += stringTokenizerRestoMenosAux.nextToken ().toString ();
                                                partesMenos.add (parteMenosAux);
                                            }
                                            else 
                                            {
                                                StringTokenizer stringTokenizerRestoMaisAux = new StringTokenizer (stringTokenizerRestoMenos.nextToken ().toString (), " ");
                                                String parteMaisAux = "";
                                                int numTokensRestoMaisAux = stringTokenizerRestoMaisAux.countTokens ();
                                                for (int l = 0; l < numTokensRestoMaisAux; l++) parteMaisAux += stringTokenizerRestoMaisAux.nextToken ().toString ();
                                                partesMais.add (parteMaisAux);
                                            }
                                            for (int l = 0; l < numTokensMenos - 1; l++)
                                            {
                                                StringTokenizer stringTokenizerRestoMenosAux = new StringTokenizer (stringTokenizerRestoMenos.nextToken ().toString (), " ");
                                                String parteMenosAux = "";
                                                int numTokensRestoMenosAux = stringTokenizerRestoMenosAux.countTokens ();
                                                for (int m = 0; m < numTokensRestoMenosAux; m++) parteMenosAux += stringTokenizerRestoMenosAux.nextToken ().toString ();
                                                partesMenos.add (parteMenosAux);
                                            }
                                            break;
                                        }
                                        catch (Exception exception3)
                                        {
                                            return false;
                                        }
                                    }
                                    else cont1++;
                                }
                                if (cont1 == parteMais.length ()) 
                                {
                                    StringTokenizer stringTokenizerRestoMaisAux = new StringTokenizer (parteMais, " ");
                                    String parteMaisAux = "";
                                    int numTokensRestoMaisAux = stringTokenizerRestoMaisAux.countTokens ();
                                    for (int l = 0; l < numTokensRestoMaisAux; l++) parteMaisAux += stringTokenizerRestoMaisAux.nextToken ().toString ();
                                    partesMais.add (parteMaisAux);
                                }
                            }
                        }
                        catch (Exception exception2)
                        {
                            return false;
                        }
                        for (int i = 0; i < partesMais.size (); i++)
                        {
                            String aux = "+ " + (String) partesMais.get (i);
                            Parte parte = new Parte (aux);
                            if (parte.isParte ()) partes.add (parte);
                        }
                        for (int j = 0; j < partesMenos.size (); j++)
                        {
                            String aux = "- " + (String) partesMenos.get (j);
                            Parte parte = new Parte (aux);
                            if (parte.isParte ()) partes.add (parte);
                        }
                        if (partes.size () == (partesMais.size () + partesMenos.size ()))
                        {
                            int contAux = 0;
                            for (int k = 0; k < partes.size () - 1; k++)
                            {
                                Parte parteVar1 = (Parte) partes.get (k);
                                Parte parteVar2 = (Parte) partes.get (k + 1);
                                if (parteVar1.getVar ().equals ("") || parteVar1.getVar ().equals (parteVar2.getVar ()) || parteVar2.getVar ().equals ("")) contAux++;
                            }
                            contAux++;
                            if (contAux == partes.size ()) 
                            {
                                GestorPolinomios.addPolinomio (this);
                                return true;
                            }
                            else return false;
                        }
                        else return false;
                    }
                    else
                    {
                        try
                        {
                            StringTokenizer stringTokenizerRestoMais = new StringTokenizer (resto, "+");
                            int numTokensMais = stringTokenizerRestoMais.countTokens ();
                            for (int j = 0; j < numTokensMais; j++)
                            {
                                String parteMais = stringTokenizerRestoMais.nextToken ().toString ();
                                char aux[] = parteMais.toCharArray ();
                                int cont1 = 0;
                                for (int k = 0; k < parteMais.length (); k++)
                                {
                                    if (aux[k] == '-')
                                    {
                                        try 
                                        {
                                            StringTokenizer stringTokenizerRestoMenos = new StringTokenizer (parteMais, "-");
                                            int numTokensMenos = stringTokenizerRestoMenos.countTokens ();
                                            if (aux[0] == '-') 
                                            {
                                                StringTokenizer stringTokenizerRestoMenosAux = new StringTokenizer (stringTokenizerRestoMenos.nextToken ().toString (), " ");
                                                String parteMenosAux = "";
                                                int numTokensRestoMenosAux = stringTokenizerRestoMenosAux.countTokens ();
                                                for (int l = 0; l < numTokensRestoMenosAux; l++) parteMenosAux += stringTokenizerRestoMenosAux.nextToken ().toString ();
                                                partesMenos.add (parteMenosAux);
                                            }
                                            else
                                            {
                                                StringTokenizer stringTokenizerRestoMaisAux = new StringTokenizer (stringTokenizerRestoMenos.nextToken ().toString (), " ");
                                                String parteMaisAux = "";
                                                int numTokensRestoMaisAux = stringTokenizerRestoMaisAux.countTokens ();
                                                for (int l = 0; l < numTokensRestoMaisAux; l++) parteMaisAux += stringTokenizerRestoMaisAux.nextToken ().toString ();
                                                partesMais.add (parteMaisAux);
                                            }
                                            for (int l = 0; l < numTokensMenos - 1; l++)
                                            {
                                                StringTokenizer stringTokenizerRestoMenosAux = new StringTokenizer (stringTokenizerRestoMenos.nextToken ().toString (), " ");
                                                String parteMenosAux = "";
                                                int numTokensRestoMenosAux = stringTokenizerRestoMenosAux.countTokens ();
                                                for (int m = 0; m < numTokensRestoMenosAux; m++) parteMenosAux += stringTokenizerRestoMenosAux.nextToken ().toString ();
                                                partesMenos.add (parteMenosAux);
                                            }
                                            break;
                                        }
                                        catch (Exception exception3)
                                        {
                                            return false;
                                        }
                                    }
                                    else cont1++;
                                }
                                if (cont1 == parteMais.length ()) 
                                {
                                    StringTokenizer stringTokenizerRestoMaisAux = new StringTokenizer (parteMais, " ");
                                    String parteMaisAux = "";
                                    int numTokensRestoMaisAux = stringTokenizerRestoMaisAux.countTokens ();
                                    for (int l = 0; l < numTokensRestoMaisAux; l++) parteMaisAux += stringTokenizerRestoMaisAux.nextToken ().toString ();
                                    partesMais.add (parteMaisAux);
                                }
                            }
                        }
                        catch (Exception exception2)
                        {
                            return false;
                        }
                        for (int i = 0; i < partesMais.size (); i++)
                        {
                            String aux = "+ " + (String) partesMais.get (i);
                            Parte parte = new Parte (aux);
                            if (parte.isParte ()) partes.add (parte);
                        }
                        for (int j = 0; j < partesMenos.size (); j++)
                        {
                            String aux = "- " + (String) partesMenos.get (j);
                            Parte parte = new Parte (aux);
                            if (parte.isParte ()) partes.add (parte);
                        }
                        if (partes.size () == (partesMais.size () + partesMenos.size ()))
                        {
                            int contAux = 0;
                            for (int k = 0; k < partes.size () - 1; k++)
                            {
                                Parte parteVar1 = (Parte) partes.get (k);
                                Parte parteVar2 = (Parte) partes.get (k + 1);
                                if (parteVar1.getVar ().equals ("") || parteVar1.getVar ().equals (parteVar2.getVar ()) || parteVar2.getVar ().equals ("")) contAux++;
                            }
                            contAux++;
                            if (contAux == partes.size ()) 
                            {
                                GestorPolinomios.addPolinomio (this);
                                return true;
                            }
                            else return false;
                        }
                        else return false;
                    }
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
    
    /** Ordena o polinomio
     */    
    public void ordenaPolinomio ()
    {
        String newResto = "";
        LinkedList newPartes = new LinkedList ();
        
        for (int j = this.grau; j >= 0; j--)
        {
            for (int k = 0; k < partes.size (); k++)
            {
                Parte parte = (Parte) partes.get (k);
                if (parte.getGrau () == j) 
                {
                    newPartes.add (parte);
                    newResto += parte.toString () + " ";
                }
            }
        }
        this.partes = newPartes;
        this.resto = newResto;
    }
    
    /** Simplifica o polinomio
     */    
    public void simplificaPolinomio ()
    {
        String newResto = "";
        int max = 0;
        
        for (int i = 0; i < partes.size (); i++)
        {
            Parte parte = (Parte) partes.get (i);
            if (parte.getGrau () > max) max = parte.getGrau ();
        }
        this.grau = max;
        
        for (int j = 0; j < partes.size () - 1; j++)
        {
            Parte parte = (Parte) partes.get (j);
            
            for (int k = j + 1; k < partes.size (); k++)
            {
                Parte parteAux = (Parte) partes.get (k);

                if (parte.getGrau () == parteAux.getGrau ())
                {
                    if (parte.getSinal ().equals (parteAux.getSinal ())) parte.setValor (parte.getValor () + parteAux.getValor ());
                    else
                    {
                        if (parte.getSinal ().equals ("+")) 
                        {
                            if (parte.getValor () < parteAux.getValor ()) parte.setSinal ("-");
                            parte.setValor (Math.abs (parte.getValor () - parteAux.getValor ()));
                        }
                        else 
                        {
                            if (parteAux.getValor () < parteAux.getValor ()) parte.setSinal ("-");
                            parte.setValor (Math.abs (parteAux.getValor () - parte.getValor ()));
                        }
                    }
                    if (parte.getValor () == 0)
                    {
                        if (parte.getGrau () == this.grau) if (this.grau > 0) this.grau--;
                        partes.remove (parte);
                        partes.remove (parteAux);
                    }
                    else
                    {
                        parte.actualizaParte ();
                        partes.set (j, parte);
                        partes.remove (k);
                    }
                    k--;
                }
            }
        }
        
        for (int l = 0; l < partes.size (); l++)
        {
            Parte parte = (Parte) partes.get (l);
            newResto += parte.toString () + " ";
        }
        this.resto = newResto;
    }
    
    /** Retorna a string correspondente ao polinomio
     * @return String correspondente ao polinomio
     */    
    public String toString ()
    {
        return nome + " = " + resto;
    }
}