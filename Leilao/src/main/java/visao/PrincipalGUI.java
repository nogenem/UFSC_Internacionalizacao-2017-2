package visao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exceptions.CarregaMercadoException;
import modelo.FabricaDeMercado;
import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;
import util.I18n;

public class PrincipalGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton btnCadastraUsuario, btnCadastrarProduto, btnDarLance,
		btnProdutosLeilao, btnProdutosVendidos, btnProdutosVencidos, btnProdutosLeiloador, 
		btnProdutosUsuario, btnSalvar, btnConfig, btnCarregar, btnUsuariosCadastrados;
	
	private MercadoLeilao mercado;
	private ConfiguracaoGUI config;

	public PrincipalGUI(final MercadoLeilao mercadoLeilao) {
		super();
		
		this.mercado = mercadoLeilao;
		this.config = new ConfiguracaoGUI();
		
		inicializarGUI();
	}
	
	private void inicializarGUI() {
		final JFrame frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//////////////////////////////////////////////
		JPanel upperBtns = new JPanel();
		getContentPane().add(upperBtns, BorderLayout.CENTER);
		upperBtns.setLayout(new MigLayout("al center center,fillx", "[center,grow,fill]", 
				"[center][center][center][center][center][center][center][center][center]"));
		
		btnCadastraUsuario = new JButton();
		btnCadastraUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaCadastrarUsuario();
			}
		});
		upperBtns.add(btnCadastraUsuario, "span,grow");
		
		btnCadastrarProduto = new JButton();
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaCadastrarProduto();
			}
		});
		upperBtns.add(btnCadastrarProduto, "span,grow");
		
		btnDarLance = new JButton();
		btnDarLance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaDarLance();
			}
		});
		upperBtns.add(btnDarLance, "span,grow");
		
		btnUsuariosCadastrados = new JButton();
		btnUsuariosCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaUsuariosCadastrados();
			}
		});
		upperBtns.add(btnUsuariosCadastrados, "span,grow");
		
		btnProdutosLeilao = new JButton();
		btnProdutosLeilao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosEmLeilao();
			}
		});
		upperBtns.add(btnProdutosLeilao, "span,grow");
		
		btnProdutosVendidos = new JButton();
		btnProdutosVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosVendidos();
			}
		});
		upperBtns.add(btnProdutosVendidos, "span,grow");
		
		btnProdutosVencidos = new JButton();
		btnProdutosVencidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosVencidos();
			}
		});
		upperBtns.add(btnProdutosVencidos, "span,grow");
		
		btnProdutosLeiloador = new JButton();
		btnProdutosLeiloador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaProdutosDeUmLeiloador();
			}
		});
		upperBtns.add(btnProdutosLeiloador, "span,grow");
		
		btnProdutosUsuario = new JButton();
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
		
		btnSalvar = new JButton();
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FabricaDeMercado.desmontar(mercado);
				mostraMensagemDeInformacao(frame, I18n.getInstance().getString("principalGUI.mercado_salvo"));
			}
	    });
		lowerBtns.add(btnSalvar, "cell 0 0,alignx center,aligny center");
		
		btnConfig = new JButton();
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraJanelaConfiguracao();
			}
		});
		lowerBtns.add(btnConfig, "cell 1 0,alignx center,aligny center");
		
		btnCarregar = new JButton();
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mercado = (MercadoLeilao) FabricaDeMercado.montar();
					btnCarregar.setEnabled(false);
					mostraMensagemDeInformacao(frame, I18n.getInstance().getString("principalGUI.mercado_carregado"));
				} catch (CarregaMercadoException err) {
					mostraMensagemDeInformacao(frame, err.getMessage());
				}
			}
		});
		lowerBtns.add(btnCarregar, "cell 2 0,alignx center,aligny center");
		this.atualizaI18n();
		
		this.setVisible(true);
	}
	
	public void atualizaI18n() {
		I18n instance = I18n.getInstance();
		
		setTitle(instance.getString("principalGUI.titulo"));
		
		btnCadastraUsuario.setText(instance.getString("principalGUI.cadastrar_usuario"));
		btnCadastrarProduto.setText(instance.getString("principalGUI.cadastrar_produto"));
		btnDarLance.setText(instance.getString("principalGUI.dar_lance"));
		btnUsuariosCadastrados.setText(instance.getString("principalGUI.usuarios_cadastrados"));
		btnProdutosLeilao.setText(instance.getString("principalGUI.produtos_em_leilao"));
		btnProdutosVendidos.setText(instance.getString("principalGUI.produtos_vendidos"));
		btnProdutosVencidos.setText(instance.getString("principalGUI.produtos_vencidos"));
		btnProdutosLeiloador.setText(instance.getString("principalGUI.produtos_leiloador"));
		btnProdutosUsuario.setText(instance.getString("principalGUI.lances_usuario"));
		btnSalvar.setText(instance.getString("principalGUI.salvar_mercado"));
		btnConfig.setText(instance.getString("principalGUI.configuracao"));
		btnCarregar.setText(instance.getString("principalGUI.carregar_mercado"));
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void mostraJanelaCadastrarUsuario() {
		new CadastraUsuarioGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaCadastrarProduto() {
		new CadastraProdutoGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaDarLance() {
		new DarLanceGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaUsuariosCadastrados() {
		new UsuariosCadastradosGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosEmLeilao() {
		new ProdutosEmLeilaoGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosVendidos() {
		new ProdutosVendidosGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosVencidos() {
		new ProdutosVencidosGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaProdutosDeUmLeiloador() {
		new ProdutosLeiloadorGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaLancesDeUmUsuario() {
		new LancesUsuarioGUI().mostrarJanela(this, this.mercado);
	}
	
	private void mostraJanelaConfiguracao() {
		this.config.mostrarJanela(this, this.mercado);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void mostraMensagemDeAlerta(final JFrame parent, String message) {
		JOptionPane.showMessageDialog(parent, message, 
				I18n.getInstance().getString("principalGUI.titulo_alerta"), JOptionPane.WARNING_MESSAGE);
	}
	
	public void mostraMensagemDeInformacao(final JFrame parent, String message) {
		JOptionPane.showMessageDialog(parent, message, 
				I18n.getInstance().getString("principalGUI.titulo_informacao"), JOptionPane.INFORMATION_MESSAGE);
	}
}
