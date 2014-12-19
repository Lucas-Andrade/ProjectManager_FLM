package utils;

import java.text.DecimalFormat;

import org.json.JSONObject;


public class dummyJson {
	public static void main(String[] args){
		
		StringBuilder builder = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.##");
	
		//instancia um novo JSONObject 
		JSONObject worker = new JSONObject(); 
		
		//preenche o objeto com os campos: titulo, ano e genero 
		worker.accumulate("Name", "Filipa");
		worker.accumulate("Payment per hour", df.format(20.56987)); 
		worker.accumulate("Cost", df.format(100.26658)); 
		
		//serializa para uma string e imprime 
		String json_string = worker.toString();
		System.out.println(json_string);
	}
}
