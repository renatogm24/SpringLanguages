package com.codingdojo.springlanguages.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.springlanguages.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long>{
	List<Language> findAll();
}
