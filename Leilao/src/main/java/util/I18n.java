package util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

public class I18n {
	
	private static final String BUNDLE_PREFIX = "MessagesBundle";
	private static final String TRANSLATIONS_FOLDER = "translations";
	
	private static I18n instance;
	
	private ResourceBundle bundle;
	private HashMap<String,Locale> locales;
	
	private I18n() {
		this.locales = new HashMap<>();
		
		this.carregaBundle(Locale.getDefault());
		this.encontraTraducoesDisponiveis();
	}
	
	public static I18n getInstance() {
		if(instance != null)
			return instance;
		return new I18n();
	}
	
	public String getString(String key) {
		return this.bundle.getString(key);
	}
	
	public SortedSet<String> getLocalesOrdenados(){
		return new TreeSet<>(this.locales.keySet());
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
		for (File file : files) {
		    if (file.isFile()) {
		        name = file.getName();
		        if(name.contains("_")) {
		        	key = name.substring(name.indexOf('_')+1, name.indexOf('.'));
		        	tmp = key.split("_");
		        	this.locales.put(key, new Locale(tmp[0], tmp.length > 1 ? tmp[1] : ""));
		        }
		    }
		}
	}
}
