package pe.cotic.restCotic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valida {
	
	public static boolean lenght(String contenido, int tamanio) {
		int longitud = contenido.length();
		
		if (longitud > tamanio) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isString(String contenido) {
		Pattern pat = Pattern.compile("[a-zA-Z]*");
		Matcher mat = pat.matcher(contenido);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNumber(String contenido) {
		Pattern pat = Pattern.compile("[0-9]*");
		Matcher mat = pat.matcher(contenido);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isStringNumber(String contenido) {
		Pattern pat = Pattern.compile("[a-zA-Z0-9]*");
		Matcher mat = pat.matcher(contenido);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isClave(String contenido) {
		Pattern pat = Pattern.compile("[a-zA-Z0-9.+=/]*");
		Matcher mat = pat.matcher(contenido);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
