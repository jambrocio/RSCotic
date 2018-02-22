package pe.cotic.restCotic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compress {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String filePath = "C:\\Users\\joch\\Desktop\\AYZA\\44444444__12_02_2015_13_45_05__1.JPG";

		String string = getFileData(filePath);
		//System.out.println("after compress:");
		String compressed = compress(string);
		//System.out.println(compressed);
		//System.out.println("after decompress:");
		String decomp = decompress(compressed);
		//System.out.println(decomp);
		
		
		String test = "Lets see how much we can compress this string!";
				
        //String output = LZString.compress(test);
        /*
        System.out.println("Compressed: " + output);
        String decompressed = LZString.decompress(output);
        System.out.println("Decompressed: " + decompressed);
        */
        /*
        String testUTF16 = "Lets see how much we can compress this string!";
        String outputUTF16 = LZString.compressToUTF16(testUTF16);
        System.out.println("Compressed UTF16 : " + outputUTF16);
        String decompressedUTF16 = LZString.decompressFromUTF16(outputUTF16);
        System.out.println("Decompressed UTF16 : " + decompressedUTF16);
        */
        
        
        
        File file = new File("C:\\Users\\joch\\Desktop\\AYZA\\compress.txt");
        
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(compressed);
		bw.close();
		
		//String decompressed = LZString.decompress(output);
		File fileDes = new File("C:\\Users\\joch\\Desktop\\AYZA\\descompress.txt");
        
		if (!fileDes.exists()) {
			fileDes.createNewFile();
		}

		FileWriter fwDes = new FileWriter(fileDes.getAbsoluteFile());
		BufferedWriter bwDes = new BufferedWriter(fwDes);
		bwDes.write(decomp);
		bwDes.close();
		
		System.out.println("Done");
		
        
        

	}
	/*
	private static String ungzip(byte[] bytes) throws Exception{
    	InputStreamReader isr = new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bytes)), StandardCharsets.UTF_8);
    	StringWriter sw = new StringWriter();
    	char[] chars = new char[1024];
    	for (int len; (len = isr.read(chars)) > 0; ) {
    		sw.write(chars, 0, len);
    	}
    	
    	return sw.toString();
    }
    	
    private static byte[] gzip(String s) throws Exception{
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	GZIPOutputStream gzip = new GZIPOutputStream(bos);
    	OutputStreamWriter osw = new OutputStreamWriter(gzip, StandardCharsets.UTF_8);
    	osw.write(s);
    	osw.close();
    	
    	return bos.toByteArray();
    }
    */
	public static String getFileData(String filepath)
			throws FileNotFoundException, IOException {
		BufferedReader bf = new BufferedReader(new FileReader(filepath));
		String data = "";
		String line;
		while ((line = bf.readLine()) != null) {
			data += line;
		}
		return data;
	}

	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		System.out.println("String length : " + str.length());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		String outStr = out.toString("ISO-8859-1");
		System.out.println("Output String lenght : " + outStr.length());
		return outStr;
	}

	public static String decompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		System.out.println("Input String length : " + str.length());
		GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(
				str.getBytes("ISO-8859-1")));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis,
				"ISO-8859-1"));
		String outStr = "";
		String line;
		while ((line = bf.readLine()) != null) {
			outStr += line;
		}
		System.out.println("Output String lenght : " + outStr.length());
		return outStr;
	}
}
