package co.edu.unbosque.pokemonapp.service;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.pokemonapp.model.DateTime;
import co.edu.unbosque.pokemonapp.model.Session;
import co.edu.unbosque.pokemonapp.model.User;
import co.edu.unbosque.pokemonapp.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRep;

	private Gson gson;

	private DateTimeService dateTimeData;

	public SessionService() {
		// TODO Auto-generated constructor stub
		gson = new Gson();
		dateTimeData = new DateTimeService();
	}

	public int create(User userSession) {
		try {

			Content contentIpLocation = Request
					.get("https://ipgeolocation.abstractapi.com/v1/?api_key=d8706a8e487c4b608c26e64aa216e16a").execute()
					.returnContent();
			Session newSessionIpLocation = gson.fromJson(contentIpLocation.toString(), Session.class);
			String location = Float.toString(newSessionIpLocation.getLatitude()) + ","
					+ Float.toString(newSessionIpLocation.getLongitude());

			System.out.println(newSessionIpLocation.getIp_address());
			System.out.println(newSessionIpLocation.getLatitude());
			System.out.println(newSessionIpLocation.getLongitude());

			Session temp = new Session();
			temp.setUserSession(userSession);
			temp.setIp_address(newSessionIpLocation.getIp_address());
			temp.setLongitude(newSessionIpLocation.getLongitude());
			temp.setLatitude(newSessionIpLocation.getLatitude());
			temp.setDatetime(dateTimeData.getDateTimeInfo(newSessionIpLocation.getIp_address()).getDatetime());
			sessionRep.save(temp);
			return 0;
		} catch (IOException error) {
			System.out.println(error);
			return 1;
		}

	}

	public List<Session> getAll() {

		return (List<Session>) sessionRep.findAll();

	}

}
