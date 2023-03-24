package com.mg.loggingservice.repository;

import com.mg.loggingservice.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

}
