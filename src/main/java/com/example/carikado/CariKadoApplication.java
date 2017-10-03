package com.example.carikado;

import com.example.carikado.model.*;
import com.example.carikado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Date;

@SpringBootApplication
public class CariKadoApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private UserService userService;

	@Autowired
	public CariKadoApplication(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CariKadoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CariKadoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Role role = new Role("Admin");
//		User user = new User("admin@carikado.com", "$2y$10$ZXBzBg.ET7lwTT41qQr/SOfQ0V56E20bCSH4Jw8W30X.OSsH5MPuW", " ");
		User user = new User("admin@carikado.com", User.base64Encoder("admin"), " ");
		UserName userName = new UserName("admin", "cari", "kado");
		UserAddress userAddress = new UserAddress();

		userAddress.setStreet(" ");
		userAddress.setPostalCode(" ");
		userAddress.setHamlet(0);
		userAddress.setNeighbourhood(0);

		user.setCreatedAt(new Date());
		user.setStatus(UserStatus.ACTIVE);
		user.setRole(role);
		user.setUserName(userName);
		user.setUserAddress(userAddress);

		try {
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
