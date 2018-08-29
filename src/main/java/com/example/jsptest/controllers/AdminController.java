package com.example.jsptest.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.AttachmentEntity;
import com.example.jsptest.entities.LanguageEntity;
import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.dto.PostingDto;
import com.example.jsptest.entities.util.EmailObject;
import com.example.jsptest.repositories.ApplicationRepository;
import com.example.jsptest.repositories.AttachmentRepository;
import com.example.jsptest.repositories.PostingRepository;
import com.example.jsptest.services.ApplicationService;
import com.example.jsptest.services.EmailService;
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
	
	@Autowired
	private EmailService emailService;
	
	@ModelAttribute("email")
	public EmailObject construct1() {
		return new EmailObject();
	}

	@ModelAttribute("postingDto")
	public PostingDto construct2() {
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
			List<ApplicationEntity> applications = ((List<ApplicationEntity>) applicationRepository.findByPosting(posting))
					.stream().filter(app -> !app.getDeleted().equals(true))
					.collect(Collectors.toList());

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
				AttachmentEntity attachment = attachmentRepository.findByApplication(app);

				response.setContentType("application/octet-stream");
				response.setContentLength(attachment.getCv().length);
				response.setHeader("content-disposition", "attachment; filename=" + "cv.pdf");

				FileCopyUtils.copy(attachment.getCv(), response.getOutputStream());

			}

		}
	}

	// Preuzmi motivaciono pismo za aplikaciju
	@GetMapping("/admin/postings/{postingId}/applications/{appId}/downloadML/")
	public void downloadMl(@PathVariable Integer appId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()) {
			ApplicationEntity app = applicationRepository.findById(appId).get();

			if (attachmentRepository.existsByApplication(app)
					&& attachmentRepository.findByApplication(app).getMotivation() != null) {
				AttachmentEntity attachment = attachmentRepository.findByApplication(app);

				response.setContentType("application/octet-stream");
				response.setContentLength(attachment.getMotivation().length);
				response.setHeader("content-disposition", "attachment; filename=" + "motivaciono_pismo.pdf");

				FileCopyUtils.copy(attachment.getMotivation(), response.getOutputStream());

			}

		}
	}

	// Preuzmi propratno pismo za aplikaciju
	@GetMapping("/admin/postings/{postingId}/applications/{appId}/downloadCL/")
	public void downloadCl(@PathVariable Integer appId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (applicationRepository.existsById(appId) && !applicationRepository.findById(appId).get().getDeleted()) {
			ApplicationEntity app = applicationRepository.findById(appId).get();

			if (attachmentRepository.existsByApplication(app)
					&& attachmentRepository.findByApplication(app).getCoverLetter() != null) {
				AttachmentEntity attachment = attachmentRepository.findByApplication(app);

				response.setContentType("application/octet-stream");
				response.setContentLength(attachment.getCoverLetter().length);
				response.setHeader("content-disposition", "attachment; filename=" + "propratno_pismo.pdf");

				FileCopyUtils.copy(attachment.getCoverLetter(), response.getOutputStream());

			}

		}
	}

	// Obrisi aplikaciju
	@GetMapping("/admin/postings/{postingId}/applications/{appId}/delete")
	public String deleteApplication(@PathVariable Integer postingId, @PathVariable Integer appId, Model model) {
		if (postingRepository.existsById(postingId) && applicationRepository.existsById(appId)
				&& applicationRepository.findByPosting(postingRepository.findById(postingId).get()) != null) {

			ApplicationEntity app = applicationRepository.findById(appId).get();
			app.setDeleted(true);
			applicationRepository.save(app);

			return "redirect:/admin/postings/{postingId}";
		}
		return "main";
	}
	
	//	Posalji email
	@PostMapping("/admin/postings/{postingId}/applications/{appId}/sendEmail")
	public String sendEmail(@ModelAttribute("email") EmailObject email, RedirectAttributes redirect,
			@PathVariable Integer postingId, @PathVariable Integer appId) {
		Boolean status = emailService.sendSimpleEmail(email);
		if (status) {
			redirect.addFlashAttribute("emailSuccess", "Email has been successfully sent.");
			return "redirect:/admin/postings/{postingId}/applications/{appId}";
		} else {
			redirect.addFlashAttribute("emailError", "Email sending failed.");
			return "redirect:/admin/postings/{postingId}/applications/{appId}";
		}
	}

}
