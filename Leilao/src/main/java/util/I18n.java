package util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class I18n {
	
	private static final String BUNDLE_PREFIX = "MessagesBundle";
	private static final String TRANSLATIONS_FOLDER = "translations";
	
	private static I18n instance;
	
	private ResourceBundle bundle;
	private HashMap<String,DadosDeLocalidade> locales;
	
	private I18n() {
		this.locales = new HashMap<>();
		this.encontraTraducoesDisponiveis();
	}
	
	public static I18n getInstance() {
		if(instance == null)
			instance = new I18n();
		return instance;
	}
	
	public String getString(String key) {
		return this.bundle.getString(key);
	}
	
	public SortedSet<String> getLocalesOrdenados(){
		return new TreeSet<>(this.locales.keySet());
	}
	
	public boolean possuiLocale(String key) {
		return this.locales.containsKey(key);
	}
	
	public DadosDeLocalidade getDadosDeLocalidade(String key) {
		if(this.locales.containsKey(key))
			return this.locales.get(key);
		return null;
	}
	
	public void carregaBundle(Locale locale) {
		try {
			File file = new File(TRANSLATIONS_FOLDER);
			URL[] urls = { file.toURI().toURL() };
			ClassLoader loader = new URLClassLoader(urls);
			this.bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale, loader);
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void encontraTraducoesDisponiveis() {
		File[] files = new File(TRANSLATIONS_FOLDER).listFiles();
		String name, key;
		String[] tmp;
		Locale locale;
		for (File file : files) {
		    if (file.isFile()) {
		        name = file.getName();
		        if(name.contains("_")) {
		        	key = name.substring(name.indexOf('_')+1, name.indexOf('.'));
		        	tmp = key.split("_");
		        	locale = new Locale(tmp[0], tmp.length > 1 ? tmp[1] : "");
		        	this.locales.put(key, new DadosDeLocalidade(locale));
		        }
		    }
		}
	}
}
