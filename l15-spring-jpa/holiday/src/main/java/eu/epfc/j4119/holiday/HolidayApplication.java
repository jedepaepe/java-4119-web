package eu.epfc.j4119.holiday;

import eu.epfc.j4119.holiday.entities.Holiday;
import eu.epfc.j4119.holiday.repositories.HolidayRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Transactional
@AllArgsConstructor
@SpringBootApplication
public class HolidayApplication implements CommandLineRunner {
	private HolidayRepository db;

	public static void main(String[] args) {
		SpringApplication.run(HolidayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner in = new Scanner(System.in);
		do {
			System.out.println();
			System.out.println("Choisissez une des options suivantes:");
			System.out.println("1. lister les demandes de congé");
			System.out.println("2. demander un congé");
			System.out.println("3. annuler un congé");
			System.out.println("Q. quitter l'application");
			String choice = in.nextLine();
			if (choice.equals("Q")) break;
			switch (choice) {
				case "1":
					List<Holiday> holidays = db.findAll();
					for(Holiday h : holidays) {
						System.out.println(h);
					}
					break;
				case "2":
					Holiday holiday = new Holiday();
					holiday.setEmployee("Sylvie");
					System.out.print("Début: ");
					holiday.setStart(in.nextLine());
					System.out.print("Fin: ");
					holiday.setEnd(in.nextLine());
					holiday.setStatus("REQUESTED");
					db.save(holiday);
					break;
				case "3":
					System.out.print("Id de la demande à annuler: ");
					Long id = Long.parseLong(in.nextLine());
					Optional<Holiday> hol = db.findById(id);
					if (hol.isPresent()) {
						hol.get().setStatus("CANCELLED");
						db.save(hol.get());
					} else {
						System.out.println("Pas de demande de congé pour l'identifiant " + id);
					}
					break;
				default:
					System.out.println("Choix non supporté");
			}
		} while (true);
	}
}
