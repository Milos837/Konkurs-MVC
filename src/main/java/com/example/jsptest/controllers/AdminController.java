package com.example.jsptest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.dto.PostingDto;
import com.example.jsptest.repositories.PostingRepository;
import com.example.jsptest.services.PostingService;

@Controller
public class AdminController {

	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private PostingService postingService;

	@ModelAttribute("postingDto")
	public PostingDto construct() {
		PostingDto posting = new PostingDto();
//		List<String> res = new ArrayList<>();
//		List<String> req = new ArrayList<>();
//		List<String> off = new ArrayList<>();
		posting.setResponsibilities(new AutoPopulatingList<String>(String.class));
		posting.setRequirements(new AutoPopulatingList<String>(String.class));
		posting.setOffering(new AutoPopulatingList<String>(String.class));
		return posting;
	}

	//	Vrati pocetnu stranu
	@GetMapping("/admin/")
	public String mainPage() {
		return "main";
	}

	//	Vrati sve konkurse
	@GetMapping("/admin/postings/")
	public String getPostings(Model model) {
		List<PostingEntity> postings = postingService.getActive();

		model.addAttribute("postings", postings);
		return "admin-postings";
	}

	//	Obrisi konkurs
	@GetMapping("/admin/postings/{postingId}/delete")
	public String deletePosting(@PathVariable Integer postingId, Model model) {
		if (postingRepository.existsById(postingId)) {
			PostingEntity posting = postingRepository.findById(postingId).get();
			posting.setDeleted(true);
			postingRepository.save(posting);

			return "redirect:/admin/postings/";
		}
		return "main";
	}

	//	Vrati formu za dodavanje konkursa
	@GetMapping("/admin/postings/new")
	public String postingForm(Model model) {
		return "posting-form";
	}

	//	Dodaj novi konkurs
	@PostMapping("/admin/postings/new")
	public String addPosting(@Valid @ModelAttribute("postingDto") PostingDto postingDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "posting-form";

		} else {
			postingService.save(postingDto);

			List<PostingEntity> postings = postingService.getActive();

			model.addAttribute("postings", postings);
			return "admin-postings";
		}
	}

}
