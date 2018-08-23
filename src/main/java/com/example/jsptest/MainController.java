package com.example.jsptest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@ModelAttribute("applicationDTO")
	public ApplicationDto construct() {
		ApplicationDto newApp = new ApplicationDto();
		newApp.setGender(EGender.MALE);
		return newApp;
	}
	
	@RequestMapping(value = { "/postings", "/" }, method = RequestMethod.GET)
    public String allPostings(Model model) {
 
        List<PostingEntity> postings  = (List<PostingEntity>) postingRepository.findAll();
 
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
		return "apply-form";
	}
	
	@PostMapping("/postings/{postingId}/apply")
	public String addApplication(@PathVariable Integer postingId,
			@ModelAttribute("applicationDto") ApplicationDto applicationDto, @RequestParam("file") MultipartFile file) {
		Integer appId = applicationService.save(postingId, applicationDto).getId();
		fileHandler.uploadCv(file, appId);
		return "apply-success";
	}
	
	

}
