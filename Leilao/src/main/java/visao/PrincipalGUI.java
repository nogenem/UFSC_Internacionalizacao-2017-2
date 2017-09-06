package visao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;

public class PrincipalGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MercadoLeilao mercado;
	private DateFormat dateFormat;

	public PrincipalGUI(final MercadoLeilao mercadoLeilao) {
		super();
		
		this.mercado = mercadoLeilao;
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		inicializarGUI();
	}
	
	private void inicializarGUI() {
		setTitle("Mercado Leilão");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//////////////////////////////////////////////
		JPanel upperBtns = new JPanel();
		getContentPane().add(upperBtns, BorderLayout.CENTER);
		upperBtns.setLayout(new MigLayout("al center center,fillx", "[center,grow,fill]", 
				"[center][center][center][center][center][center][center][center][center]"));
		
		JButton btnCadastraUsuario = new JButton("Cadastrar Usuário");
		btnCadastraUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaCadastrarUsuario();
			}
		});
		upperBtns.add(btnCadastraUsuario, "span,grow");
		
		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaCadastrarProduto();
			}
		});
		upperBtns.add(btnCadastrarProduto, "span,grow");
		
		JButton btnDarLance = new JButton("Dar Lance");
		btnDarLance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaDarLance();
			}
		});
		upperBtns.add(btnDarLance, "span,grow");
		
		JButton btnUsuariosCadastrados = new JButton("Ver Usuários Cadastrados");
		btnUsuariosCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaUsuariosCadastrados();
			}
		});
		upperBtns.add(btnUsuariosCadastrados, "span,grow");
		
		JButton btnProdutosLeilao = new JButton("Ver Produtos em Leilão");
		btnProdutosLeilao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosEmLeilao();
			}
		});
		upperBtns.add(btnProdutosLeilao, "span,grow");
		
		JButton btnProdutosVendidos = new JButton("Ver Produtos Vendidos");
		btnProdutosVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosVendidos();
			}
		});
		upperBtns.add(btnProdutosVendidos, "span,grow");
		
		JButton btnProdutosVencidos = new JButton("Ver Produtos Vencidos");
		btnProdutosVencidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosVencidos();
			}
		});
		upperBtns.add(btnProdutosVencidos, "span,grow");
		
		JButton btnProdutosLeiloador = new JButton("Ver Produtos de um Leiloador");
		btnProdutosLeiloador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosDeUmLeiloador();
			}
		});
		upperBtns.add(btnProdutosLeiloador, "span,grow");
		
		JButton btnProdutosUsuario = new JButton("Ver Produtos de um Usuário");
		btnProdutosUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaLancesDeUmUsuario();
			}
		});
		upperBtns.add(btnProdutosUsuario, "span,grow");
		
		//////////////////////////////////////////////
		JPanel lowerBtns = new JPanel();
		getContentPane().add(lowerBtns, BorderLayout.SOUTH);
		lowerBtns.setLayout(new MigLayout("al center center, wrap", "[center][center][center]", "[center]"));
		
		JButton btnSalvar = new JButton("Salvar Mercado");
		lowerBtns.add(btnSalvar, "cell 0 0,alignx center,aligny center");
		
		JButton btnConfig = new JButton("Configuração");
		lowerBtns.add(btnConfig, "cell 1 0,alignx center,aligny center");
		
		JButton btnCarregar = new JButton("Carregar Mercado");
		lowerBtns.add(btnCarregar, "cell 2 0,alignx center,aligny center");
		
		this.setVisible(true);
	}

	private void mostraJanelaCadastrarUsuario() {
		new CadastraUsuarioGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaCadastrarProduto() {
		new CadastraProdutoGUI(this.dateFormat).mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaDarLance() {
		new DarLanceGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaUsuariosCadastrados() {
		new UsuariosCadastradosGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosEmLeilao() {
		new ProdutosEmLeilaoGUI(this.dateFormat).mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosVendidos() {
		new ProdutosVendidosGUI(this.dateFormat).mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosVencidos() {
		new ProdutosVencidosGUI(this.dateFormat).mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosDeUmLeiloador() {
		new ProdutosLeiloadorGUI(this.dateFormat).mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaLancesDeUmUsuario() {
		new LancesUsuarioGUI().mostrarJanela(this, this.mercado);
	}
}
