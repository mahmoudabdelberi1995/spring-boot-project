package com.demo.repository;

import com.demo.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository  extends JpaRepository<Formation ,Long> {
}
