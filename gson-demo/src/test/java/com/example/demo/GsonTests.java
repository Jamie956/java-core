package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.example.Item;
import com.example.Order;
import com.example.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTests {

	@Test
	public void entityToJson() {
		Item item = new Item();
		item.setId("01");
		item.setName("seafood");

		String jsonStr = new Gson().toJson(item);
		System.out.println(jsonStr);// {"id":"01","name":"seafood"}
	}

	@Test
	public void jsonToEntity() {
		Item item = new Gson().fromJson("{\"id\":\"01\",\"name\":\"seafood\"}", Item.class);
		System.out.println(item);//Item [id=01, name=seafood]
	}

	@Test
	public void jsonToEntityWithMultiName() {
		User user = new Gson().fromJson("{\"id\":\"02\",\"first_name\":Zhou,\"last_name\":\"Jamie\"}", User.class);
		System.out.println(user);// User [id=02, firstName=Zhou, lastName=Jamie]
	}

	@Test
	public void arrStrToArr() {
		String[] arr = new Gson().fromJson("[\"Android\",\"Java\",\"PHP\"]", String[].class);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);// Android Java PHP
		}
	}

	@Test
	public void arrStrToList() {
		List<String> list = new Gson().fromJson("[\"Android\",\"Java\",\"PHP\"]", new TypeToken<List<String>>() {}.getType());
		System.out.println(list);//[Android, Java, PHP]
	}

	@Test
	public void jsonToMap() {
		Map<String, String> map = new Gson().fromJson("{\"id\":\"01\",\"name\":\"seafood\"}", new TypeToken<HashMap<String, String>>() {}.getType());
		System.out.println(map);//{name=seafood, id=01}
	}

	@Test
	public void mapToJson() {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("id", "01");
		userMap.put("firstname", "Jamie");
		userMap.put("lastname", "Zhou");

		String userJson = new Gson().toJson(userMap);
		System.out.println(userJson);//{"firstname":"Jamie","id":"01","lastname":"Zhou"}
	}

	@Test
	public void nestListToJson() {
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();

		Map<String, String> userMap1 = new HashMap<String, String>();
		userMap1.put("id", "01");
		userMap1.put("firstname", "Jamie");
		userMap1.put("lastname", "Zhou");

		Map<String, String> userMap2 = new HashMap<String, String>();
		userMap2.put("id", "02");
		userMap2.put("firstname", "John");
		userMap2.put("lastname", "Nash");

		userList.add(userMap1);
		userList.add(userMap2);

		String userJson = new Gson().toJson(userList);
		System.out.println(userJson);//[{"firstname":"Jamie","id":"01","lastname":"Zhou"},{"firstname":"John","id":"02","lastname":"Nash"}]
	}

	@Test
	public void nestEntityToJson() {
		Order order = new Order();

		List<Item> itemlist = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setId("01");
		item1.setName("item01");
		itemlist.add(item1);
		Item item2 = new Item();
		item2.setId("02");
		item2.setName("item02");
		itemlist.add(item2);
		order.setItemlist(itemlist);

		order.setId("666");
		order.setName("newBee");
		order.setItemlist(itemlist);

		String json = new Gson().toJson(order);
		System.out.println(json);// {"id":"666","name":"newBee","itemlist":[{"id":"01","name":"item01"},{"id":"02","name":"item02"}]}
	}

	@Test
	public void nestJsonToEntity() {
		Order order = new Gson().fromJson("{\"id\":\"666\",\"name\":\"newBee\",\"itemlist\":[{\"id\":\"01\",\"name\":\"item01\"},{\"id\":\"02\",\"name\":\"item02\"}]}", Order.class);
		System.out.println(order);//Order [id=666, name=newBee, itemlist=[Item [id=01, name=item01], Item [id=02, name=item02]]]
	}

	@Test
	public void jsonArrToEntity() {
		String nestJson = "[{\"id\":\"09\",\"name\":\"item09\"}, {\"id\":\"10\",\"name\":\"item10\"}, {\"id\":\"11\",\"name\":\"item11\"}]";
		List<Item> itemlist = new Gson().fromJson(nestJson, new TypeToken<List<Item>>() {}.getType());
		System.out.println(itemlist);//[Item [id=09, name=item09], Item [id=10, name=item10], Item [id=11, name=item11]]
	}

}