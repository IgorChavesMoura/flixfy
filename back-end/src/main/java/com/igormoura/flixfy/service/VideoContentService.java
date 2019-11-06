package com.igormoura.flixfy.service;

import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.model.video.*;
import com.igormoura.flixfy.repository.VideoContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoContentService {

	@Value("${root.folder}")
	private String UPLOAD_FOLDER;

	@Autowired
	private FormatService formatService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private EpisodeService episodeService;

	@Autowired
	private VideoContentRepository videoContentRepository;
	
	public List<VideoContent> findAll(){
		
		return videoContentRepository.findAll();
		
	}

	public List<VideoContent> findByOwner(User owner){

		return videoContentRepository.findByOwner(owner);

	}

	public List<VideoContent> findByTitle(String query){

	    return videoContentRepository.findByTitleContaining(query);

    }

    public List<VideoContent> findByCategory(Long categoryId){

		Category category = categoryService.findOne(categoryId).get();

		return videoContentRepository.findByCategories(category);

	}

    public List<VideoContent> findByTitleAndCategory(String title, Long categoryId){


	    Category category = categoryService.findOne(categoryId).get();

	    return videoContentRepository.findByTitleContainingAndCategories(title,category);

    }

	public Optional<VideoContent> findOne(Long id){
		
		return Optional.ofNullable(videoContentRepository.getOne(id));
		
	}


	
	public VideoContent save(VideoContent vc) {
		
		if(vc.getId() != null) {
			
			VideoContent vcDB = videoContentRepository.getOne(vc.getId());
			
			vcDB.setTitle(vc.getTitle());
			vcDB.setDuration(vc.getDuration());
			vcDB.setYear(vc.getYear());

			vcDB.setFormat(formatService.findOne(vc.getFormat().getId()).get());

			List<Category> vcCategories = new ArrayList<>();

			for(Category c : vc.getCategories()){

				Category categoryDB = categoryService.findOne(c.getId()).get();

				vcCategories.add(categoryDB);

			}

			vcDB.setCategories(vcCategories);

			if(vcDB.getType() == ContentType.TV_SERIES){

				List<Episode> vcEpisodes = new ArrayList<>();

				for(Episode e : vc.getEpisodes()){

					if(e.getId() != null){

						Episode episodeDB = episodeService.save(e);

						vcEpisodes.add(episodeDB);

					}

				}

				vcDB.setEpisodes(vcEpisodes);

			}

			vcDB = videoContentRepository.save(vcDB);
			
			return vcDB;
			
		} else {
			
			VideoContent vcDB = videoContentRepository.save(vc);

			vcDB.setFormat(formatService.findOne(vc.getFormat().getId()).get());

			List<Category> vcCategories = new ArrayList<>();

			for(Category c : vc.getCategories()){

				Category categoryDB = categoryService.findOne(c.getId()).get();

				vcCategories.add(categoryDB);

			}

			vcDB.setCategories(vcCategories);

			if(vcDB.getType() == ContentType.TV_SERIES){

				List<Episode> vcEpisodes = new ArrayList<>();

				for(Episode e : vc.getEpisodes()){

					if(e.getId() != null){

						Episode episodeDB = episodeService.save(e);

						vcEpisodes.add(episodeDB);

					}

				}

				vcDB.setEpisodes(vcEpisodes);

			}

			vcDB = videoContentRepository.save(vc);
			
			return vcDB;
			
		}
		
	}

	public VideoContent save(String title, Integer duration, Integer year, ContentType contentType, Long formatId, List<Long> categoryIds, User owner, Optional<MultipartFile> picture){

		VideoContent vc = new VideoContent();

		vc.setTitle(title);
		vc.setDuration(duration);
		vc.setYear(year);
		vc.setOwner(owner);
		vc.setType(contentType);

		Format f = formatService.findOne(formatId).get();

		vc.setFormat(f);

		List<Category> categories = new ArrayList<>();

		for(Long categoryId : categoryIds){

			Category c = categoryService.findOne(categoryId).get();

			categories.add(c);

		}

		vc.setCategories(categories);

		vc = videoContentRepository.save(vc);

		if(picture.isPresent()){

			saveVideoContentImage(picture.get(),vc);

		}

		return vc;

	}

	public VideoContent save(Long id, String title, Integer duration, Integer year, ContentType contentType, Long formatId, List<Long> categoryIds, List<Episode> episodes, Optional<MultipartFile> picture){

        VideoContent vc = videoContentRepository.getOne(id);

        vc.setTitle(title);
        vc.setDuration(duration);
        vc.setYear(year);
        vc.setType(contentType);

        Format f = formatService.findOne(formatId).get();

        vc.setFormat(f);

        List<Category> categories = new ArrayList<>();

        for(Long categoryId : categoryIds){

            Category c = categoryService.findOne(categoryId).get();

            categories.add(c);

        }

        vc.setCategories(categories);

        List<Episode> orphanEpisodes = new ArrayList<>();

        for(Episode e : vc.getEpisodes()){

        	if(!episodes.contains(e)){

        		orphanEpisodes.add(e);

			}

		}

        vc.setEpisodes(episodes);

        vc = videoContentRepository.save(vc);

        if(picture.isPresent()){

            saveVideoContentImage(picture.get(),vc);

        }

        for(Episode oe : orphanEpisodes){

        	episodeService.delete(oe.getId());

		}

        return vc;

    }

    public VideoContent save(Long id, String title, Integer duration, Integer year, ContentType contentType, Long formatId, List<Long> categoryIds, Optional<MultipartFile> picture){

        VideoContent vc = videoContentRepository.getOne(id);

        vc.setTitle(title);
        vc.setDuration(duration);
        vc.setYear(year);
        vc.setType(contentType);

        Format f = formatService.findOne(formatId).get();

        vc.setFormat(f);

        List<Category> categories = new ArrayList<>();

        for(Long categoryId : categoryIds){

            Category c = categoryService.findOne(categoryId).get();

            categories.add(c);

        }

        vc.setCategories(categories);

        vc = videoContentRepository.save(vc);

        if(picture.isPresent()){

            saveVideoContentImage(picture.get(),vc);

        }


        return vc;

    }


	public VideoContent save(String title, Integer duration, Integer year, ContentType contentType, Long formatId, List<Long> categoryIds, User owner, List<Episode> episodes, Optional<MultipartFile> picture){

		VideoContent vc = new VideoContent();

		vc.setTitle(title);
		vc.setDuration(duration);
		vc.setYear(year);
		vc.setOwner(owner);
		vc.setType(contentType);

		Format f = formatService.findOne(formatId).get();

		vc.setFormat(f);

		List<Category> categories = new ArrayList<>();

		for(Long categoryId : categoryIds){

			Category c = categoryService.findOne(categoryId).get();

			categories.add(c);

		}

		vc.setCategories(categories);

		List<Episode> vcEpisodes = new ArrayList<>();

		for(Episode e : episodes){

			e = episodeService.save(e);

			vcEpisodes.add(e);

		}

		vc.setEpisodes(vcEpisodes);

		vc = videoContentRepository.save(vc);

		if(picture.isPresent()){

			saveVideoContentImage(picture.get(),vc);

		}

		return vc;

	}

	
	public void delete(Long id) {

		VideoContent vc = findOne(id).get();

		if(vc.getPictureUrl() != null){

			File picture = new File(vc.getPictureUrl());

			if(picture.exists()){

				picture.delete();

			}

		}

		List<Episode> episodes = vc.getEpisodes();

		List<Episode> orphanEpisodes = new ArrayList<>();

		orphanEpisodes.addAll(episodes);


		vc.setEpisodes(new ArrayList<>());
		vc.setCategories(new ArrayList<>());

		vc = videoContentRepository.save(vc);

		videoContentRepository.delete(vc);

		for(Episode oe : orphanEpisodes){

			episodeService.delete(oe.getId());

		}

	}

	public void addVideoToUser(Long videoId, User u){

		VideoContent vc = findOne(videoId).get();

		vc.setOwner(u);

		save(vc);

	}

	public void saveVideoContentImage(MultipartFile imgFile, VideoContent vc){

		String imgDir = FileSystems.getDefault()
				.getPath("")
				.toAbsolutePath()
				.toString() + "/" + UPLOAD_FOLDER + "/video_imgs/";


		String dbPictureUrl = UPLOAD_FOLDER + "/video_imgs/" + vc.getId();

		File dir = new File(imgDir);

		if(!dir.exists()){

			dir.mkdirs();
		}

		String imgFilePath = imgDir + vc.getId();

		try {

			imgFile.transferTo(new File(imgFilePath));

		} catch(IOException ioex){

			ioex.printStackTrace();

		}



		vc.setPictureUrl(dbPictureUrl);

		save(vc);

	}

}
