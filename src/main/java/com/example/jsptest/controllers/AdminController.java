package com.example.jsptest.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.AttachmentEntity;
import com.example.jsptest.entities.LanguageEntity;
import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.dto.PostingDto;
import com.example.jsptest.repositories.ApplicationRepository;
import com.example.jsptest.repositories.AttachmentRepository;
import com.example.jsptest.repositories.PostingRepository;
import com.example.jsptest.services.ApplicationService;
import com.example.jsptest.services.PostingService;

@Controller
public class AdminController {

	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private PostingService postingService;

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private AttachmentRepository attachmentRepository;

	@ModelAttribute("postingDto")
	public PostingDto construct() {
		PostingDto posting = new PostingDto();
		posting.setResponsibilities(new AutoPopulatingList<String>(String.class));
		posting.setRequirements(new AutoPopulatingList<String>(String.class));
		posting.setOffering(new AutoPopulatingList<String>(String.class));
		return posting;
	}

	// Vrati pocetnu stranu
	@GetMapping("/admin/")
	public String mainPage() {
		return "main";
	}

	// Vrati sve konkurse
	@GetMapping("/admin/postings/")
	public String getPostings(Model model) {
		List<PostingEntity> postings = postingService.getActive();

		model.addAttribute("postings", postings);
		return "admin-postings";
	}

	// Obrisi konkurs
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

	// Vrati formu za dodavanje konkursa
	@GetMapping("/admin/postings/new")
	public String postingForm(Model model) {
		return "posting-form";
	}

	// Dodaj novi konkurs
	@PostMapping("/admin/postings/new")
	public String addPosting(@Valid @ModelAttribute("postingDto") PostingDto postingDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "posting-form";

		} else {
			postingService.save(postingDto);

			return "redirect:/admin/postings/";
		}
	}

	// Vrati aplikacije za konkurs
	@GetMapping("/admin/postings/{postingId}")
	public String getPosting(@PathVariable Integer postingId, Model model) {
		if (postingRepository.existsById(postingId)) {
			PostingEntity posting = postingRepository.findById(postingId).get();
			List<ApplicationEntity> applications = applicationRepository.findByPosting(posting);

			model.addAttribute("posting", posting);
			model.addAttribute("applications", applications);
			return "admin-posting-detail";
		}
		return "main";
	}

	// Vrati aplikaciju
	@GetMapping("/admin/postings/{postingId}/applications/{appId}")
	public String getApplication(@PathVariable Integer postingId, @PathVariable Integer appId, Model model) {
		if (postingRepository.existsById(postingId) && applicationRepository.existsById(appId)
				&& applicationRepository.findByPosting(postingRepository.findById(postingId).get()) != null) {

			PostingEntity posting = postingRepository.findById(postingId).get();
			ApplicationEntity app = applicationRepository.findById(appId).get();
			List<LanguageEntity> languages = applicationService.getLanguagesForApplication(appId);
			model.addAttribute("posting", posting);
			model.addAttribute("application", app);
			model.addAttribute("languages", languages);

			return "admin-app-detail";
		}
		return "main";
	}

	// Preuzmi CV za aplikaciju
	@GetMapping("/admin/postings/{postingId}/applications/{appId}/downloadCV/")
	public void downloadCv(@PathVariable Integer appId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()) {
			ApplicationEntity app = applicationRepository.findById(appId).get();

			if (attachmentRepository.existsByApplication(app)) {
				AttachmentEntity cv = attachmentRepository.findByApplication(app);

				response.setContentType("application/octet-stream");
				response.setContentLength(cv.getCv().length);
				response.setHeader("content-disposition", "attachment; filename=" + "cv.pdf");

				FileCopyUtils.copy(cv.getCv(), response.getOutputStream());

			}

		}
	}

}
