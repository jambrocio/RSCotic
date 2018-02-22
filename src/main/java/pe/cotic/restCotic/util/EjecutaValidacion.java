package pe.cotic.restCotic.util;

public class EjecutaValidacion {

	public static void main(String[] args) {
		/*
		 * boolean retornoStringNumber = Valida.isStringNumber(
		 * "6e2521465883fa00fe0bae4eb7e39cb18037250bd5b16516c05d1f6bae449173");
		 * System.out.println("retornoStringNumber : " + retornoStringNumber);
		 * boolean retornoString = Valida.isString("asdaweAWD");
		 * System.out.println("retornoString : " + retornoString); boolean
		 * retornoNumber = Valida.isNumber("0044112");
		 * System.out.println("retornoNumber : " + retornoNumber);
		 */

		/*
		 String cadena1 = "Sé te n°ota que est|as eno'jado N° 1234"; 
		 String cadena2 = cadena1.replaceAll("[á|Á]", "A"); 
		 cadena2 = cadena2.replaceAll("[é|É]", "E");
		 cadena2 = cadena2.replaceAll("[í|Í]", "I");
		 cadena2 = cadena2.replaceAll("[ó|Ó]", "O");
		 cadena2 = cadena2.replaceAll("[ú|Ú]", "U");
		 cadena2 = cadena2.replaceAll("['|||°]", "");
		 System.out.println("cadena : " + cadena2);
		*/
		
		String input = "jórge chrÍstian ambrocio sernaque 145 |°#$'";
		String alphaOnly = input.replaceAll("[á|à|Á|À]", "A");
		alphaOnly = alphaOnly.replaceAll("[é|è|É|È]", "E");
		alphaOnly = alphaOnly.replaceAll("[í|ì|Í|Ì]", "I");
		alphaOnly = alphaOnly.replaceAll("[ó|ò|Ó|Ò]", "O");
		alphaOnly = alphaOnly.replaceAll("[ú|ù|Ú|Ù]", "U");
		alphaOnly = alphaOnly.replaceAll("[^a-zA-Z0-9$ ]+","");
		
		
		System.out.println("Input : " + alphaOnly);
	}
	
}
