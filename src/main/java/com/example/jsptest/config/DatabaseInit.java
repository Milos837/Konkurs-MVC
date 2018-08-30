package com.example.jsptest.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.jsptest.entities.AdminEntity;
import com.example.jsptest.entities.CitizenshipEntity;
import com.example.jsptest.entities.LanguageEntity;
import com.example.jsptest.entities.RoleEntity;
import com.example.jsptest.repositories.AdminRepository;
import com.example.jsptest.repositories.CitizenshipRepository;
import com.example.jsptest.repositories.LanguageRepository;
import com.example.jsptest.repositories.RoleRepository;

public class DatabaseInit {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private CitizenshipRepository citizenshipRepository;

	@Autowired
	private RoleRepository roleRepository;

	private RoleEntity adminRole;

	@PostConstruct
	public void init() {
		roleInit();
		userInit();
		citizenshipInit();
		languageInit();
	}

	private void roleInit() {
		if (((List<RoleEntity>) roleRepository.findAll()).isEmpty()) {
			adminRole = new RoleEntity("ROLE_ADMIN");
			roleRepository.save(adminRole);
		} else {
			adminRole = roleRepository.findByName("ROLE_ADMIN");
		}
	}

	private void userInit() {
		if (((List<AdminEntity>) adminRepository.findAll()).isEmpty()) {
			AdminEntity admin = new AdminEntity();
			admin.setDeleted(false);
			admin.setUsername("admin");
			admin.setPassword(getPassEncoded("admin"));
			admin.setRole(adminRole);
			adminRepository.save(admin);
		}
	}

	private void citizenshipInit() {
		if (((List<CitizenshipEntity>) citizenshipRepository.findAll()).isEmpty()) {
			String[] citizenships = { "ENGLISH", "FRENCH", "GERMAN", "RUSSIAN", "SERBIAN", "OTHER" };
			for (String citizenship : citizenships) {
				CitizenshipEntity c = new CitizenshipEntity();
				c.setDeleted(false);
				c.setCitizenship(citizenship);
				citizenshipRepository.save(c);
			}
		}
	}

	private void languageInit() {
		if (((List<LanguageEntity>) languageRepository.findAll()).isEmpty()) {
			String[] languages = { "ENGLISH", "GERMAN", "FRENCH" };
			String[] levels = { "A1", "A2", "B1", "B2", "C1", "C2" };
			for (String language : languages) {
				for (String level : levels) {
					LanguageEntity l = new LanguageEntity();
					l.setDeleted(false);
					l.setLanguage(language);
					l.setLevel(level);
					languageRepository.save(l);
				}
			}
		}
	}

	public static String getPassEncoded(String pass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(pass);
	}

}
