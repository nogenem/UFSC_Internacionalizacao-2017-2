package interfaces;

import java.util.Locale;
import java.util.TimeZone;

public interface IConfiguracao {
	public Locale getCurrentLocale();
	public void setCurrentLocale(String newLocaleID);
	public TimeZone getCurrentTimeZone();
	public void setCurrentTimeZone(String newTimeZoneID);
}
