package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import modelo.Configuracao;
import modelo.FabricaDeConfiguracao;
import modelo.MercadoLeilao;
import net.miginfocom.swing.MigLayout;
import util.I18n;

public class ConfiguracaoGUI extends ParentGUI {
	
	private Configuracao config;
	private String selectedLanguage, selectedTimezone;
	
	private JLabel lblLingua, lblFusos;
	private JButton btnSalvar;
	
	public ConfiguracaoGUI() {
		config = Configuracao.getInstance();
	}
	
	public Configuracao getConfig() {
		return this.config;
	}
	
	@Override
	protected void constroiFrame(final PrincipalGUI parent, final MercadoLeilao mercado) {
		currentFrame.setTitle(i18n.getString("configuracaoGUI.titulo"));
		currentFrame.setSize(450, 250);
		currentFrame.getContentPane().setLayout(new MigLayout("al center center,fillx", 
				"[grow]", "[][]20[][]30[]"));
		
		this.lblLingua = new JLabel();
		currentFrame.getContentPane().add(lblLingua, "span,grow");
		
		final SortedSet<String> linguas = i18n.getLocalesOrdenados();
		JComboBox<Object> cbLinguas = new JComboBox<>(linguas.toArray());
		cbLinguas.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				selectedLanguage = (String) cbLinguas.getSelectedItem();
			}
		});
		currentFrame.getContentPane().add(cbLinguas, "span,grow");
		
		this.lblFusos = new JLabel();
		currentFrame.getContentPane().add(lblFusos, "span,grow");
		
		final Object[] timezoneOrdenado = ZoneId.getAvailableZoneIds().toArray();
		Arrays.sort(timezoneOrdenado);
		JComboBox<Object> cbTimezones = new JComboBox<>(timezoneOrdenado);
		cbTimezones.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				selectedTimezone = (String) cbTimezones.getSelectedItem();
			}
		});
		currentFrame.getContentPane().add(cbTimezones, "span,grow");
		
		this.btnSalvar = new JButton();
		this.btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String currentLanguage = config.getLocalidadeAtual().toString();
				String currentTimeZone = config.getFusoHorarioAtual().getId();
				
				if(!selectedTimezone.equals(currentTimeZone))
					config.setFusoHorarioAtual(selectedTimezone);
				if(!selectedLanguage.equals(currentLanguage)) {
					config.setLocalidadeAtual(selectedLanguage);
					I18n.getInstance().carregaBundle(config.getLocalidadeAtual());
					parent.atualizaI18n();
				}
				if(!selectedTimezone.equals(currentTimeZone) || !selectedLanguage.equals(currentLanguage))
					FabricaDeConfiguracao.desmontar(config);
				currentFrame.dispose();
			}
		});
		currentFrame.getContentPane().add(btnSalvar, "span,grow,height 30::");
		
		////////////////////////////////////////////////////////////////////////
		cbLinguas.setSelectedItem(this.config.getLocalidadeAtual().toString());
		cbTimezones.setSelectedItem(this.config.getFusoHorarioAtual().getId());
		this.atualizaLabels();
	}
	
	private void atualizaLabels() {
		this.lblLingua.setText(i18n.getString("configuracaoGUI.lingua"));
		this.lblFusos.setText(i18n.getString("configuracaoGUI.fuso_horario"));
		this.btnSalvar.setText(i18n.getString("configuracaoGUI.salvar"));
	}
}
