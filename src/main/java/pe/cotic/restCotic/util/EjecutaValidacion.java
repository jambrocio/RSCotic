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
		 String cadena1 = "S� te n�ota que est|as eno'jado N� 1234"; 
		 String cadena2 = cadena1.replaceAll("[�|�]", "A"); 
		 cadena2 = cadena2.replaceAll("[�|�]", "E");
		 cadena2 = cadena2.replaceAll("[�|�]", "I");
		 cadena2 = cadena2.replaceAll("[�|�]", "O");
		 cadena2 = cadena2.replaceAll("[�|�]", "U");
		 cadena2 = cadena2.replaceAll("['|||�]", "");
		 System.out.println("cadena : " + cadena2);
		*/
		
		String input = "j�rge chr�stian ambrocio sernaque 145 |�#$'";
		String alphaOnly = input.replaceAll("[�|�|�|�]", "A");
		alphaOnly = alphaOnly.replaceAll("[�|�|�|�]", "E");
		alphaOnly = alphaOnly.replaceAll("[�|�|�|�]", "I");
		alphaOnly = alphaOnly.replaceAll("[�|�|�|�]", "O");
		alphaOnly = alphaOnly.replaceAll("[�|�|�|�]", "U");
		alphaOnly = alphaOnly.replaceAll("[^a-zA-Z0-9$ ]+","");
		
		
		System.out.println("Input : " + alphaOnly);
	}
	
}
