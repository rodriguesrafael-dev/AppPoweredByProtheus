package br.grupolider.view.ui;

import br.grupolider.controller.dto.BemDTO;
import br.grupolider.controller.dto.FuncionarioDTO;
import br.grupolider.controller.dto.ProdutoDTO;
import br.grupolider.controller.dto.VeiculoParadoDTO;
import br.grupolider.model.table.FuncionarioTCR;
import br.grupolider.model.table.FuncionarioTM;
import br.grupolider.model.table.PesquisaBemTCR;
import br.grupolider.model.table.PesquisaBemTM;
import br.grupolider.model.table.PesquisaProdutoTCR;
import br.grupolider.model.table.PesquisaProdutoTM;
import br.grupolider.model.table.UltimaSaidaProdutoTCR;
import br.grupolider.model.table.UltimaSaidaProdutoTM;
import br.grupolider.model.table.VeiculoParadoTCR;
import br.grupolider.model.table.VeiculoParadoTM;
import br.grupolider.model.vo.BemVO;
import br.grupolider.model.vo.FuncionarioVO;
import br.grupolider.model.vo.ProdutoVO;
import br.grupolider.model.vo.VeiculoParadoVO;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rodrigues rafael
 *
 * @version 3.0.1
 */
public class JFDesktop extends javax.swing.JFrame {

    private PesquisaProdutoTM pesquisaTM;
    private final PesquisaProdutoTCR pesquisaTCR;

    private PesquisaBemTM pesquisaBemTM;
    private final PesquisaBemTCR pesquisaBemTCR;

    private VeiculoParadoTM veiculoTM;
    private final VeiculoParadoTCR veiculoTCR;

    private FuncionarioTM funcionarioTM;
    private final FuncionarioTCR funcionarioTCR;
    
    private UltimaSaidaProdutoTM ultimaSaidaProdutoTM;
    private final UltimaSaidaProdutoTCR ultimaSaidaProdutoTCR;

    public JFDesktop() {
        initComponents();
        logoApp();
        screenResolution();
        jPanelOneOnCenter();
        closeWindowESC();

        pesquisaTM = new PesquisaProdutoTM();
        pesquisaTCR = new PesquisaProdutoTCR();

        pesquisaBemTM = new PesquisaBemTM();
        pesquisaBemTCR = new PesquisaBemTCR();

        veiculoTM = new VeiculoParadoTM();
        veiculoTCR = new VeiculoParadoTCR();

        funcionarioTM = new FuncionarioTM();
        funcionarioTCR = new FuncionarioTCR();
        
        ultimaSaidaProdutoTM = new UltimaSaidaProdutoTM();
        ultimaSaidaProdutoTCR = new UltimaSaidaProdutoTCR();

        jtfCodProduto.grabFocus();

        formatPesquisaProdutoTable();
        formatUltimaSaidaProdutoTable();
        formatPesquisaBemTable();
        formatVeiculoParadoTable();
        pesquisarFuncionario();
    }

    private void screenResolution() {
        this.setLocationRelativeTo(null);
        this.setSize(1200, 680);
        Insets in = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();

        int width = display.width - (in.left + in.top);
        int height = display.height - (in.top + in.bottom);
        this.setSize(width, height);
    }

    private void jPanelOneOnCenter() {
        int widthScreen = this.getWidth();
        int heightScreen = this.getHeight();

        int widthjPanelDesk = jPanelDesk.getWidth();
        int heightjPanelDesk = jPanelDesk.getHeight();
        this.setSize(1200, 680);

        int novoX = (widthScreen - widthjPanelDesk);
        int novoY = (heightScreen - heightjPanelDesk);

        jPanelDesk.getParent().setLayout(new GridBagLayout());
        jPanelDesk.setSize(new Dimension(novoX, novoY));
        jPanelDesk.repaint();
    }
    
    private void formatPesquisaProdutoTable() {
        jtPesquisaProduto.setModel(pesquisaTM);
        jtPesquisaProduto.setDefaultRenderer(Object.class, pesquisaTCR);
        pesquisaTCR.getFormat(jtPesquisaProduto);
    }
    
    private void formatPesquisaBemTable() {
        jtPesquisaBem.setModel(pesquisaBemTM);
        jtPesquisaBem.setDefaultRenderer(Object.class, pesquisaBemTCR);
        pesquisaBemTCR.getFormat(jtPesquisaBem);
    }

    private void formatVeiculoParadoTable() {
        jtVeiculoParado.setModel(veiculoTM);
        jtVeiculoParado.setDefaultRenderer(Object.class, veiculoTCR);
        veiculoTCR.getFormat(jtVeiculoParado);
        getVeiculoParado();
    }

    private void getVeiculoParado() {
        VeiculoParadoDTO vd = new VeiculoParadoDTO();
        List<VeiculoParadoVO> listVeiculo;

        listVeiculo = vd.getVeiculoParado();
        veiculoTM = new VeiculoParadoTM((ArrayList<VeiculoParadoVO>) listVeiculo);
        jtVeiculoParado.setModel(veiculoTM);
        jlVeiculosParados.setText(Integer.toString(jtVeiculoParado.getRowCount()));
    }

