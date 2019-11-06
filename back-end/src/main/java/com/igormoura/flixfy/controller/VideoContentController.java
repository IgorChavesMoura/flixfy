package com.igormoura.flixfy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igormoura.flixfy.model.video.ContentType;
import com.igormoura.flixfy.model.video.Episode;
import com.igormoura.flixfy.model.video.VideoContent;
import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.service.UserService;
import com.igormoura.flixfy.service.VideoContentService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VideoContentController {

    @Autowired
    private VideoContentService videoContentService;

    @Autowired
    private UserService userService;


    @GetMapping("/vc/{id}")
    public ResponseEntity<?> getVideoContent(@PathVariable("id") Long vcId){

        Optional<VideoContent> videoContent = videoContentService.findOne(vcId);

        if(!videoContent.isPresent()){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(videoContent.get());

    }

    @GetMapping("/vc")
    public ResponseEntity<?> getVideoContent(@RequestParam(name="query", required = false) Optional<String> query,
                                             @RequestParam(name="category",required = false) Optional<Long> category){


        List<VideoContent> vcs;

        if(category.isPresent() && query.isPresent()){

            vcs = videoContentService.findByTitleAndCategory(query.get(),category.get());

        } else if(query.isPresent()){

            vcs = videoContentService.findByTitle(query.get());

        } else if(category.isPresent()){

            vcs = videoContentService.findByCategory(category.get());

        } else {

            vcs = new ArrayList<>();

        }


        return ResponseEntity.ok(vcs);


    }

    @GetMapping("/vc/picture/{id}")
    public ResponseEntity<byte[]> getVideoContentPicture(@PathVariable("id") Long vcId){

        VideoContent vc = videoContentService.findOne(vcId).get();

        String picturePath = vc.getPictureUrl();

        File picture = new File(picturePath);

        try {

            InputStream ips = new FileInputStream(picture);

            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //httpHeaders.setContentLength(doc.get().getFileSize());
            httpHeaders.setContentDispositionFormData("attachment", "picture");

            byte[] bytes = IOUtils.toByteArray(ips);

            return new ResponseEntity<byte[]>(bytes,httpHeaders,HttpStatus.OK);

        } catch (IOException e) {

            return ResponseEntity.notFound().build();

        }



    }

    @PostMapping("/vc/save")
    public ResponseEntity<?> createVideoContent( @RequestParam("id") Optional<Long> id,
                                                 @RequestParam("userId") Long userId,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("duration") Integer duration,
                                                 @RequestParam("year") Integer year,
                                                 @RequestParam("type")ContentType contentType,
                                                 @RequestParam("format") Long formatId,
                                                 @RequestParam("categories") List<Long> categories,
                                                 String[] episodes,
                                                 @RequestParam(name = "picture", required = false) Optional<MultipartFile> picture){


        try {

            List<Episode> episodeList = new ArrayList<>();



            if(episodes != null && episodes.length > 0){

                ObjectMapper objectMapper = new ObjectMapper();

                for(int i = 0; i < episodes.length; i++){

                    if(!episodes[i].equals("{}")){

                        Episode e = objectMapper.readValue(episodes[i], Episode.class);
                        episodeList.add(e);

                    }


                }

            }



            User owner = userService.findOne(userId).get();


            if(id.isPresent()){

                if(episodeList.size() > 0 && contentType.equals(ContentType.TV_SERIES)){

                    videoContentService.save(id.get(),title,duration,year,contentType,formatId,categories,episodeList,picture);


                } else {

                    videoContentService.save(id.get(),title,duration,year,contentType,formatId,categories,picture);

                }

            } else {

                if(episodeList.size() > 0 && contentType.equals(ContentType.TV_SERIES)){

                    videoContentService.save(title,duration,year,contentType,formatId,categories,owner,episodeList,picture);


                } else {

                    videoContentService.save(title,duration,year,contentType,formatId,categories,owner,picture);

                }

            }

        } catch (Exception e){

            e.printStackTrace();

            return ResponseEntity.badRequest().build();

        }



        return ResponseEntity.ok().build();

    }



    @DeleteMapping("/vc/{id}")
    public ResponseEntity<?> deleteVideoContent(@PathVariable("id") Long id){

        try {

            videoContentService.delete(id);

            return ResponseEntity.ok().build();

        } catch(Exception e){

            e.printStackTrace();

            return ResponseEntity.badRequest().build();

        }

    }

}
