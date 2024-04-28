package co.edu.unbosque.pokemonapp.service;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

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

			Content content = Request.get(
					"https://timezone.abstractapi.com/v1/current_time/?api_key=6f518906dba04da29b870e599bbc4652&location="
							+ location)
					.execute().returnContent();

			dateTimeData = gson.fromJson(content.toString(), DateTime.class);

			System.out.println(dateTimeData.getDatetime());

			return dateTimeData;

		} catch (IOException error) {
			System.out.println(error);
		}
		return null;

	}

}
