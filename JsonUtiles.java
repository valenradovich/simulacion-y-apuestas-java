package apuestas_qatar22;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;


public class JsonUtiles {
	public static void grabar(JSONArray array, String archivo) {
		try {
			FileWriter file = new FileWriter("./apuestas_qatar22/json/"+archivo+".json");
			file.write(array.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String leer(String archivo) 
	{
		String contenido = "";
		try 
		{
			contenido = new String(Files.readAllBytes(Paths.get("./apuestas_qatar22/json/"+archivo+".json")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return contenido;
	}
}
    

