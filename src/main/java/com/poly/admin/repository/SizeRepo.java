package com.poly.admin.repository;

import com.poly.admin.model.SizeSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepo extends JpaRepository<SizeSample, Integer> {
}
