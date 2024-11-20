package com.gautam.JPA_Tutorial.repositories;

import com.gautam.JPA_Tutorial.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {

}
