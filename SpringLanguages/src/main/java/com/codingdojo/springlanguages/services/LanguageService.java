package com.codingdojo.springlanguages.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.springlanguages.models.Language;
import com.codingdojo.springlanguages.repositories.LanguageRepository;


@Service
public class LanguageService {

	private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }
    // devuelve todos los libros
    public List<Language> allLanguages() {
        return languageRepository.findAll();
    }
    // crea un libro
    public Language createLanguage(Language b) {
        return languageRepository.save(b);
    }
    //actualiza un libro
    public Language updateLanguage(Language b) {
        return languageRepository.save(b);
    }
    // recupera un libro
    public Language findLanguage(Long id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if(optionalLanguage.isPresent()) {
            return optionalLanguage.get();
        } else {
            return null;
        }
    }
    
    public void deleteLanguage(Long id) {
    	languageRepository.deleteById(id);
    }
	
}
