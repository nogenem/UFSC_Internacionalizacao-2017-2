package visao;

import interfaces.IUsuario;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import modelo.FabricaDeMercado;
import modelo.Lance;
import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;
import modelo.Usuario;

public class LeilaoGUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JButton btnCadastraUsuario, btnCadastraProduto, btnDarLance, 
		btnVerUsuarios, btnVerProdsLeilao, btnVerProdsVendidos, btnVerProdsVencidos, 
		btnVerProdsLeiloador, btnVerLancesUsuario, btnSalvar, btnCarregar;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private MercadoLeilao mercado;
	
	public LeilaoGUI(final MercadoLeilao mercadoLeilao) {
		this.mercado = mercadoLeilao;
		setTitle("Mercado de leilao");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(420,455);
	    setResizable(false);
	    getContentPane().setLayout(null);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	    btnCadastraUsuario = new JButton("Cadastrar Usuario");
	    btnCadastraUsuario.setBounds(30, 20, 350, 30);
	    btnCadastraUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaCadastrarUsuario(mercado);
			}
		});
	    getContentPane().add(btnCadastraUsuario);
	    
	    btnCadastraProduto = new JButton("Cadastrar Produto");
	    btnCadastraProduto.setBounds(30, 60, 350, 30);
	    btnCadastraProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaCadastrarProduto(mercado);
			}
		});
	    getContentPane().add(btnCadastraProduto);
	    
	    btnDarLance = new JButton("Dar Lance");
	    btnDarLance.setBounds(30, 100, 350, 30);
	    btnDarLance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaDarLance(mercado);
			}
		});
	    getContentPane().add(btnDarLance);
	    
	    btnVerUsuarios = new JButton("Ver Usu\u00E1rios Cadastrados");
	    btnVerUsuarios.setBounds(30, 140, 350, 30);
	    btnVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaUsuariosCadastrados(mercado);
			}
		});
	    getContentPane().add(btnVerUsuarios);
	    
	    btnVerProdsLeilao = new JButton("Ver Produtos Em Leilao");
	    btnVerProdsLeilao.setBounds(30, 180, 350, 30);
	    btnVerProdsLeilao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosEmLeilao(mercado);
			}
		});
	    getContentPane().add(btnVerProdsLeilao);
	    
	    btnVerProdsVendidos = new JButton("Ver Produtos Vendidos");
	    btnVerProdsVendidos.setBounds(30, 220, 350, 30);
	    btnVerProdsVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosVendidos(mercado);
			}
		});
	    getContentPane().add(btnVerProdsVendidos);
	    
	    btnVerProdsVencidos = new JButton("Ver Produtos Vencidos");
	    btnVerProdsVencidos.setBounds(30, 260, 350, 30);
	    btnVerProdsVencidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosVencidos(mercado);
			}
		});
	    getContentPane().add(btnVerProdsVencidos);
	    
	    btnVerProdsLeiloador = new JButton("Ver Produtos De Um Leiloador");
	    btnVerProdsLeiloador.setBounds(30, 300, 350, 30);
	    btnVerProdsLeiloador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosDeUmLeiloador(mercado);
			}
		});
	    getContentPane().add(btnVerProdsLeiloador);
	    
	    btnVerLancesUsuario = new JButton("Ver Lances De Um Usuario");
	    btnVerLancesUsuario.setBounds(30, 340, 350, 30);
	    btnVerLancesUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaLancesDeUmUsuario(mercado);
			}
		});
	    getContentPane().add(btnVerLancesUsuario);
	    
	    btnSalvar = new JButton("Salvar o Mercado");
	    btnSalvar.setBounds(30, 381, 165, 30);
	    btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FabricaDeMercado fabrica = new FabricaDeMercado();
				fabrica.desmontar(mercado);
				JOptionPane.showMessageDialog(null, "Mercado Salvo");
			}
	    	
	    });
	    getContentPane().add(btnSalvar);
	    
	    btnCarregar = new JButton("Carregar o Mercado");
	    btnCarregar.setBounds(215, 381, 165, 30);
	    btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FabricaDeMercado fabrica = new FabricaDeMercado();
				mercado = (MercadoLeilao) fabrica.montar();
				JOptionPane.showMessageDialog(null, "Mercado Carregado");
			}
		});
	    getContentPane().add(btnCarregar);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaCadastrarUsuario(final MercadoLeilao mercado) {
		final JFrame janela = new JFrame("Cadastrar Usuario");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    janela.setSize(420,420);
	    janela.setResizable(false);
	    janela.getContentPane().setLayout(null);
	    janela.setLocationRelativeTo(null);
	    janela.setVisible(true);
	    
	    JLabel labelNome = new JLabel("Nome do usuario:");
	    labelNome.setBounds(30, 30, 150, 20);
	    janela.getContentPane().add(labelNome);
	    
	    final JTextField fieldNome = new JTextField();
	    fieldNome.setBounds(30, 50, 350, 30);
	    janela.getContentPane().add(fieldNome);
	    
	    JLabel labelEndereco = new JLabel("Endereco do usuario:");
	    labelEndereco.setBounds(30, 91, 150, 20);
	    janela.getContentPane().add(labelEndereco);
	    
	    final JTextField fieldEndereco = new JTextField();
	    fieldEndereco.setBounds(30, 111, 350, 30);
	    janela.getContentPane().add(fieldEndereco);
	    
	    JLabel labelEmail = new JLabel("E-mail do usuario:");
	    labelEmail.setBounds(30, 152, 150, 20);
	    janela.getContentPane().add(labelEmail);
	    
	    final JTextField fieldEmail = new JTextField();
	    fieldEmail.setBounds(30, 172, 350, 30);
	    janela.getContentPane().add(fieldEmail);
	    
	    JLabel labelCpf = new JLabel("Apelido do usuario:");
	    labelCpf.setBounds(30, 213, 150, 20);
	    janela.getContentPane().add(labelCpf);
	    
	    final JTextField fieldApelido = new JTextField();
	    fieldApelido.setBounds(30, 233, 350, 30);
	    janela.getContentPane().add(fieldApelido);
	    
	    JButton botao = new JButton("Cadastrar");
	    botao.setBounds(155, 330, 100, 30);
	    botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = fieldNome.getText();
				String endereco = fieldEndereco.getText();
				String email = fieldEmail.getText();
				String apelido = fieldApelido.getText();
				try {
					mercado.cadastrarUsuario(nome, endereco, email, apelido);
					janela.dispose();
				} catch (Exception e) {
//					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ja existe um usuario com este apelido.");
				}
			}
	    });
	    janela.getContentPane().add(botao);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaCadastrarProduto(final MercadoLeilao mercado) {
		final JFrame janela = new JFrame("Cadastrar Produto");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    janela.setSize(420,420);
	    janela.setResizable(false);
	    janela.getContentPane().setLayout(null);
	    janela.setLocationRelativeTo(null);
	    janela.setVisible(true);
	    
	    JLabel labelNome = new JLabel("Nome do produto:");
	    labelNome.setBounds(30, 25, 150, 20);
	    janela.getContentPane().add(labelNome);
	    
	    final JTextField fieldNome = new JTextField();
	    fieldNome.setBounds(30, 45, 350, 30);
	    janela.getContentPane().add(fieldNome);
	    
	    JLabel labelDescricao = new JLabel("Descricao do produto:");
	    labelDescricao.setBounds(30, 86, 150, 20);
	    janela.getContentPane().add(labelDescricao);
	    
	    final JTextField fieldDescricao = new JTextField();
	    fieldDescricao.setBounds(30, 106, 350, 30);
	    janela.getContentPane().add(fieldDescricao);
	    
	    JLabel labelLanceMinimo = new JLabel("Lance minimo:");
	    labelLanceMinimo.setBounds(30, 147, 150, 20);
	    janela.getContentPane().add(labelLanceMinimo);
	    
	    final JTextField fieldLanceMinimo = new JTextField();
	    fieldLanceMinimo.setBounds(30, 167, 350, 30);
	    janela.getContentPane().add(fieldLanceMinimo);
	    
	    JLabel labelApelido = new JLabel("Apelido do leiloador:");
	    labelApelido.setBounds(30, 208, 150, 20);
	    janela.getContentPane().add(labelApelido);
	    
	    final JTextField fieldApelido = new JTextField();
	    fieldApelido.setBounds(30, 228, 350, 30);
	    janela.getContentPane().add(fieldApelido);
	    
	    JLabel labelData = new JLabel("Data limite (dd/MM/yyyy HH:mm)");
	    labelData.setBounds(30, 269, 350, 20);
	    janela.getContentPane().add(labelData);
	    
	    MaskFormatter mascaraData = null;
	    try {
	    	mascaraData = new MaskFormatter("##/##/####   ##:##");
	    	mascaraData.setPlaceholderCharacter('_');
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    final JFormattedTextField fieldData = new JFormattedTextField(mascaraData);
	    fieldData.setBounds(30, 289, 350, 30);
	    janela.getContentPane().add(fieldData);
	    
	    JButton botao = new JButton("Cadastrar");
	    botao.setBounds(155, 330, 100, 30);
	    botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = fieldNome.getText();
				String descricao = fieldDescricao.getText();
				Double valorMinimo = 0.0;
				try {
					valorMinimo = Double.parseDouble(fieldLanceMinimo.getText());
				}catch(Exception e) {
//					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O lance minimo deve ser um numero.\nEx: 1234.56");
				}
				String apelidoLeiloador = fieldApelido.getText();
				String data = fieldData.getText();
				try {
					Date dataLimite = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data);
					mercado.cadastrarProduto(nome, descricao, valorMinimo, apelidoLeiloador, dataLimite);
					janela.dispose();
				} catch (Exception e) {
//					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O produto ja existe ou o leiloador nao esta cadastrado.");
				}
			}
	    });
	    janela.getContentPane().add(botao);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaDarLance(final MercadoLeilao mercado) {
		final JFrame janela = new JFrame("Dar Lance");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    janela.setSize(420,420);
	    janela.setResizable(false);
	    janela.getContentPane().setLayout(null);
	    janela.setLocationRelativeTo(null);
	    janela.setVisible(true);
	    
	    JLabel labelNome = new JLabel("Nome produto:");
	    labelNome.setBounds(30, 30, 150, 20);
	    janela.getContentPane().add(labelNome);
	    
	    final JTextField fieldNome = new JTextField();
	    fieldNome.setBounds(30, 50, 350, 30);
	    janela.getContentPane().add(fieldNome);
	    
	    JLabel labelApelido = new JLabel("Apelido do comprador:");
	    labelApelido.setBounds(30, 91, 150, 20);
	    janela.getContentPane().add(labelApelido);
	    
	    final JTextField fieldApelido = new JTextField();
	    fieldApelido.setBounds(30,111,350,30);
	    janela.getContentPane().add(fieldApelido);
        
        JLabel labelValor = new JLabel("Valor do lance:");
	    labelValor.setBounds(30, 152, 150, 20);
	    janela.getContentPane().add(labelValor);
	    
	    final JTextField fieldValor = new JTextField();
	    fieldValor.setBounds(30, 172, 350, 30);
	    janela.getContentPane().add(fieldValor);
	    
	    JButton botao = new JButton("Dar lance");
	    botao.setBounds(155, 330, 100, 30);
	    botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeProduto = fieldNome.getText();
				String apelidoComprador = fieldApelido.getText();
				Double valorLance = 0.0;
				try {
					valorLance = Double.parseDouble(fieldValor.getText());
				}catch(Exception e) {
//					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O valor do lance deve ser um numero.\nEx: 1234.56");
				}
				try {
					mercado.daLance(nomeProduto, apelidoComprador, valorLance);
					janela.dispose();
				} catch (Exception e) {
//					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O valor do lance eh inferior ao necessario ou o comprador nao esta cadastrado.");
				}
			}
	    });
	    janela.getContentPane().add(botao);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaUsuariosCadastrados(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Usuarios Cadastrados");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		scroll2.setViewportView(panel);
		
		List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();
		final JList<Object> list = new JList<>(usuariosCadastrados.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);
		
		final JLabel labelNome = new JLabel("Nome:  ");
		final JLabel labelApelido = new JLabel("Apelido:  ");
		final JLabel labelEndereco = new JLabel("Endereco:  ");
		final JLabel labelEmail = new JLabel("E-mail:  ");
		panel.add(labelNome);
        panel.add(labelApelido);
        panel.add(labelEndereco);
        panel.add(labelEmail);
        
        atualizaJanelaUsuariosCadastrados(list, labelNome, labelApelido, labelEndereco, labelEmail);
        
		list.addListSelectionListener(new ListSelectionListener() {
            
			public void valueChanged(ListSelectionEvent event) {
				atualizaJanelaUsuariosCadastrados(list, labelNome, labelApelido, labelEndereco, labelEmail);
            }
        });
	}
	
	private void atualizaJanelaUsuariosCadastrados(JList<Object> list, JLabel labelNome, JLabel labelApelido, 
			JLabel labelEndereco, JLabel labelEmail) {
		IUsuario usuarioSelecionado = (IUsuario) list.getSelectedValue();
		if(usuarioSelecionado != null) {
			labelNome.setText("Nome:  " + usuarioSelecionado.getNome());
            labelApelido.setText("Apelido:  " + usuarioSelecionado.getApelido());
            labelEndereco.setText("Endereco:  " + usuarioSelecionado.getEndereco());
            labelEmail.setText("E-mail:  " + usuarioSelecionado.getEmail());
		}
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	private void janelaProdutosEmLeilao(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Produtos Em Leilao");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(6, 1, 0, 0));
		scroll2.setViewportView(panel);
		
		@SuppressWarnings("unchecked")
		List<ProdutoLeilao> produtosEmLeilao = (List<ProdutoLeilao>) mercado.getProdutosEmLeilao();
		final JList<Object> list = new JList<>(produtosEmLeilao.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);
		
		final JLabel labelNome = new JLabel("Nome:  ");
		final JLabel labelDescricao = new JLabel("Descricao:  ");
		final JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$");
		final JLabel labelUltimoLance = new JLabel("Ultimo lance: R$");
		final JLabel labelApelidoLeiloador = new JLabel("Apelido Leiloador:  ");
		final JLabel labelDataLimite = new JLabel("Data limite:  ");
        panel.add(labelNome);
        panel.add(labelDescricao);
        panel.add(labelLanceMinimo);
        panel.add(labelUltimoLance);
        panel.add(labelApelidoLeiloador);
        panel.add(labelDataLimite);
        
        atualizaJanelaProdutosEmLeilao(list, labelNome, labelDescricao, labelLanceMinimo, 
        		labelUltimoLance, labelApelidoLeiloador, labelDataLimite);
        
		list.addListSelectionListener(new ListSelectionListener() {
            
			public void valueChanged(ListSelectionEvent event) {
				atualizaJanelaProdutosEmLeilao(list, labelNome, labelDescricao, labelLanceMinimo, 
						labelUltimoLance, labelApelidoLeiloador, labelDataLimite);
            }
        });
	}
	
	private void atualizaJanelaProdutosEmLeilao(JList<Object> list, JLabel labelNome, JLabel labelDescricao,
			JLabel labelLanceMinimo, JLabel labelUltimoLance, JLabel labelApelidoLeiloador, JLabel labelDataLimite) {
		ProdutoLeilao produtoSelecionado = (ProdutoLeilao)list.getSelectedValue();
		if(produtoSelecionado != null) {
			labelNome.setText("Nome:  " + produtoSelecionado.getNome());
	        labelDescricao.setText("Descricao:  " + produtoSelecionado.getDescricao());
	        labelLanceMinimo.setText("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
	        labelUltimoLance.setText("Ultimo lance: R$" + produtoSelecionado.getValorUltimoLance());
	        labelApelidoLeiloador.setText("Apelido Leiloador:  " + produtoSelecionado.getApelidoLeiloador());
	        labelDataLimite.setText("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaProdutosVendidos(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Produtos Vendidos");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(7, 1, 0, 0));
		scroll2.setViewportView(panel);
		
		@SuppressWarnings("unchecked")
		List<ProdutoLeilao> produtosVendidos = (List<ProdutoLeilao>) mercado.getProdutosVendidos();
		final JList<Object> list = new JList<>(produtosVendidos.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);
		
		final JLabel labelNome = new JLabel("Nome:  ");
		final JLabel labelDescricao = new JLabel("Descricao:  ");
		final JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$");
		final JLabel labelPrecoVendido = new JLabel("Preco vendido: R$");
		final JLabel labelApelidoLeiloador = new JLabel("Apelido Leiloador:  ");
		final JLabel labelApelidoComprador = new JLabel("Apelido Comprador:  ");
		final JLabel labelDataLimite = new JLabel("Data limite:  ");
        panel.add(labelNome);
        panel.add(labelDescricao);
        panel.add(labelLanceMinimo);
        panel.add(labelPrecoVendido);
        panel.add(labelApelidoLeiloador);
        panel.add(labelApelidoComprador);
        panel.add(labelDataLimite);
        
        atualizaJanelaProdutosVendidos(list, labelNome, labelDescricao, labelLanceMinimo, 
				labelPrecoVendido, labelApelidoLeiloador, labelApelidoComprador, labelDataLimite);
        
		list.addListSelectionListener(new ListSelectionListener() {
            
			public void valueChanged(ListSelectionEvent event) {
				atualizaJanelaProdutosVendidos(list, labelNome, labelDescricao, labelLanceMinimo, 
						labelPrecoVendido, labelApelidoLeiloador, labelApelidoComprador, labelDataLimite);
            }
        });
	}
	
	private void atualizaJanelaProdutosVendidos(JList<Object> list, JLabel labelNome, JLabel labelDescricao, 
			JLabel labelLanceMinimo, JLabel labelPrecoVendido, JLabel labelApelidoLeiloador, JLabel labelApelidoComprador, 
			JLabel labelDataLimite) {
		ProdutoLeilao produtoSelecionado = (ProdutoLeilao)list.getSelectedValue();
		if(produtoSelecionado != null) {
			labelNome.setText("Nome:  " + produtoSelecionado.getNome());
	        labelDescricao.setText("Descricao:  " + produtoSelecionado.getDescricao());
	        labelLanceMinimo.setText("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
	        labelPrecoVendido.setText("Preco vendido: R$" + produtoSelecionado.getValorUltimoLance());
	        labelApelidoLeiloador.setText("Apelido Leiloador:  " + produtoSelecionado.getApelidoLeiloador());
	        labelApelidoComprador.setText("Apelido Comprador:  " + produtoSelecionado.getApelidoComprador());
	        labelDataLimite.setText("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaProdutosVencidos(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Produtos Vencidos E Nao Vendidos");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(5, 1, 0, 0));
		scroll2.setViewportView(panel);
		
		@SuppressWarnings("unchecked")
		List<ProdutoLeilao> produtosVencidosENaoVendidos = (List<ProdutoLeilao>) mercado.getProdutosVencidosENaoVendidos();
		final JList<Object> list = new JList<>(produtosVencidosENaoVendidos.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);
		
		final JLabel labelNome = new JLabel("Nome:  ");
		final JLabel labelDescricao = new JLabel("Descricao:  ");
		final JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$");
		final JLabel labelApelidoLeiloador = new JLabel("Apelido Leiloador:  ");
		final JLabel labelDataLimite = new JLabel("Data limite:  ");
        panel.add(labelNome);
        panel.add(labelDescricao);
        panel.add(labelLanceMinimo);
        panel.add(labelApelidoLeiloador);
        panel.add(labelDataLimite);
        
        atualizaJanelaProdutosVencidos(list, labelNome, labelDescricao, labelLanceMinimo, 
        		labelApelidoLeiloador, labelDataLimite);
        
		list.addListSelectionListener(new ListSelectionListener() {
            
			public void valueChanged(ListSelectionEvent event) {
				atualizaJanelaProdutosVencidos(list, labelNome, labelDescricao, labelLanceMinimo, 
						labelApelidoLeiloador, labelDataLimite);
            }
        });
	}
	
	private void atualizaJanelaProdutosVencidos(JList<Object> list, JLabel labelNome, JLabel labelDescricao, 
			JLabel labelLanceMinimo, JLabel labelApelidoLeiloador, JLabel labelDataLimite) {
		ProdutoLeilao produtoSelecionado = (ProdutoLeilao)list.getSelectedValue();
		if(produtoSelecionado != null) {
			labelNome.setText("Nome:  " + produtoSelecionado.getNome());
			labelDescricao.setText("Descricao:  " + produtoSelecionado.getDescricao());
			labelLanceMinimo.setText("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
			labelApelidoLeiloador.setText("Apelido Leiloador:  " + produtoSelecionado.getApelidoLeiloador());
			labelDataLimite.setText("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaProdutosDeUmLeiloador(final MercadoLeilao mercado) {
		JFrame janela = new JFrame("Ver Produtos De Um Leiloador");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 320);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(5, 56, 189, 224);
		contentPane.add(scrollPane);
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(204, 56, 385, 224);
		contentPane.add(scrollPane_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(6, 1, 0, 0));
		final JLabel labelNome = new JLabel("Nome:  ");
		final JLabel labelDescricao = new JLabel("Descricao:  ");
		final JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$");
		final JLabel labelUltimoLance = new JLabel("Ultimo lance: R$");
		final JLabel labelApelidoLeiloador = new JLabel("Apelido Leiloador:  ");
		final JLabel labelDataLimite = new JLabel("Data limite:  ");
        panel.add(labelNome);
        panel.add(labelDescricao);
        panel.add(labelLanceMinimo);
        panel.add(labelUltimoLance);
        panel.add(labelApelidoLeiloador);
        panel.add(labelDataLimite);
		scrollPane_1.setViewportView(panel);
		
		JLabel label = new JLabel("Selecione o usuario");
		label.setBounds(5, 3, 113, 20);
		contentPane.add(label);
		
		List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();
		
		final JComboBox<Object> comboBox = new JComboBox<>(usuariosCadastrados.toArray());
		comboBox.setBounds(5, 23, 189, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener () {
			
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelecionado = (Usuario) comboBox.getSelectedItem();
				List<ProdutoLeilao> produtosDoLeiloador = null;
				try {
					produtosDoLeiloador = mercado.retornaProdutosDeUmLeiloador(usuarioSelecionado.getApelido());
				} catch (Exception e) {
					e.printStackTrace();
				}
				final JList<Object> listProdutos = new JList<>(produtosDoLeiloador.toArray());
				scrollPane.setViewportView(listProdutos);
				listProdutos.setBorder(new LineBorder(new Color(0, 0, 0)));
				listProdutos.addListSelectionListener(new ListSelectionListener() {
		            
					public void valueChanged(ListSelectionEvent arg0) {
						ProdutoLeilao produtoSelecionado = (ProdutoLeilao)listProdutos.getSelectedValue();
						
						labelNome.setText("Nome:  " + produtoSelecionado.getNome());
						labelDescricao.setText("Descricao:  " + produtoSelecionado.getDescricao());
						labelLanceMinimo.setText("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
						labelUltimoLance.setText("Ultimo lance: R$" + produtoSelecionado.getValorUltimoLance());
						labelApelidoLeiloador.setText("Apelido Leiloador:  " + produtoSelecionado.getApelidoLeiloador());
						labelDataLimite.setText("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
		            }
		        });
				
				if(listProdutos.getModel().getSize() > 0)
					listProdutos.setSelectedIndex(0);
				else {
					labelNome.setText("Nome:  ");
					labelDescricao.setText("Descricao:  ");
					labelLanceMinimo.setText("Lance minimo:  R$");
					labelUltimoLance.setText("Ultimo lance: R$");
					labelApelidoLeiloador.setText("Apelido Leiloador:  ");
					labelDataLimite.setText("Data limite:  ");
				}
			}
		});
		if(comboBox.getItemCount() > 0)
			comboBox.setSelectedIndex(0);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void janelaLancesDeUmUsuario (final MercadoLeilao mercado) {
		JFrame janela = new JFrame("Ver Lances De Um Usuario");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 320);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(5, 56, 189, 224);
		contentPane.add(scrollPane);
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(204, 56, 385, 224);
		contentPane.add(scrollPane_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		final JLabel labelNomeDoUsuario = new JLabel("Nome do usuario:  ");
		final JLabel labelNomeDoProduto = new JLabel("Nome do produto:  ");
		final JLabel labelValorDoLance = new JLabel("Valor do lance:  R$");
		panel.add(labelNomeDoUsuario);
		panel.add(labelNomeDoProduto);
		panel.add(labelValorDoLance);
		scrollPane_1.setViewportView(panel);
		
		JLabel label = new JLabel("Selecione o usuario");
		label.setBounds(5, 3, 113, 20);
		contentPane.add(label);
		
		List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();
		
		final JComboBox<Object> comboBox = new JComboBox<>(usuariosCadastrados.toArray());
		comboBox.setBounds(5, 23, 189, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener () {
			
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelecionado = (Usuario) comboBox.getSelectedItem();
				List<Lance> lancesDoUsuario = null;
				try {
					lancesDoUsuario = mercado.retornaLancesDeUmUsuario(usuarioSelecionado.getApelido());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				final JList<Object> listLances = new JList<>(lancesDoUsuario.toArray());
				scrollPane.setViewportView(listLances);
				listLances.setBorder(new LineBorder(new Color(0, 0, 0)));
				listLances.addListSelectionListener(new ListSelectionListener() {
		            
					public void valueChanged(ListSelectionEvent arg0) {
						Lance lanceSelecionado = (Lance)listLances.getSelectedValue();
						
						labelNomeDoUsuario.setText("Nome do usuario:  " + lanceSelecionado.getNomeDonoDoLance());
						labelNomeDoProduto.setText("Nome do produto:  " + lanceSelecionado.getNomeProdutoQueRecebeuOLance());
						labelValorDoLance.setText("Valor do lance:  R$" + lanceSelecionado.getValorDoLance());
		            }
		        });
				if(listLances.getModel().getSize() > 0)
					listLances.setSelectedIndex(0);
				else {
					labelNomeDoUsuario.setText("Nome do usuario:  ");
					labelNomeDoProduto.setText("Nome do produto:  ");
					labelValorDoLance.setText("Valor do lance:  R$");
				}
			}
		});
		if(comboBox.getItemCount() > 0)
			comboBox.setSelectedIndex(0);
	}
}