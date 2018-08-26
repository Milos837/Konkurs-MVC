package com.example.jsptest.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.jsptest.entities.CitizenshipEntity;
import com.example.jsptest.entities.OfferingEntity;
import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.RequirementsEntity;
import com.example.jsptest.entities.ResponsibilitiesEntity;
import com.example.jsptest.entities.dto.ApplicationDto;
import com.example.jsptest.entities.enums.EGender;
import com.example.jsptest.repositories.CitizenshipRepository;
import com.example.jsptest.repositories.OfferingRepository;
import com.example.jsptest.repositories.PostingRepository;
import com.example.jsptest.repositories.ReponsibilitiesRepository;
import com.example.jsptest.repositories.RequirementsRepository;
import com.example.jsptest.services.ApplicationService;
import com.example.jsptest.services.FileHandler;

@Controller
public class MainController {

	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private RequirementsRepository requirementsRepository;

	@Autowired
	private ReponsibilitiesRepository responsibilitiesRepository;

	@Autowired
	private OfferingRepository offeringRepository;

	@Autowired
	private CitizenshipRepository citizenshipRepository;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private FileHandler fileHandler;

	@ModelAttribute("applicationDto")
	public ApplicationDto construct() {
		ApplicationDto newApp = new ApplicationDto();
		newApp.setGender(EGender.MALE);
		return newApp;
	}
	
	@GetMapping("/")
	public String mainPage() {
		return "main";
	}

	@RequestMapping(value = { "/postings"}, method = RequestMethod.GET)
	public String allPostings(Model model) {

		List<PostingEntity> postings = ((List<PostingEntity>) postingRepository.findAll())
				.stream().filter(posting -> !posting.getDeleted().equals(true))
				.collect(Collectors.toList());

		model.addAttribute("postings", postings);

		return "postings";
	}

	@GetMapping("/postings/{postingId}")
	public String getPosting(Model model, @PathVariable Integer postingId) {

		PostingEntity posting = postingRepository.findById(postingId).get();
		List<RequirementsEntity> req = requirementsRepository.findByPosting(posting);
		List<ResponsibilitiesEntity> res = responsibilitiesRepository.findByPosting(posting);
		List<OfferingEntity> off = offeringRepository.findByPosting(posting);
		posting.setRequirements(req);
		posting.setResponsibilities(res);
		posting.setOffering(off);

		model.addAttribute("posting", posting);

		return "posting-detail";
	}

	@GetMapping("/postings/{postingId}/apply")
	public String applyForm(Model model, @PathVariable Integer postingId) {

		PostingEntity posting = postingRepository.findById(postingId).get();
		List<CitizenshipEntity> citizenships = (List<CitizenshipEntity>) citizenshipRepository.findAll();

		model.addAttribute("posting", posting);
		model.addAttribute("citizenships", citizenships);
		if (!model.containsAttribute("applicationDto")) {
			model.addAttribute("applicationDto", new ApplicationDto());
		}
		return "apply-form";
	}

	@PostMapping("/postings/{postingId}/apply")
	public String addApplication(@PathVariable Integer postingId,
			@ModelAttribute("applicationDto") @Valid ApplicationDto applicationDto, BindingResult result,
			@RequestParam("file") MultipartFile file, Model model) {

		if (result.hasErrors()) {
			PostingEntity posting = postingRepository.findById(postingId).get();
			List<CitizenshipEntity> citizenships = (List<CitizenshipEntity>) citizenshipRepository.findAll();

			model.addAttribute("posting", posting);
			model.addAttribute("citizenships", citizenships);
			return "apply-form";
		} else {
			Integer appId = applicationService.save(postingId, applicationDto).getId();
			fileHandler.uploadCv(file, appId);
			return "apply-success";
		}
	}

}
