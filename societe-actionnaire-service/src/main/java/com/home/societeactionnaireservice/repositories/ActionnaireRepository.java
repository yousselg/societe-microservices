package com.home.societeactionnaireservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.societeactionnaireservice.entities.Actionnaire;

@Repository
public interface ActionnaireRepository extends JpaRepository<Actionnaire, Long> {

}
