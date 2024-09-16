package com.gautam.JPA.repositories;

import com.gautam.JPA.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository  extends JpaRepository<Video , Integer> {
}
