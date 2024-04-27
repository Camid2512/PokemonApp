package co.edu.unbosque.pokemonapp.service;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.pokemonapp.model.Session;
import co.edu.unbosque.pokemonapp.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRep;

	private Gson gson;

	public SessionService() {
		// TODO Auto-generated constructor stub
		gson = new Gson();
	}

	public int create() {
		try {

			Content content = Request
					.get("https://ipgeolocation.abstractapi.com/v1/?api_key=d8706a8e487c4b608c26e64aa216e16a").execute()
					.returnContent();
			Session newSession = gson.fromJson(content.toString(), Session.class);

			System.out.println(newSession.getIp_address());
			System.out.println(newSession.getLatitude());
			System.out.println(newSession.getLongitude());

			Session temp = new Session();
			temp.setIp_address(newSession.getIp_address());
			temp.setLongitude(newSession.getLongitude());
			temp.setLatitude(newSession.getLatitude());

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
