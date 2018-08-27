package com.example.jsptest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<String> res = new ArrayList<>();
		List<String> req = new ArrayList<>();
		List<String> off = new ArrayList<>();
		posting.setResponsibilities(res);
		posting.setRequirements(req);
		posting.setOffering(off);
		return posting;
	}

	@GetMapping("/admin/")
	public String mainPage() {
		return "main";
	}

	// TODO Treba naci bolje resenje
	@GetMapping("/admin/postings/")
	public String deletePosting(@RequestParam(required = false) Integer postingId,
			@RequestParam(required = false) String command, Model model) {
		if (postingId == null) {
			List<PostingEntity> postings = postingService.getActive();

			model.addAttribute("postings", postings);
			return "admin-postings";

		} else if (command.equals("DELETE")) {
			if (postingRepository.existsById(postingId)) {
				PostingEntity posting = postingRepository.findById(postingId).get();
				posting.setDeleted(true);
				postingRepository.save(posting);

				List<PostingEntity> postings = postingService.getActive();

				model.addAttribute("postings", postings);
				return "admin-postings";
			}
		}

		return "main";
	}
	
	@GetMapping("/admin/postings/new")
	public String postingForm(Model model) {
		return "posting-form";
	}

	@PostMapping("/admin/postings/new")
	public String addPosting(@Valid @ModelAttribute("postingDto") PostingDto postingDto, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			return "posting-form";

		} else {
			postingService.save(postingDto);

			List<PostingEntity> postings = postingService.getActive();

			model.addAttribute("postings", postings);
			return "admin-postings";
		}
	}
	
	@PostMapping("/admin/postings/new/responsibility")
	public String addResponsibility(@RequestParam String responsibility, Model model) {
		construct().getResponsibilities().add(responsibility);
		return "redirect:/admin/postings/new";
	}

}
