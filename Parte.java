package OperacoesPolinomios;

import java.util.*;

/** Classe representante das partes de um polinomio
 */
public class Parte
{
    private String parte;
    private String sinal;
    private String resto;
    private String aux;
    private double valor;
    private String var;
    private int grau;
    
    /** Cria uma nova parte
     * @param parte Expressao correspondente a parte
     */    
    public Parte (String parte)
    {
        this.parte = parte;
    }
    
    /** Retorna o sinal da parte
     * @return Sinal da parte
     */    
    public String getSinal ()
    {
        return this.sinal;
    }
    
    /** Modifica o sinal da parte
     * @param newSinal Sinal da parte
     */    
    public void setSinal (String newSinal)
    {
        this.sinal = newSinal;
    }
    
    /** Retorna o valor da parte
     * @return Valor da parte
     */    
    public double getValor ()
    {
        return this.valor;
    }
    
    /** Modifica o valor da parte
     * @param newValor Valor da parte
     */    
    public void setValor (double newValor)
    {
        this.valor = newValor;
    }
    
    /** Retorna a variavel da parte
     * @return Variavel da parte
     */    
    public String getVar ()
    {
        return this.var;
    }
    
    /** Retorna o grau da parte
     * @return Grau da parte
     */    
    public int getGrau ()
    {
        return this.grau;
    }
    
    /** Modifica o grau da parte
     * @param newGrau Grau da parte
     */    
    public void setGrau (int newGrau)
    {
        this.grau = newGrau;
    }
    
    /** Verifica se é parte
     * @return Verdadeiro se for parte, falso se não for
     */    
    public boolean isParte ()
    {
        try
        {
            StringTokenizer stringTokenizerParte = new StringTokenizer (this.parte, " ");
            this.sinal = stringTokenizerParte.nextToken ().toString ();
            this.resto = stringTokenizerParte.nextToken ().toString ();
            
            StringTokenizer stringTokenizerResto = new StringTokenizer (resto, "^");
            int numTokensResto = stringTokenizerResto.countTokens ();
            if (numTokensResto <= 2)
            {
                this.aux = stringTokenizerResto.nextToken ().toString ();
                if (stringTokenizerResto.hasMoreTokens ())
                {
                    try
                    {
                        this.grau = Integer.parseInt (stringTokenizerResto.nextToken ().toString ());
                        if (this.aux.length () == 1)
                        {
                            try
                            {
                                double aux1;
                                aux1 = Double.parseDouble (this.aux);
                                this.valor = Math.pow (aux1, this.grau);
                                this.var = "";
                                this.grau = 0;
                                this.resto = "" + this.valor;
                            }
                            catch (Exception exception4)
                            {
                                this.valor = 1;
                                this.var = this.aux;
                                this.resto = "" + this.var + "^" + this.grau;
                            }
                        }
                        else 
                        {
                            this.valor = Double.parseDouble (aux.substring (0, aux.length () - 1));
                            this.var = aux.substring (aux.length () - 1);
                            if (this.grau > 1) this.resto = "" + this.valor + this.var + "^" + this.grau;
                            if (this.grau == 1) this.resto = "" + this.valor + this.var;
                            if (this.grau == 0) this.resto = "" + this.valor;
                        }
                        return true;
                    }
                    catch (Exception exception1)
                    {
                        return false;
                    }
                }
                else
                {
                
                    try
                    {
                        this.valor = Double.parseDouble (aux);
                        this.var = "";
                        this.resto = "" + this.valor;
                        return true;
                    }
                    catch (Exception exception2)
                    {
                        this.grau = 1;
                        if (aux.length () == 1)
                        {
                            this.valor = 1;
                            this.var = aux;
                            this.resto = "" + this.var;
                            return true;
                        }
                        else
                        {
                            try
                            {
                                this.valor = Double.parseDouble (aux.substring (0, aux.length () - 1));
                                this.var = resto.substring (aux.length () - 1);
                                this.resto = "" + this.valor + this.var;
                                return true;
                            }
                            catch (Exception exception3)
                            {
                                return false;
                            }
                        }
                    }
                }
            }
            else
            {
                return false;
            }
        }
        catch (Exception exception)
        {
            return false;
        }
    }
    
    /** Actualiza a parte
     */    
    public void actualizaParte ()
    {
        if (this.grau == 0) this.resto = "" + this.valor;
        if (this.grau == 1)
        {
            if (this.valor == 1) this.resto = "" + this.var;
            else this.resto = "" + this.valor + this.var;
        }
        if (this.grau > 1)
        {
            if (this.valor == 1) this.resto = "" + this.var + "^" + this.grau;
            else this.resto = "" + this.valor + this.var + "^" + this.grau;
        }
    }
    
    /** Retorna a string correspondente a expresao da parte
     * @return Expressao da parte
     */    
    public String toString ()
    {
        return sinal + " " + resto;
    }
}
