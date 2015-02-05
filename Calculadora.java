package OperacoesPolinomios;

import java.util.*;

/** Classe que efectua os calculos
 */
public abstract class Calculadora
{
    private static String letrasMaiusculas[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static String letrasMinusculas[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private static String operadores[] = {"+","-","*","/"};
    
    /** Transforma uma operacao da forma infixa para a forma posfixa
     * @param operacao Operacao a transformar
     * @return Operacao transformada
     */    
    public static String infixaPosFixa (Operacao operacao)
    {
        String resto = operacao.getResto ();
        String posFixa = "";
        Stack stack = new Stack ();
        
        StringTokenizer stringTokenizer = new StringTokenizer (resto, " ");
        int numTokens = stringTokenizer.countTokens ();
        for (int i = 0; i < numTokens; i++)
        {
            String caracter = stringTokenizer.nextToken ().toString ();
            
            int cont = 0;
            for (int j = 0; j < 26; j++)
            {
                if (caracter.equals (letrasMinusculas[j])) 
                {
                    posFixa += caracter + " ";
                    cont++;
                }
                if (caracter.equals (letrasMaiusculas[j]))
                {
                    posFixa += caracter + " ";
                    cont++;
                }
            }
            
            if (cont == 0)
            {
                if (caracter.equals ("(")) stack.add (caracter);
                else
                {
                    if (caracter.equals (")"))
                    {
                        for (int j = 0; j < stack.size (); j++)
                        {
                            String caracterAux = (String) stack.pop ();
                            if (!(caracterAux.equals ("("))) posFixa += caracterAux + " ";
                            else break;
                        }
                    }
                    else
                    {
                        if (stack.isEmpty ()) stack.add (caracter);
                        else
                        {
                            String caracterAux = (String) stack.pop ();
                            stack.add (caracterAux);
                            if (Calculadora.isInferior (caracter, caracterAux)) stack.add (caracter);
                            else
                            {
                                if (caracterAux.equals ("(")) stack.add (caracter);
                                else
                                {
                                    while ((!(stack.isEmpty())) && (!(Calculadora.isInferior (caracter, caracterAux))))
                                    {
                                        caracterAux = (String) stack.pop ();
                                        if (!(caracterAux.equals ("("))) posFixa += caracterAux + " ";
                                    }
                                    if (caracterAux.equals ("(")) stack.add (caracterAux);
                                    stack.add (caracter);
                                }
                            }
                        }
                    }
                }
            }
        }
        int stackSize = stack.size ();
        for (int i = 0; i < stackSize; i++)
        {
            String aux = (String) stack.pop ();
            if (!(aux.equals ("("))) posFixa += aux + " ";
        }
        return posFixa;
    }
    
    /** Verifica se o operador1 é inferior ao operador2
     * @param operador1 Operador
     * @param operador2 Operador
     * @return Verdadeiro se o operador1 inferior ao operador2, falso se não é
     */    
    public static boolean isInferior (String operador1, String operador2)
    {
        String operadoresSuperiores[] = {"/","*"};
        String operadoresInferiores[] = {"+","-"};
        
        int cont1 = 0;
        for (int i = 0; i < 2; i++)
        {
            if (operador1.equals (operadoresSuperiores[i]))
            {
                int cont = 0;
                for (int j = 0; j < 2; j++) 
                {
                    if (! (operador2.equals (operadoresSuperiores[j]))) cont++;
                }
                if (cont == 0) return false;
                else return true;
            }
            else cont1++;
        }
        if (cont1 == 2) return false;
        else return true;
    }
    
    /** Efectua os calculos
     * @param posFixa Polinomio na forma posfixa
     * @return Resultado
     */    
    public static String calcula (String posFixa)
    {
        StringTokenizer stringTokenizer = new StringTokenizer (posFixa, " ");
        Stack stack = new Stack ();
        int numTokens = stringTokenizer.countTokens ();
        
        for (int i = 0; i < numTokens; i++)
        {
            String aux = stringTokenizer.nextToken ().toString ();
            int cont = 0;
            for (int j = 0; j < 4; j++) if (aux.equals (operadores[j])) cont++;
            if (cont == 0) stack.push (aux);
            else
            {
                Polinomio polinomio2 = Calculadora.descodificaOperando ((String) stack.pop ());
                if (stack.isEmpty ())
                {
                    if (aux.equals ("-"))
                    {
                        LinkedList partes = polinomio2.getPartes ();
                        for (int j = 0; j < partes.size (); j++)
                        {
                            Parte parte = (Parte) partes.get (j);
                            if (parte.getSinal ().equals ("+")) parte.setSinal ("-");
                            else parte.setSinal ("+");
                            partes.set (j, parte);
                        }
                        polinomio2.setPartes (partes);
                        polinomio2.actualizaResto ();
                    }
                    stack.push (polinomio2.getResto ());
                }
                else
                {
                    Polinomio polinomio1 = Calculadora.descodificaOperando ((String) stack.pop ());
                    Polinomio polinomio = new Polinomio ("");
                    if (aux.equals ("+")) polinomio = Calculadora.soma (polinomio1, polinomio2);
                    if (aux.equals ("-")) polinomio = Calculadora.sub (polinomio1, polinomio2);
                    if (aux.equals ("*")) polinomio = Calculadora.mul (polinomio1, polinomio2);
                    if (aux.equals ("/")) polinomio = Calculadora.div (polinomio1, polinomio2);
                    if (polinomio.getResto ().equals ("")) stack.push ("0");
                    else stack.push (polinomio.getResto ());
                }
            }
        }
        return (String) stack.pop ();
    }
    
    /** Soma dois polinomios
     * @param operando1 Polinomio
     * @param operando2 Polinomio
     * @return Polinomio com a soma dos polinomios
     */    
    public static Polinomio soma (Polinomio operando1, Polinomio operando2)
    {
        Polinomio polinomio = new Polinomio ("z = " + operando1.getResto () + " " + operando2.getResto ());
        if (polinomio.isPolinomio ()) 
        {
            polinomio.simplificaPolinomio ();
            polinomio.ordenaPolinomio ();   
        }
        return polinomio;
    }
    
    /** Subtrai dois polinomios
     * @param operando1 Polinomio
     * @param operando2 Polinomio
     * @return Polinomio com a subtracao dos polinomios
     */    
    public static Polinomio sub (Polinomio operando1, Polinomio operando2)
    {
        Polinomio operando2Aux = new Polinomio ("y = " + operando2.getResto ());
        operando2Aux.isPolinomio ();
        LinkedList partes = operando2Aux.getPartes ();
        for (int i = 0; i < partes.size (); i++)
        {
            Parte parte = (Parte) partes.get (i);
            if (parte.getSinal ().equals ("+")) parte.setSinal ("-");
            else parte.setSinal ("+");
            partes.set (i, parte);
        }
        operando2Aux.setPartes (partes);
        operando2Aux.actualizaResto ();
        Polinomio polinomio = new Polinomio ("z = " + operando1.getResto () + " " + operando2Aux.getResto ());
        if (polinomio.isPolinomio ()) 
        {
            polinomio.simplificaPolinomio ();
            polinomio.ordenaPolinomio ();   
        }
        return polinomio;
    }
    
    /** Multiplica dois polinomios
     * @param operando1 Polinomio
     * @param operando2 Polinomio
     * @return Polinomio com a multiplicacao dos polinomios
     */    
    public static Polinomio mul (Polinomio operando1, Polinomio operando2)
    {
        LinkedList partes1 = operando1.getPartes ();
        LinkedList partes2 = operando2.getPartes ();
        String aux = "";
        
        for (int i = 0; i < partes1.size (); i++)
        {
            for (int j = 0; j < partes2.size (); j++)
            {
                Parte parte1 = (Parte) partes1.get (i);
                Parte parte2 = (Parte) partes2.get (j);
                if ((parte1.getValor () * parte2.getValor ()) != 0)
                {
                    if (parte1.getSinal ().equals (parte2.getSinal ())) aux += "+ ";
                    else aux += "- ";
                    aux += (parte1.getValor () * parte2.getValor ());
                    if (!((parte1.getGrau () + parte2.getGrau ()) == 0)) 
                    {
                        if (parte1.getVar ().equals ("")) aux += parte2.getVar ();
                        else aux += parte1.getVar ();
                        aux += "^" + (parte1.getGrau () + parte2.getGrau ());
                    }
                    aux += " ";
                }
            }
        }
        Polinomio polinomio = new Polinomio ("x = " + aux);
        if (polinomio.isPolinomio ())
        {
            polinomio.simplificaPolinomio ();
            polinomio.ordenaPolinomio ();
        }
        return polinomio;
    }
    
    /** Divide dois polinomios
     * @param operando1 Polinomio
     * @param operando2 Polinomio
     * @return Polinomio com a divisao dos polinomios
     */    
    public static Polinomio div (Polinomio operando1, Polinomio operando2)
    {
        LinkedList partesOperando1 = operando1.getPartes ();
        LinkedList partesOperando2 = operando2.getPartes ();
        
        String divisao = "";
        
        while (operando1.getGrau () >= operando2.getGrau ())
        {
            Parte parte1 = (Parte) partesOperando1.get (0);
            Parte parte2 = (Parte) partesOperando2.get (0);
            
            double coeficiente = parte1.getValor () / parte2.getValor ();
            int grau = parte1.getGrau () - parte2.getGrau ();
            String sinal;
            if (parte1.getSinal ().equals (parte2.getSinal ())) sinal = "+";
            else sinal = "-";
            String aux = "";
            if (operando1.getGrau () == operando2.getGrau ()) aux = sinal + " " + coeficiente;
            else aux = sinal + " " + coeficiente + parte1.getVar () + "^" + grau;
            divisao += aux + " ";
            Polinomio polinomioAux = new Polinomio ("y = " + aux);
            if (polinomioAux.isPolinomio ())
            {
                polinomioAux.simplificaPolinomio ();
                polinomioAux.ordenaPolinomio ();
            }
            Polinomio operando1Original = operando1;
            operando1 = Calculadora.mul (polinomioAux, operando2);
            partesOperando1 = operando1.getPartes ();
            for (int i = 0; i < partesOperando1.size (); i++)
            {
                Parte parteAux = (Parte) partesOperando1.get (i);
                if (parteAux.getSinal ().equals ("+")) parteAux.setSinal ("-");
                else parteAux.setSinal ("+");
                partesOperando1.set (i, parteAux);
            }
            operando1.setPartes (partesOperando1);
            operando1.actualizaResto ();
            operando1 = Calculadora.soma (operando1Original, operando1);
            partesOperando1 = operando1.getPartes ();
        }
        
        if (operando1.getResto ().equals (""))
        {
            Polinomio polinomio = new Polinomio ("z = " + divisao);
            if (polinomio.isPolinomio ())
            {
                polinomio.simplificaPolinomio ();
                polinomio.ordenaPolinomio ();
            }
            return polinomio;
        }
        else
        {
            Polinomio polinomio = new Polinomio ("z = ");
            if (polinomio.isPolinomio ())
            {
                polinomio.simplificaPolinomio ();
                polinomio.ordenaPolinomio ();
            }
            return polinomio;
        }
    }    
    
    /** Retorna um polinomio de acordo com o tipo de operando
     * @param operando Operando
     * @return Polinomio
     */    
    public static Polinomio descodificaOperando (String operando)
    {
        if (GestorPolinomios.existePolinomio (operando)) return GestorPolinomios.getPolinomio (operando);
        else
        {
            if (GestorOperacoes.existeOperacao (operando))
            {
                String aux = Calculadora.calcula (Calculadora.infixaPosFixa (GestorOperacoes.getOperacao (operando)));
                Polinomio polinomio = new Polinomio ("x = " + aux);
                if (polinomio.isPolinomio ()) 
                {
                    polinomio.simplificaPolinomio ();
                    polinomio.ordenaPolinomio ();
                }
                return polinomio;
            }
            else
            {
                Polinomio polinomio = new Polinomio ("x = " + operando);
                if (polinomio.isPolinomio ()) 
                {
                    polinomio.simplificaPolinomio ();
                    polinomio.ordenaPolinomio ();
                }
                return polinomio;
            }
        }
    }
}