package OperacoesPolinomios;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;

/** Classe que gere os polinomios e as operacoes e interage com o utilizador
 */
public class Interface extends JFrame
{
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGTH = 400;
    
    private LinkedList polinomios;
    private LinkedList polinomiosLidos = new LinkedList ();
    private LinkedList operacoes;
    private LinkedList operacoesLidas = new LinkedList ();
    
    private JMenuBar jMenuBar = new JMenuBar ();
        private JMenu jMenuFicheiro = new JMenu();
            private JMenuItem jItemFicheiroAbrir = new JMenuItem();
            private JSeparator jSeparatorFicheiro1 = new JSeparator();
            private JMenuItem jItemFicheiroSair = new JMenuItem();
        private JMenu jMenuVer = new JMenu ();
            private JMenuItem jItemVerPolinomios = new JMenuItem ();
            private JMenuItem jItemVerOperacoes = new JMenuItem ();
        private JMenu jMenuAjuda = new JMenu();
            private JMenuItem jItemAjudaConteudos = new JMenuItem();
            private JSeparator jSeparatorAjuda = new JSeparator();
            private JMenuItem jItemAjudaAcerca = new JMenuItem();
    
    private JPanel jPanelApresentacao = new JPanel ();
        private JLabel jLabelApresentacaoTitulo = new JLabel ();
        private JLabel jLabelApresentacaoDisciplina = new JLabel ();
        private JLabel jLabelApresentacaoNomes = new JLabel ();
        private JLabel jLabelApresentacaoData = new JLabel ();
    private JPanel jPanelPolinomios = new JPanel ();
        private JTable jTablePolinomios;
        private JLabel jLabelPolinomios = new JLabel ();
        private int numPolinomios;
    private JPanel jPanelOperacoes = new JPanel ();
        private JTable jTableOperacoes;
        private JLabel jLabelOperacoes = new JLabel ();
        private int numOperacoes;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints ();
    
    private JFileChooser jFileChooser = new JFileChooser ();
    private BufferedReader ficheiro;
    
    /** Cria uma nova Interface
     */    
    public Interface ()
    {
        initComponents ();
    }
    
    /** Inicializa os componentes graficos
     */    
    public void initComponents ()
    {
        this.getContentPane ().add (jPanelApresentacao);
        this.getContentPane().add(jPanelOperacoes);
        this.getContentPane().add(jPanelPolinomios);
        
        jPanelOperacoes.setVisible(false);
        jPanelPolinomios.setVisible(false);
        
        this.setTitle ("Operacoes com polinomios");
        this.setSize (FRAME_WIDTH, FRAME_HEIGTH);
        this.setLocation ((int) ((1024 - FRAME_WIDTH) / 2), (int) ((768 - FRAME_HEIGTH) / 2));
        this.setJMenuBar (jMenuBar);
        this.addWindowListener (new WindowAdapter() 
        {
            public void windowClosing (WindowEvent event) 
            {
                System.exit (0);
            }
        });
        
        jMenuBar.setEnabled (true);
        jMenuBar.add (jMenuFicheiro);
        jMenuBar.add (jMenuVer);
        jMenuBar.add (jMenuAjuda);
        
        jMenuFicheiro.setEnabled (true);
        jMenuFicheiro.setLabel ("Ficheiro");
        jMenuFicheiro.add (jItemFicheiroAbrir);
        jMenuFicheiro.add (jSeparatorFicheiro1);
        jMenuFicheiro.add (jItemFicheiroSair);

            jItemFicheiroAbrir.setEnabled (true);
            jItemFicheiroAbrir.setLabel ("Abrir");
            jItemFicheiroAbrir.addActionListener(new ActionListener () 
            {
                public void actionPerformed (ActionEvent event) 
                {
                    limpaFrameInicial ();
                    jFileChooser.showOpenDialog (jFileChooser);
                    if (GestorFicheiros.existeFicheiro (jFileChooser.getSelectedFile ().toString ()))
                    {
                        if (GestorFicheiros.leFicheiro ())
                        {
                            inicializa ();
                        }
                        else JOptionPane.showMessageDialog (null, "O programa não consegue ler o ficheiro correctamente", "Erro na leitura do ficheiro", JOptionPane.ERROR_MESSAGE);
                    }
                    else JOptionPane.showMessageDialog (null, "O ficheiro que deseja visualizar não existe", "Erro na abertura do ficheiro", JOptionPane.ERROR_MESSAGE);
                }
            });

            jSeparatorFicheiro1.setEnabled (true);

            jItemFicheiroSair.setEnabled (true);
            jItemFicheiroSair.setLabel ("Sair");
            jItemFicheiroSair.addActionListener(new ActionListener () 
            {
                public void actionPerformed (ActionEvent event) 
                {
                    System.exit (0);
                }
            });
        
        jMenuVer.setEnabled (true);
        jMenuVer.setLabel ("Ver");
        jMenuVer.add (jItemVerPolinomios);
        jMenuVer.add (jItemVerOperacoes);
            
            jItemVerPolinomios.setEnabled (false);
            jItemVerPolinomios.setLabel ("Polinomios");
            jItemVerPolinomios.addActionListener(new ActionListener () 
            {
                public void actionPerformed (ActionEvent event) 
                {
                    limpaFramePolinomios ();
                }
            });
            
            jItemVerOperacoes.setEnabled (false);
            jItemVerOperacoes.setLabel ("Operacoes");
            jItemVerOperacoes.addActionListener(new ActionListener () 
            {
                public void actionPerformed (ActionEvent event) 
                {
                    limpaFrameOperacoes ();
                }
            });

        jMenuAjuda.setEnabled (true);
        jMenuAjuda.setLabel ("Ajuda");
        jMenuAjuda.add (jItemAjudaConteudos);
        jMenuAjuda.add (jSeparatorAjuda);
        jMenuAjuda.add (jItemAjudaAcerca);
        
            jItemAjudaConteudos.setEnabled (true);
            jItemAjudaConteudos.setLabel ("Conteudos");
            
            jSeparatorAjuda.setEnabled (true);
            
            jItemAjudaAcerca.setEnabled (true);
            jItemAjudaAcerca.setLabel ("Acerca...");
        
        //Panel de Aprsentacao
        //jPanelApresentacao.setEnabled (true);
        jPanelApresentacao.setLayout (new GridBagLayout ());
        jPanelApresentacao.setSize (FRAME_WIDTH, FRAME_HEIGTH);
        
            jLabelApresentacaoTitulo.setEnabled (true);
            jLabelApresentacaoTitulo.setText ("Operacoes com Polinomios");
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
        jPanelApresentacao.add (jLabelApresentacaoTitulo, gridBagConstraints);
        
            jLabelApresentacaoDisciplina.setEnabled (true);
            jLabelApresentacaoDisciplina.setText ("Algoritmos e Estruturas de Dados I");
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
        jPanelApresentacao.add (jLabelApresentacaoDisciplina, gridBagConstraints);
        
            jLabelApresentacaoNomes.setEnabled (true);
            jLabelApresentacaoNomes.setText ("Andre Barbosa & Filipe Montenegro");
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 2;
        jPanelApresentacao.add (jLabelApresentacaoNomes, gridBagConstraints);
        
            jLabelApresentacaoData.setEnabled (true);
            jLabelApresentacaoData.setText ("2002/2003");
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 3;
        jPanelApresentacao.add (jLabelApresentacaoData, gridBagConstraints);
    }
    
