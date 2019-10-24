package com.igormoura.flixfy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igormoura.flixfy.model.video.Format;

@Repository
public interface FormatRepository extends JpaRepository<Format, Long>{

}
