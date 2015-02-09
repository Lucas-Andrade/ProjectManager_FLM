package gson;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class gson {

	@Test
	public void test() {
		JsonElement element = new JsonParser().parse("{\"Email\":\"user1@email.com\",\"Username\":\"user1\",\"Full name\":\"User 1\"}");
		JsonObject obj = element.getAsJsonObject();
		JsonElement newElement = obj.get("Username");
		System.out.println(newElement.getAsString());
	}

}
