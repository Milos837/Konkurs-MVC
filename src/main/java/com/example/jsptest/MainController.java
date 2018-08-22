package com.example.jsptest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.jsptest.entities.OfferingEntity;
import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.RequirementsEntity;
import com.example.jsptest.entities.ResponsibilitiesEntity;
import com.example.jsptest.repositories.OfferingRepository;
import com.example.jsptest.repositories.PostingRepository;
import com.example.jsptest.repositories.ReponsibilitiesRepository;
import com.example.jsptest.repositories.RequirementsRepository;

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
	
	@RequestMapping(value = { "/postings" }, method = RequestMethod.GET)
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
	
	

}
