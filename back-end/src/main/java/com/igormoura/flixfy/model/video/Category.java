package com.igormoura.flixfy.model.video;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	private String description;

	@JsonIgnore
	@ManyToMany(mappedBy="categories")
	private List<VideoContent> videos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<VideoContent> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoContent> videos) {
		this.videos = videos;
	}


	@Override
	public boolean equals(Object obj) {

		if(!(obj instanceof Category)){

			return false;

		}

		Category c = (Category) obj;

		return this.id.equals(c.getId());
	}
}
