package com.example.ahmed.restapidemo.controller;

import com.example.ahmed.restapidemo.model.JobPost;
import com.example.ahmed.restapidemo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public List<JobPost> jobs(){
        return  jobService.getAllJobs();
    }

    @GetMapping("/searchJob/{query}")
    public List<JobPost> searchJob(@PathVariable("query") String name){
        return  jobService.getJobByName(name);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity jobById(@PathVariable("id") int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/jobs"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
       final HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
       Map map = new HashMap();
       map.put("response", response.body());
       JobPost post = jobService.getJobById(id);
        return   ResponseEntity.ok(map);
//       if(post == null){
//           map.put("status",404);
//           map.put("message","Job not found");
//           return  new ResponseEntity(map, HttpStatusCode.valueOf(499));
//
//       }else {
//           map.put("status", 200);
//           map.put("message", "Job get successFully");
//           map.put("data", post);
//           return   ResponseEntity.ok(map);
//       }

    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file){
        jobService.uploadFile(file);
        return  ResponseEntity.ok("done");
    }

    @PostMapping("/addJob")
    public ResponseEntity addJob(@RequestBody() JobPost post){
        Map map = new HashMap();
        ResponseEntity response = new ResponseEntity(map,HttpStatusCode.valueOf(200));

        if(post.getPostProfile() == null){
            map.put("status",401);
            map.put("mesage","please add profile");
            response = new ResponseEntity(map,HttpStatusCode.valueOf(402
            ));
        }else if(post.getPostDesc() == null){
            map.put("status",401);
            map.put("mesage","please add description");
            response = (ResponseEntity) ResponseEntity.unprocessableEntity();
        }else if(post.getPostId() == 0){
            map.put("status",401);
            map.put("mesage","please add id");
        }
          jobService.addJob(post);
          return response ;
    }
}
