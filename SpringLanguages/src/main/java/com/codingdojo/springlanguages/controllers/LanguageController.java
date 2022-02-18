package com.codingdojo.springlanguages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.springlanguages.models.Language;
import com.codingdojo.springlanguages.services.LanguageService;



@Controller
public class LanguageController {
	private final LanguageService languageService;

	public LanguageController(LanguageService languageService){
	        this.languageService = languageService;
	    }

	@RequestMapping("/languages")
	 public String index(Model model, @ModelAttribute("language") Language language) {
        List<Language> languages = languageService.allLanguages();
        model.addAttribute("languages", languages);
        return "/languages/index.jsp";
    }

	@RequestMapping(value = "/languages", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("language") Language language, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("languages", languageService.allLanguages());
			return "/languages/index.jsp";
		}
		
		languageService.createLanguage(language);
		return "redirect:/languages";
	}
	
	@RequestMapping("/languages/new")
	 public String newExpense(@ModelAttribute("language") Language language) {
       return "/languages/new.jsp";
   }

	@RequestMapping("/languages/{id}")
	public String show(Model model, @PathVariable("id") Long id) {
		Language language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "show.jsp";
	}
	
	@GetMapping("/languages/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "/languages/edit.jsp";	
	}

	@PutMapping("/languages/{id}")
	public String update(@Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
			return "/languages/edit.jsp";
		} else {
			languageService.updateLanguage(language);
			return "redirect:/languages";
		}
	}
	
	@DeleteMapping("/languages/{id}")
    public String destroy(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
        return "redirect:/languages";
    }
}