    //pesquisar produto por descricao e preencher tabela
    private void pesquisarProduto() {
        ProdutoDTO pd = new ProdutoDTO();
        List<ProdutoVO> listProd;

        String produto = jtfPesquisa.getText().toUpperCase();
        listProd = pd.searchDescProduto(produto);
        pesquisaTM = new PesquisaProdutoTM((ArrayList<ProdutoVO>) listProd);
        jtPesquisaProduto.setModel(pesquisaTM);

        formatPesquisaProdutoTable();
        jtfPesquisa.grabFocus();
        jtfPesquisa.selectAll();
    }
    
    private void ultimaMovimentacaoProduto(String codigo) {
        ProdutoDTO pd = new ProdutoDTO();
        List<ProdutoVO> listProd;

        codigo = jtfCodProduto.getText();
        listProd = pd.movimentacaoProduto(codigo);
        ultimaSaidaProdutoTM = new UltimaSaidaProdutoTM((ArrayList<ProdutoVO>) listProd);
        jtUltimaSaidaProduto.setModel(ultimaSaidaProdutoTM);

        formatUltimaSaidaProdutoTable();
        jtfCodProduto.grabFocus();
        jtfCodProduto.selectAll();
    }

    private void pesquisarBem() {
        BemDTO bd = new BemDTO();
        List<BemVO> listBem;

        String produto = jtfPesquisaBemTabela.getText().toUpperCase();
        listBem = bd.searchDescBem(produto);
        pesquisaBemTM = new PesquisaBemTM((ArrayList<BemVO>) listBem);
        jtPesquisaBem.setModel(pesquisaBemTM);

        formatPesquisaBemTable();
        jtfPesquisaBemTabela.grabFocus();
        jtfPesquisaBemTabela.selectAll();
    }

