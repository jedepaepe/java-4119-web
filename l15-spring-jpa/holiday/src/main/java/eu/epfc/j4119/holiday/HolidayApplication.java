package eu.epfc.j4119.holiday;

import eu.epfc.j4119.holiday.entities.Holiday;
import eu.epfc.j4119.holiday.repositories.HolidayRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@SpringBootApplication
public class HolidayApplication implements CommandLineRunner {
	private HolidayRepository db;

	public HolidayApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(HolidayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 1. créer un enregistrement dans la table Holiday
		Holiday holiday1 = new Holiday();
		holiday1.setEmployee("Sylvie");
		holiday1.setStart("1/5/2022");
		holiday1.setEnd("20/5/2022");
		holiday1.setStatus("REQUESTED");
		db.save(holiday1);

		// 2. lire le contenu de la table holiday
		List<Holiday> holidays = db.findAll();
		for(Holiday h : holidays) {
			System.out.println(h);
		}
	}
}
