package com.example.ahmed.restapidemo.service;

import com.example.ahmed.restapidemo.model.JobPost;
import com.example.ahmed.restapidemo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobPost> getAllJobs(){
        return  jobRepository.getAllJobs();
    }

    public JobPost getJobById(int id){
        return  jobRepository.getJobById(id);
    }
    public List<JobPost> getJobByName(String name){
        return  jobRepository.getJobByName(name);
    }

    public void addJob(JobPost job){
        jobRepository.addJob(job);
    }

    public  void uploadFile(MultipartFile file){
        try {
            jobRepository.uploadPost(file);

        }catch (IOException e){
            e.printStackTrace();
        }
  }
}
