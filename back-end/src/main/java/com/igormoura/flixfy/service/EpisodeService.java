package com.igormoura.flixfy.service;

import com.igormoura.flixfy.model.video.Episode;
import com.igormoura.flixfy.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    public List<Episode> findAll(){

        return episodeRepository.findAll();

    }

    public Optional<Episode> findOne(Long id){

        return Optional.ofNullable(episodeRepository.getOne(id));

    }

    public Episode save(Episode e){

        if(e.getId() != null){

            Episode episodeDB = episodeRepository.getOne(e.getId());

            episodeDB.setTitle(e.getTitle());
            episodeDB.setDuration(e.getDuration());

            episodeDB = episodeRepository.save(episodeDB);

            return episodeDB;


        } else {

            Episode episodeDB = episodeRepository.save(e);

            return episodeDB;

        }

    }

    public void delete(Long id){

        //e.setVideoContent(null);

        //e = episodeRepository.save(e);

        episodeRepository.deleteById(id);

    }

}
