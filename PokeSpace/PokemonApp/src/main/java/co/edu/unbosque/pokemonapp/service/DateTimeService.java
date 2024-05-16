package co.edu.unbosque.pokemonapp.service;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.edu.unbosque.pokemonapp.model.DateTime;

@Service
public class DateTimeService {

	private DateTime dateTimeData;

	private Gson gson;

	public DateTimeService() {
		// TODO Auto-generated constructor stub
		gson = new Gson();
	}

	public DateTime getDateTimeInfo(String location) {

		try {

			Content content = Request.get("https://worldtimeapi.org/api/ip/" + location).execute().returnContent();

			String jsonResponse = content.asString();

			JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
			String dateTime = jsonObject.get("datetime").getAsString();

			dateTimeData = new DateTime(dateTime);

			System.out.println(dateTimeData.getDatetime());

			return dateTimeData;

		} catch (IOException error) {
			System.out.println(error);
		}
		return null;

	}

}