    //produto selecionado da tabela de pesquisa por descricao
    private void produtoSelecionado() throws ParseException {
        ProdutoVO pv;
        ProdutoDTO pd = new ProdutoDTO();

        int linhaSelecionada = jtPesquisaProduto.getSelectedRow();
        Object obj = jtPesquisaProduto.getValueAt(linhaSelecionada, 0);
        String codigo = obj.toString();
        
        String[] words = {
            codigo
        };

        StringBuilder str = new StringBuilder();
        for (String word : words) {
            str.append(word);
        }

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection selection = new StringSelection(str.toString());
        clipboard.setContents(selection, selection);

        try {
            pv = pd.searchProduto(codigo);
            jtfCodProduto.setText(pv.getCodigo());
            jtfDescProduto.setText(pv.getDescricao());
            jtfSaldoAtual.setText(pv.getSaldo_atual());
            jtfUnMedida.setText(pv.getUn_medida());
            jtfEmbalagem.setText(pv.getQuant_embalagem());           
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "<html>"
                    + "<h3><font color = navy blue face = "
                    + "arial><b>Produto sem movimentacao!</b></h3>",
                    "ATENCAO!", JOptionPane.WARNING_MESSAGE);
        }
        jtfCodProduto.grabFocus();
        jtfCodProduto.selectAll();
    }

    //produto selecionado da tabela de pesquisa por descricao
    private void bemSelecionado() throws ParseException {
        BemVO bv;
        BemDTO bd = new BemDTO();

        int linhaSelecionada = jtPesquisaBem.getSelectedRow();
        Object obj = jtPesquisaBem.getValueAt(linhaSelecionada, 0);
        String codigo = obj.toString();

        try {
            bv = bd.searchBem(codigo);
            jtfCodBem.setText(bv.getCod_bem());
            jtfDescBem.setText(bv.getDesc_bem());
            jtfKmAtual.setText(bv.getKm());
            jtfDataUltKM.setText(bv.getDt_ult_km());
            jtfFilial.setText(bv.getFilial());
            jtfPlaca.setText(bv.getPlaca());
            jtfChassi.setText(bv.getChassi());
            jtfRenavam.setText(bv.getRenavam());
            jtfAnoFab.setText(bv.getAno_fab());
            jtfModelo.setText(bv.getModelo());
            jtfDescModelo.setText(bv.getDesc_modelo());
            jtfSerie.setText(bv.getSerie());
            jtfFabricante.setText(bv.getFabricante());
            jtfTipoVeiculo.setText(bv.getTipo_veiculo());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "<html>"
                    + "<h3><font color = navy blue face = "
                    + "arial><b>Bem nao cadastrado!</b></h3>",
                    "ATENCAO!", JOptionPane.WARNING_MESSAGE);
        }
        jtfCodBem.grabFocus();
        jtfCodBem.selectAll();
    }

    private void funcionarioSelecionado() throws ParseException {
       
        int linhaSelecionada = jtFuncionario.getSelectedRow();
        Object obj = jtFuncionario.getValueAt(linhaSelecionada, 0);
        String codigo = obj.toString();
        
        String[] words = {
            codigo
        };

        StringBuilder str = new StringBuilder();
        for (String word : words) {
            str.append(word);
        }

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection selection = new StringSelection(str.toString());
        clipboard.setContents(selection, selection);
    }

    //pesquisar produto por descricao e preencher tabela
    private void pesquisarFuncionario() {
        FuncionarioDTO fd = new FuncionarioDTO();
        List<FuncionarioVO> listFuncionario;

        listFuncionario = fd.searchFuncionario();
        funcionarioTM = new FuncionarioTM((ArrayList<FuncionarioVO>) listFuncionario);
        jtFuncionario.setModel(funcionarioTM);

        formatFuncionarioTable();

        jtfFuncionario.grabFocus();
        jtfFuncionario.selectAll();
    }

    public void filtrarNomeNaTabela() {
        TableRowSorter sorter = new TableRowSorter(funcionarioTM);
        jtFuncionario.setRowSorter(sorter);
        String texto = jtfFuncionario.getText();

        try {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "<html>"
                    + "<h3><font color = navy blue face = "
                    + "arial><b>Funcionario nao cadastrado!</b></h3>",
                    "ATENCAO!", JOptionPane.ERROR_MESSAGE);
        }
        jtfFuncionario.grabFocus();
        jtfFuncionario.selectAll();
    }

    private void formatFuncionarioTable() {
        jtFuncionario.setModel(funcionarioTM);
        jtFuncionario.setDefaultRenderer(Object.class, funcionarioTCR);
        funcionarioTCR.getFormat(jtFuncionario);
    }
    
    private void formatUltimaSaidaProdutoTable() {
        jtUltimaSaidaProduto.setModel(ultimaSaidaProdutoTM);
        jtUltimaSaidaProduto.setDefaultRenderer(Object.class, ultimaSaidaProdutoTCR);
        ultimaSaidaProdutoTCR.getFormat(jtUltimaSaidaProduto);
    }

    private void searchProduto() throws ParseException {
        ProdutoVO pv;
        ProdutoDTO pd = new ProdutoDTO();
        String codigo = jtfCodProduto.getText();
       
        try {
            pv = pd.searchProduto(codigo);
            jtfDescProduto.setText(pv.getDescricao());
            jtfSaldoAtual.setText(pv.getSaldo_atual());
            jtfUnMedida.setText(pv.getUn_medida());
            jtfEmbalagem.setText(pv.getQuant_embalagem());
        } catch (NullPointerException ex) {

            JOptionPane.showMessageDialog(null,
                    "<html><h3><font color = navy blue face = "
                    + "arial><b>Produto nao cadastrado \n"
                    + "<html><h3><font color = navy blue face = "
                    + "arial><b>ou sem movimentacao de estoque.",
                    "ATENCAO!", JOptionPane.WARNING_MESSAGE);
        }
        jtfCodProduto.grabFocus();
        jtfCodProduto.selectAll();
    }

    private void searchBem() throws ParseException {
        BemVO bv;
        BemDTO bd = new BemDTO();
        String bem = jtfCodBem.getText();

        try {
            bv = bd.searchBem(bem);
            jtfDescBem.setText(bv.getDesc_bem());
            jtfKmAtual.setText(bv.getKm());
            jtfDataUltKM.setText(bv.getDt_ult_km());
            jtfFilial.setText(bv.getFilial());
            jtfPlaca.setText(bv.getPlaca());
            jtfChassi.setText(bv.getChassi());
            jtfRenavam.setText(bv.getRenavam());
            jtfAnoFab.setText(bv.getAno_fab());
            jtfModelo.setText(bv.getModelo());
            jtfDescModelo.setText(bv.getDesc_modelo());
            jtfSerie.setText(bv.getSerie());
            jtfFabricante.setText(bv.getFabricante());
            jtfTipoVeiculo.setText(bv.getTipo_veiculo());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "<html>"
                    + "<h3><font color = navy blue face = "
                    + "arial><b>Bem nao cadastrado!</b></h3>",
                    "ATENCAO!", JOptionPane.WARNING_MESSAGE);
        }
        jtfCodBem.grabFocus();
        jtfCodBem.selectAll();
    }

    private void copyDescBem() {
        String[] words = {
            "FABRICANTE: ", jtfFabricante.getText(), "\n",
            "FROTA: ", jtfCodBem.getText(), "\n",
            "MODELO: ", jtfModelo.getText(), "\n",
            "CHASSI: ", jtfChassi.getText(), "\n",
            "ANO: ", jtfAnoFab.getText()
        };

        StringBuilder str = new StringBuilder();
        for (String word : words) {
            str.append(word);
        }

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection selection = new StringSelection(str.toString());
        clipboard.setContents(selection, selection);
    }

    private void clearFieldsProduto() {
        jtfDescProduto.setText(null);
        jtfSaldoAtual.setText(null);
        jtfUnMedida.setText(null);
        jtfEmbalagem.setText(null);
    }

    private void cleanFieldsBem() {
        jtfPlaca.setText(null);
        jtfAnoFab.setText(null);
        jtfChassi.setText(null);
        jtfRenavam.setText(null);
        jtfFabricante.setText(null);
        jtfDescBem.setText(null);
        jtfFilial.setText(null);
        jtfDescModelo.setText(null);
        jtfKmAtual.setText(null);
        jtfDataUltKM.setText(null);
        jtfSerie.setText(null);
        jtfModelo.setText(null);
        jtfTipoVeiculo.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelDesk = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPaneDesk = new javax.swing.JTabbedPane();
        jpConsultaEstoque = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jtfCodProduto = new javax.swing.JTextField();
        jbtnPesquisarCodProduto = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jtfDescProduto = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jtfSaldoAtual = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jtfUnMedida = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jtfEmbalagem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jtfPesquisa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbtnPesquisa = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtPesquisaProduto = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtUltimaSaidaProduto = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtUltimaEntradaProduto = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jtfCodBem = new javax.swing.JTextField();
        jbtnPesquisarCodBem = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jtfDescBem = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jtfPlaca = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jtfAnoFab = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jtfDataUltKM = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jtfModelo = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jtfChassi = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jtfFilial = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jtfDescModelo = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jtfRenavam = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jtfKmAtual = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jtfSerie = new javax.swing.JTextField();
        jbtnCopyPaste = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtPesquisaBem = new javax.swing.JTable();
        jtfPesquisaBemTabela = new javax.swing.JTextField();
        jbtnPesquisaBemTabela = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jtfFabricante = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jtfTipoVeiculo = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtVeiculoParado = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jlVeiculosParados = new javax.swing.JLabel();
        jbtnAtualizar = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtFuncionario = new javax.swing.JTable();
        jtfFuncionario = new javax.swing.JTextField();
        jbtnPesquisarFuncionario = new javax.swing.JButton();
        jMenuBarPrincipal = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OficinaMGR 3.1.0");
        setSize(new java.awt.Dimension(1200, 700));

        jPanelDesk.setPreferredSize(new java.awt.Dimension(1200, 600));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/logoGrupoLider_transparent.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Powered By");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/protheus100x41.png"))); // NOI18N

        jTabbedPaneDesk.setForeground(new java.awt.Color(10, 36, 106));
        jTabbedPaneDesk.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTabbedPaneDesk.setPreferredSize(new java.awt.Dimension(1000, 600));
        jTabbedPaneDesk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPaneDeskMouseClicked(evt);
            }
        });

        jpConsultaEstoque.setBackground(new java.awt.Color(0, 102, 255));
        jpConsultaEstoque.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "CONSULTA ESTOQUE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 23), new java.awt.Color(255, 255, 255)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)))); // NOI18N
        jpConsultaEstoque.setMaximumSize(new java.awt.Dimension(1200, 700));
        jpConsultaEstoque.setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Codigo Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfCodProduto.setBackground(new java.awt.Color(255, 255, 255));
        jtfCodProduto.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfCodProduto.setForeground(new java.awt.Color(10, 36, 106));
        jtfCodProduto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfCodProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCodProdutoActionPerformed(evt);
            }
        });

        jbtnPesquisarCodProduto.setBackground(new java.awt.Color(204, 204, 204));
        jbtnPesquisarCodProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/search-16.png"))); // NOI18N
        jbtnPesquisarCodProduto.setToolTipText("Ir para a busca");
        jbtnPesquisarCodProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPesquisarCodProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jtfCodProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jbtnPesquisarCodProduto))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPesquisarCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnPesquisarCodProduto, jtfCodProduto});

        jPanel5.setBackground(new java.awt.Color(102, 153, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descricao Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfDescProduto.setEditable(false);
        jtfDescProduto.setBackground(new java.awt.Color(204, 204, 204));
        jtfDescProduto.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfDescProduto.setForeground(new java.awt.Color(10, 36, 106));
        jtfDescProduto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtfDescProduto.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jtfDescProduto)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jtfDescProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel7.setBackground(new java.awt.Color(10, 36, 106));
        jPanel7.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Estoque Atual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(255, 255, 255)))); // NOI18N

        jtfSaldoAtual.setEditable(false);
        jtfSaldoAtual.setBackground(new java.awt.Color(204, 204, 204));
        jtfSaldoAtual.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfSaldoAtual.setForeground(new java.awt.Color(10, 36, 106));
        jtfSaldoAtual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfSaldoAtual.setFocusable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfSaldoAtual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(102, 153, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Un. Med", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfUnMedida.setEditable(false);
        jtfUnMedida.setBackground(new java.awt.Color(204, 204, 204));
        jtfUnMedida.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfUnMedida.setForeground(new java.awt.Color(10, 36, 106));
        jtfUnMedida.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtfUnMedida.setFocusable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfUnMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfUnMedida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(102, 153, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BD/LT/PC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfEmbalagem.setEditable(false);
        jtfEmbalagem.setBackground(new java.awt.Color(204, 204, 204));
        jtfEmbalagem.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfEmbalagem.setForeground(new java.awt.Color(10, 36, 106));
        jtfEmbalagem.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfEmbalagem.setFocusable(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfEmbalagem, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfEmbalagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jtfPesquisa.setBackground(new java.awt.Color(255, 255, 255));
        jtfPesquisa.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfPesquisa.setForeground(new java.awt.Color(10, 36, 106));
        jtfPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfPesquisaMouseClicked(evt);
            }
        });
        jtfPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPesquisaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Consulta por descricao do produto");

        jbtnPesquisa.setBackground(new java.awt.Color(204, 204, 204));
        jbtnPesquisa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jbtnPesquisa.setForeground(new java.awt.Color(51, 51, 51));
        jbtnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/search-16.png"))); // NOI18N
        jbtnPesquisa.setText("Pesquisar");
        jbtnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPesquisaActionPerformed(evt);
            }
        });

        jtPesquisaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtPesquisaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPesquisaProdutoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtPesquisaProduto);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jtfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jbtnPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPesquisa))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnPesquisa, jtfPesquisa});

        jPanel27.setBackground(new java.awt.Color(0, 102, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ULTIMAS SAIDAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 255, 255)))); // NOI18N

        jtUltimaSaidaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jtUltimaSaidaProduto);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane5)
                .addGap(6, 6, 6))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jPanel28.setBackground(new java.awt.Color(0, 102, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ULTIMAS ENTRADAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 255, 255)))); // NOI18N

        jtUltimaEntradaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jtUltimaEntradaProduto);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane6)
                .addGap(6, 6, 6))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jpConsultaEstoqueLayout = new javax.swing.GroupLayout(jpConsultaEstoque);
        jpConsultaEstoque.setLayout(jpConsultaEstoqueLayout);
        jpConsultaEstoqueLayout.setHorizontalGroup(
            jpConsultaEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaEstoqueLayout.createSequentialGroup()
                .addGroup(jpConsultaEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpConsultaEstoqueLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpConsultaEstoqueLayout.setVerticalGroup(
            jpConsultaEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaEstoqueLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpConsultaEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpConsultaEstoqueLayout.createSequentialGroup()
                        .addGroup(jpConsultaEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))))
        );

        jpConsultaEstoqueLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel2, jPanel5, jPanel7, jPanel8, jPanel9});

        jTabbedPaneDesk.addTab("Estoque", jpConsultaEstoque);

        jPanel6.setBackground(new java.awt.Color(0, 102, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "CONSULTA BEM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 23), new java.awt.Color(255, 255, 255)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(51, 51, 51));

        jPanel10.setBackground(new java.awt.Color(102, 153, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Codigo Bem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfCodBem.setBackground(new java.awt.Color(255, 255, 255));
        jtfCodBem.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfCodBem.setForeground(new java.awt.Color(10, 36, 106));
        jtfCodBem.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfCodBem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCodBemActionPerformed(evt);
            }
        });

        jbtnPesquisarCodBem.setBackground(new java.awt.Color(204, 204, 204));
        jbtnPesquisarCodBem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/search-16.png"))); // NOI18N
        jbtnPesquisarCodBem.setToolTipText("Pesquisar Bem");
        jbtnPesquisarCodBem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPesquisarCodBemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jtfCodBem, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jbtnPesquisarCodBem)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfCodBem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPesquisarCodBem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnPesquisarCodBem, jtfCodBem});

        jPanel11.setBackground(new java.awt.Color(102, 153, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descricao Bem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfDescBem.setEditable(false);
        jtfDescBem.setBackground(new java.awt.Color(204, 204, 204));
        jtfDescBem.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfDescBem.setForeground(new java.awt.Color(10, 36, 106));
        jtfDescBem.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtfDescBem.setFocusable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfDescBem)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfDescBem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(102, 153, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Placa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfPlaca.setEditable(false);
        jtfPlaca.setBackground(new java.awt.Color(204, 204, 204));
        jtfPlaca.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfPlaca.setForeground(new java.awt.Color(10, 36, 106));
        jtfPlaca.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfPlaca.setFocusable(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfPlaca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(102, 153, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ano Fab.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfAnoFab.setEditable(false);
        jtfAnoFab.setBackground(new java.awt.Color(204, 204, 204));
        jtfAnoFab.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfAnoFab.setForeground(new java.awt.Color(10, 36, 106));
        jtfAnoFab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfAnoFab.setFocusable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfAnoFab, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfAnoFab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(102, 153, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Ult. KM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfDataUltKM.setEditable(false);
        jtfDataUltKM.setBackground(new java.awt.Color(204, 204, 204));
        jtfDataUltKM.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfDataUltKM.setForeground(new java.awt.Color(10, 36, 106));
        jtfDataUltKM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDataUltKM.setFocusable(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfDataUltKM, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfDataUltKM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(102, 153, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Modelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfModelo.setEditable(false);
        jtfModelo.setBackground(new java.awt.Color(204, 204, 204));
        jtfModelo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfModelo.setForeground(new java.awt.Color(10, 36, 106));
        jtfModelo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfModelo.setFocusable(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfModelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(102, 153, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chassi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfChassi.setEditable(false);
        jtfChassi.setBackground(new java.awt.Color(204, 204, 204));
        jtfChassi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfChassi.setForeground(new java.awt.Color(10, 36, 106));
        jtfChassi.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtfChassi.setFocusable(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfChassi, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfChassi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(102, 153, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Filial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfFilial.setEditable(false);
        jtfFilial.setBackground(new java.awt.Color(204, 204, 204));
        jtfFilial.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfFilial.setForeground(new java.awt.Color(10, 36, 106));
        jtfFilial.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtfFilial.setFocusable(false);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfFilial)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfFilial, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(102, 153, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descricao Modelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfDescModelo.setEditable(false);
        jtfDescModelo.setBackground(new java.awt.Color(204, 204, 204));
        jtfDescModelo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfDescModelo.setForeground(new java.awt.Color(10, 36, 106));
        jtfDescModelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDescModelo.setFocusable(false);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfDescModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfDescModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(102, 153, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Renavam", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfRenavam.setEditable(false);
        jtfRenavam.setBackground(new java.awt.Color(204, 204, 204));
        jtfRenavam.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfRenavam.setForeground(new java.awt.Color(10, 36, 106));
        jtfRenavam.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfRenavam.setFocusable(false);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfRenavam, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfRenavam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(102, 153, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Km Atual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfKmAtual.setEditable(false);
        jtfKmAtual.setBackground(new java.awt.Color(204, 204, 204));
        jtfKmAtual.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfKmAtual.setForeground(new java.awt.Color(10, 36, 106));
        jtfKmAtual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfKmAtual.setFocusable(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfKmAtual, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfKmAtual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(102, 153, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Serie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfSerie.setEditable(false);
        jtfSerie.setBackground(new java.awt.Color(204, 204, 204));
        jtfSerie.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfSerie.setForeground(new java.awt.Color(10, 36, 106));
        jtfSerie.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfSerie.setFocusable(false);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfSerie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jbtnCopyPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/copy_paste32.png"))); // NOI18N
        jbtnCopyPaste.setToolTipText("Copiar informacoes Bem");
        jbtnCopyPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCopyPasteActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jtPesquisaBem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtPesquisaBem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPesquisaBemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtPesquisaBem);

        jtfPesquisaBemTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPesquisaBemTabelaActionPerformed(evt);
            }
        });

        jbtnPesquisaBemTabela.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jbtnPesquisaBemTabela.setForeground(new java.awt.Color(51, 51, 51));
        jbtnPesquisaBemTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/search-16.png"))); // NOI18N
        jbtnPesquisaBemTabela.setText("Pesquisar");
        jbtnPesquisaBemTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPesquisaBemTabelaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pesquisar Bem (placa, serie, renavam ou descricao)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jtfPesquisaBemTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnPesquisaBemTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 531, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPesquisaBemTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPesquisaBemTabela))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnPesquisaBemTabela, jtfPesquisaBemTabela});

        jPanel23.setBackground(new java.awt.Color(102, 153, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Fabricante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfFabricante.setEditable(false);
        jtfFabricante.setBackground(new java.awt.Color(204, 204, 204));
        jtfFabricante.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfFabricante.setForeground(new java.awt.Color(10, 36, 106));
        jtfFabricante.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtfFabricante.setFocusable(false);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfFabricante)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfFabricante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(102, 153, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo Bem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Code Pro", 1, 14), new java.awt.Color(51, 51, 51)))); // NOI18N

        jtfTipoVeiculo.setEditable(false);
        jtfTipoVeiculo.setBackground(new java.awt.Color(204, 204, 204));
        jtfTipoVeiculo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jtfTipoVeiculo.setForeground(new java.awt.Color(10, 36, 106));
        jtfTipoVeiculo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfTipoVeiculo.setFocusable(false);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfTipoVeiculo)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtfTipoVeiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jbtnCopyPaste, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnCopyPaste, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel12, jPanel13, jPanel14, jPanel15, jPanel16, jPanel18, jPanel19, jPanel20, jPanel21});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel10, jbtnCopyPaste});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel11, jPanel17});

        jTabbedPaneDesk.addTab("Bem", jPanel6);

        jPanel22.setBackground(new java.awt.Color(0, 102, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "VEICULO PARADO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 23), new java.awt.Color(255, 255, 255)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)))); // NOI18N
        jPanel22.setForeground(new java.awt.Color(51, 51, 51));

        jtVeiculoParado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtVeiculoParado);

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total de veículos parados: ");

        jlVeiculosParados.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jlVeiculosParados.setForeground(new java.awt.Color(255, 255, 255));
        jlVeiculosParados.setText("jLabel4");

        jbtnAtualizar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jbtnAtualizar.setForeground(new java.awt.Color(51, 51, 51));
        jbtnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/btn_refresh.png"))); // NOI18N
        jbtnAtualizar.setText("Atualizar");
        jbtnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAtualizarActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(0, 0, 255));

        jTextField2.setBackground(new java.awt.Color(34, 139, 34));

        jTextField3.setBackground(new java.awt.Color(255, 0, 0));

        jTextField4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Aguardando material");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(34, 139, 34));
        jLabel8.setText("Material disponivel CDAM");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Servico externo concluido");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Aguardando servico externo");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(137, 137, 137)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnAtualizar)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(20, 20, 20)
                .addComponent(jlVeiculosParados)
                .addGap(20, 20, 20))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jlVeiculosParados)
                            .addComponent(jbtnAtualizar)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPaneDesk.addTab("Veiculo Parado", jPanel22);

        jPanel25.setBackground(new java.awt.Color(0, 102, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "FUNCIONÁRIOS MANUTENÇÃO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 23), new java.awt.Color(255, 255, 255)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)))); // NOI18N
        jPanel25.setForeground(new java.awt.Color(51, 51, 51));

        jtFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtFuncionario);

        jtfFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfFuncionarioActionPerformed(evt);
            }
        });

        jbtnPesquisarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/grupolider/image/search-16.png"))); // NOI18N
        jbtnPesquisarFuncionario.setText("Pesquisar");
        jbtnPesquisarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPesquisarFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnPesquisarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPesquisarFuncionario))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );

        jPanel25Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnPesquisarFuncionario, jtfFuncionario});

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1151, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneDesk.addTab("Funcionario", jPanel4);

        javax.swing.GroupLayout jPanelDeskLayout = new javax.swing.GroupLayout(jPanelDesk);
        jPanelDesk.setLayout(jPanelDeskLayout);
        jPanelDeskLayout.setHorizontalGroup(
            jPanelDeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeskLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelDeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDeskLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jTabbedPaneDesk, javax.swing.GroupLayout.DEFAULT_SIZE, 1153, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanelDeskLayout.setVerticalGroup(
            jPanelDeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeskLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelDeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addComponent(jTabbedPaneDesk, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jMenuBarPrincipal.setBackground(new java.awt.Color(204, 204, 204));
        jMenuBarPrincipal.setAlignmentY(0.5F);
        jMenuBarPrincipal.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jMenuBarPrincipal.setMaximumSize(new java.awt.Dimension(1200, 700));

        jMenu2.setForeground(new java.awt.Color(51, 51, 51));
        jMenu2.setText("Sobre este App");
        jMenu2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        jMenuItem1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem1.setText("Informacoes do App");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBarPrincipal.add(jMenu2);

        jMenu1.setForeground(new java.awt.Color(10, 36, 106));
        jMenu1.setText("Imprimir");
        jMenu1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBarPrincipal.add(jMenu1);

        setJMenuBar(jMenuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDesk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1193, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelDesk, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new JDSobreApp(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jtfCodProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCodProdutoActionPerformed
        clearFieldsProduto();
        try {
            searchProduto();
            ultimaMovimentacaoProduto(jtfCodProduto.getText());
        } catch (ParseException ex) {
            Logger.getLogger(JFDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtfCodProdutoActionPerformed

    private void jtfCodBemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCodBemActionPerformed
        cleanFieldsBem();
        try {
            searchBem();
        } catch (ParseException ex) {
            Logger.getLogger(JFDesktop.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtfCodBemActionPerformed

    private void jbtnCopyPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCopyPasteActionPerformed
        copyDescBem();
    }//GEN-LAST:event_jbtnCopyPasteActionPerformed

    private void jbtnPesquisarCodProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPesquisarCodProdutoActionPerformed
        try {
            searchProduto();
            ultimaMovimentacaoProduto(jtfCodProduto.getText());
        } catch (ParseException ex) {
            Logger.getLogger(JFDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnPesquisarCodProdutoActionPerformed

    private void jbtnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAtualizarActionPerformed
        getVeiculoParado();
    }//GEN-LAST:event_jbtnAtualizarActionPerformed

    private void jTabbedPaneDeskMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPaneDeskMouseClicked
        jtfCodBem.grabFocus();
    }//GEN-LAST:event_jTabbedPaneDeskMouseClicked

    private void jtfPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPesquisaActionPerformed
        pesquisarProduto();
    }//GEN-LAST:event_jtfPesquisaActionPerformed

    private void jbtnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPesquisaActionPerformed
        pesquisarProduto();
    }//GEN-LAST:event_jbtnPesquisaActionPerformed

    private void jtPesquisaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPesquisaProdutoMouseClicked
        clearFieldsProduto();
        try {
            produtoSelecionado();
            searchProduto();
            ultimaMovimentacaoProduto(jtfCodProduto.getText());
        } catch (ParseException ex) {
            Logger.getLogger(JFDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtPesquisaProdutoMouseClicked

    private void jbtnPesquisaBemTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPesquisaBemTabelaActionPerformed
        pesquisarBem();
    }//GEN-LAST:event_jbtnPesquisaBemTabelaActionPerformed

    private void jtfPesquisaBemTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPesquisaBemTabelaActionPerformed
        pesquisarBem();
    }//GEN-LAST:event_jtfPesquisaBemTabelaActionPerformed

    private void jtPesquisaBemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPesquisaBemMouseClicked
        cleanFieldsBem();
        try {
            bemSelecionado();
        } catch (ParseException ex) {
            Logger.getLogger(JFDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtPesquisaBemMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        try {
            screenshot();
        } catch (PrintException ex) {
            Logger.getLogger(JFDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jbtnPesquisarCodBemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPesquisarCodBemActionPerformed
        cleanFieldsBem();
        try {
            searchBem();
        } catch (ParseException ex) {
            Logger.getLogger(JFDesktop.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnPesquisarCodBemActionPerformed

    private void jtfPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfPesquisaMouseClicked
        jtfPesquisa.grabFocus();
        jtfPesquisa.selectAll();
    }//GEN-LAST:event_jtfPesquisaMouseClicked

    private void jtfFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFuncionarioActionPerformed
        filtrarNomeNaTabela();
    }//GEN-LAST:event_jtfFuncionarioActionPerformed

    private void jbtnPesquisarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPesquisarFuncionarioActionPerformed
        filtrarNomeNaTabela();
    }//GEN-LAST:event_jbtnPesquisarFuncionarioActionPerformed

    private void jtFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFuncionarioMouseClicked
        try {
            funcionarioSelecionado();
        } catch (ParseException ex) {
            Logger.getLogger(JFDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtFuncionarioMouseClicked

    private void screenshot() throws PrintException {
        Rectangle ALL_SCREEN = new Rectangle(Toolkit
                .getDefaultToolkit().getScreenSize());
        try {
            Robot robot = new Robot();
            File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\screenshot.png");
            BufferedImage bi = robot.createScreenCapture(ALL_SCREEN);
            ImageIO.write(bi, "png", file);

            toPrint(file);
            file.delete();
        } catch (AWTException | IOException e) {
        }

    }

    private void toPrint(File txt) {
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        PrintService[] printService
                = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PNG, null);
        PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.PNG;
        HashDocAttributeSet hashDocAttributeSet = new HashDocAttributeSet();

        try {
            FileInputStream stream = new FileInputStream(txt);
            Doc doc = new SimpleDoc(stream, docFlavor, hashDocAttributeSet);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
            printRequestAttributeSet.add(MediaSizeName.EXECUTIVE);

            //janela para escolher a impressora
            PrintService printServico = ServiceUI.printDialog(null, 600, 500, printService, impressoraPadrao, docFlavor, printRequestAttributeSet);

            //imprime direto sem escolher impressora
            //PrintService printServico = PrintServiceLookup.lookupDefaultPrintService();
            if (printServico != null) {
                DocPrintJob docPrintJob = printServico.createPrintJob();
                try {
                    docPrintJob.print(doc, printRequestAttributeSet);
                } catch (PrintException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }

    }

    //fechar janela pela tecla ESC
    private void closeWindowESC() {
        JRootPane jdCadastroFilial = getRootPane();
        jdCadastroFilial.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        jdCadastroFilial.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFDesktop.this.dispose();
            }
        });
    }

    //insert application logo
    private void logoApp() {
        String url = "/br/grupolider/image/logo_app-png.png";
        ImageIcon icon = new ImageIcon(this.getClass().getResource(url));
        setIconImage(icon.getImage());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFDesktop.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFDesktop.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFDesktop.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFDesktop.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JFDesktop().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelDesk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPaneDesk;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton jbtnAtualizar;
    private javax.swing.JButton jbtnCopyPaste;
    private javax.swing.JButton jbtnPesquisa;
    private javax.swing.JButton jbtnPesquisaBemTabela;
    private javax.swing.JButton jbtnPesquisarCodBem;
    private javax.swing.JButton jbtnPesquisarCodProduto;
    private javax.swing.JButton jbtnPesquisarFuncionario;
    private javax.swing.JLabel jlVeiculosParados;
    private javax.swing.JPanel jpConsultaEstoque;
    private javax.swing.JTable jtFuncionario;
    private javax.swing.JTable jtPesquisaBem;
    private javax.swing.JTable jtPesquisaProduto;
    private javax.swing.JTable jtUltimaEntradaProduto;
    private javax.swing.JTable jtUltimaSaidaProduto;
    private javax.swing.JTable jtVeiculoParado;
    private javax.swing.JTextField jtfAnoFab;
    private javax.swing.JTextField jtfChassi;
    private javax.swing.JTextField jtfCodBem;
    private javax.swing.JTextField jtfCodProduto;
    private javax.swing.JTextField jtfDataUltKM;
    private javax.swing.JTextField jtfDescBem;
    private javax.swing.JTextField jtfDescModelo;
    private javax.swing.JTextField jtfDescProduto;
    private javax.swing.JTextField jtfEmbalagem;
    private javax.swing.JTextField jtfFabricante;
    private javax.swing.JTextField jtfFilial;
    private javax.swing.JTextField jtfFuncionario;
    private javax.swing.JTextField jtfKmAtual;
    private javax.swing.JTextField jtfModelo;
    private javax.swing.JTextField jtfPesquisa;
    private javax.swing.JTextField jtfPesquisaBemTabela;
    private javax.swing.JTextField jtfPlaca;
    private javax.swing.JTextField jtfRenavam;
    private javax.swing.JTextField jtfSaldoAtual;
    private javax.swing.JTextField jtfSerie;
    private javax.swing.JTextField jtfTipoVeiculo;
    private javax.swing.JTextField jtfUnMedida;
    // End of variables declaration//GEN-END:variables

}
