package com.library.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Library;

@Repository
public interface LibraryDao extends JpaRepository<Library, Integer>{

}
