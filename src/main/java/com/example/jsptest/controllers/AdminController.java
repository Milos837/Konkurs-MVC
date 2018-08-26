package com.example.jsptest.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.repositories.PostingRepository;

@Controller
public class AdminController {
	
	@Autowired
	private PostingRepository postingRepository;
	
	@GetMapping("/admin/")
	public String mainPage() {
		return "main";
	}
	
	// TODO	Treba naci bolje resenje
	@GetMapping("/admin/postings/")
	public String deletePosting(@RequestParam(required = false) Integer postingId, @RequestParam(required = false) String command, Model model) {
		if (postingId == null) {
			List<PostingEntity> postings = ((List<PostingEntity>) postingRepository.findAll())
					.stream().filter(posting -> !posting.getDeleted().equals(true))
					.collect(Collectors.toList());
			
			model.addAttribute("postings", postings);
			return "admin-postings";
			
		} else if (command.equals("DELETE")) {
			if (postingRepository.existsById(postingId)) {
				PostingEntity posting = postingRepository.findById(postingId).get();
				posting.setDeleted(true);
				postingRepository.save(posting);
				
				List<PostingEntity> postings = ((List<PostingEntity>) postingRepository.findAll())
						.stream().filter(posting1 -> !posting1.getDeleted().equals(true))
						.collect(Collectors.toList());
				
				model.addAttribute("postings", postings);
				return "admin-postings";
			}
		}
		
		return "main";
	}

}