    /** Inicializa as operacoes e os polinomios
     */    
    public void inicializa ()
    {
        LinkedList linhas = GestorFicheiros.getLinhasFicheiro ();
        for (int i = 0; i < linhas.size (); i++)
        {
            String aux = (String) linhas.get (i);
            polinomiosLidos.add (aux);
            Polinomio polinomio = new Polinomio (aux);
            if (polinomio.isPolinomio ())
            {
                polinomio.simplificaPolinomio ();
                polinomio.ordenaPolinomio ();
            }
            else
            {
                polinomiosLidos.remove (aux);
                Operacao operacao = new Operacao (aux);
                if (!(operacao.isOperacao ())) 
                {
                    operacoesLidas.remove (aux);
                    JOptionPane.showMessageDialog (null, "O programa não consegue identificar nenhum polinomio/operacão escrito correctamente na linha " + (i + 1), "Erro na conversao das linhas do ficheiro", JOptionPane.ERROR_MESSAGE);
                }
                else operacoesLidas.add (aux);
            }
        }
        polinomios = GestorPolinomios.getPolinomios ();
        operacoes = GestorOperacoes.getOperacoes ();
        
        //Panel dos Polinomios
        jPanelPolinomios.setEnabled (false);
        jPanelPolinomios.setLayout (new GridBagLayout ());
        jPanelPolinomios.setSize (FRAME_WIDTH, FRAME_HEIGTH);
        
            jLabelPolinomios.setEnabled (true);
            jLabelPolinomios.setText ("Polinomios");
        jPanelPolinomios.add (jLabelPolinomios);
        
            numPolinomios = polinomios.size ();
            Vector columnsPolinomiosNames = new Vector ();
            columnsPolinomiosNames.add ("Nome");
            columnsPolinomiosNames.add ("Valor lido");
            columnsPolinomiosNames.add ("Valor Final");
            Vector rowsPolinomios = new Vector ();
            rowsPolinomios.add (columnsPolinomiosNames);
            
            String maxLido = "Valor Lido";
            String maxFinal = "Valor final";
            
            for (int i = 0; i < numPolinomios; i++)
            {
                Vector linhasPolinomios = new Vector ();
                Polinomio polinomio = (Polinomio) polinomios.get (i);
                linhasPolinomios.add (polinomio.getNome ());
                String valorLido = (String) polinomiosLidos.get (i);
                
                if(valorLido.substring (4, valorLido.length ()).length() > maxLido.length())
                    maxLido = valorLido;
                
                String str = polinomio.getResto ();
                
                if(str.length() > maxFinal.length())
                    maxFinal = str;
                
                linhasPolinomios.add (valorLido.substring (4, valorLido.length ()));
                if (str.equals ("")) linhasPolinomios.add ("0");
                else linhasPolinomios.add (str); 
                rowsPolinomios.add (linhasPolinomios);
            }
            
            jTablePolinomios = new JTable (rowsPolinomios, columnsPolinomiosNames);
            jTablePolinomios.setEnabled (false);
            
            TableColumnModel tableColumnModelPolinomios = jTablePolinomios.getColumnModel ();
            TableColumn tableColumnPolinomios;

            (tableColumnPolinomios = tableColumnModelPolinomios.getColumn (0)).setPreferredWidth (75);
            (tableColumnPolinomios = tableColumnModelPolinomios.getColumn (1)).setPreferredWidth (maxLido.length () * 8);
            (tableColumnPolinomios = tableColumnModelPolinomios.getColumn (2)).setPreferredWidth (maxFinal.length () * 8);

        jPanelPolinomios.add (jTablePolinomios, gridBagConstraints);
        
        //Panel das Operacoes
        jPanelOperacoes.setEnabled (false);
        jPanelOperacoes.setLayout (new GridBagLayout ());
        jPanelOperacoes.setSize (FRAME_WIDTH, FRAME_HEIGTH);
        
            jLabelOperacoes.setEnabled (true);
            jLabelOperacoes.setText ("Operacoes");
        jPanelOperacoes.add (jLabelOperacoes);
        
            numOperacoes = operacoes.size ();
            Vector columnsOperacoesNames = new Vector ();
            columnsOperacoesNames.add ("Nome");
            columnsOperacoesNames.add ("Expressao");
            columnsOperacoesNames.add ("Resultado");
            Vector rowsOperacoes = new Vector ();
            rowsOperacoes.add (columnsOperacoesNames);
            
            String maxExp = "Expressao";
            String maxRes = "Resultado";
            
            for (int i = 0; i < numOperacoes; i++)
            {
                Vector linhasOperacoes = new Vector ();
                Operacao operacao = (Operacao) operacoes.get (i);
                linhasOperacoes.add (operacao.getNome ());
                String valorLido = (String) operacoesLidas.get (i);
                
                if(valorLido.substring (4, valorLido.length ()).length() > maxExp.length())
                    maxExp = valorLido;
                
                String str = Calculadora.calcula (Calculadora.infixaPosFixa (operacao));
                
                String op = operacao.toString ();
                char opAux[] = op.toCharArray ();
                for (int j = 0; j < op.length (); j++)
                {
                    if (str.equals ("0")) if (opAux[j] == '/') JOptionPane.showMessageDialog (null, "A expressão aritmética " + operacao.getNome () + " inclui operações de divisão não exacta", "Erro na divisão de polinomios", JOptionPane.ERROR_MESSAGE);
                }
                
                if(str.length() > maxRes.length())
                    maxRes = str;
                
                linhasOperacoes.add (valorLido.substring (4, valorLido.length ()));
                linhasOperacoes.add (str);
                rowsOperacoes.add (linhasOperacoes);
            }
            
            jTableOperacoes = new JTable (rowsOperacoes, columnsOperacoesNames);
            jTableOperacoes.setEnabled (false);
            
            TableColumnModel tableColumnModelOperacoes = jTableOperacoes.getColumnModel ();
            TableColumn tableColumnOperacoes;

            (tableColumnOperacoes = tableColumnModelOperacoes.getColumn (0)).setPreferredWidth (75);
            (tableColumnOperacoes = tableColumnModelOperacoes.getColumn (1)).setPreferredWidth (maxExp.length () * 8);
            (tableColumnOperacoes = tableColumnModelOperacoes.getColumn (2)).setPreferredWidth (maxRes.length () * 8);
            
        jPanelOperacoes.add (jTableOperacoes, gridBagConstraints);
        
        if (polinomios.size () != 0) jItemVerPolinomios.setEnabled (true);
        if (operacoes.size () != 0) jItemVerOperacoes.setEnabled (true);
    }
    
    /** Limpa o painel inicial
     */    
    public void limpaFrameInicial ()
    {
        this.getContentPane().remove(jPanelApresentacao);
        this.repaint();
    }
    
    /** Limpa o painel dos polinomios
     */    
    public void limpaFramePolinomios ()
    {
        jPanelOperacoes.setVisible(false);
        jPanelPolinomios.setVisible(true);
        this.repaint();
    }
    
    /** Limpa o painel das operacoes
     */    
    public void limpaFrameOperacoes ()
    {
        jPanelOperacoes.setVisible(true);
        jPanelPolinomios.setVisible(false);
        this.repaint();
    }
    
    /** Metodo executavel da classe Interface
     * @param args Argumentos iniciais
     */    
    public static void main (String[] args)
    {
        new Interface ().show ();
    }
}