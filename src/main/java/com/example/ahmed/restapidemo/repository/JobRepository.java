package com.example.ahmed.restapidemo.repository;

import com.example.ahmed.restapidemo.model.JobPost;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JobRepository {

    private final List<JobPost> jobs = new ArrayList<>(Arrays.asList(
            new JobPost(1, "Java developer", "", 4, Arrays.asList("Java", "Android", "Spring JPA", "Spring IOC")),
            new JobPost(2, "Full-Stack developer", "", 4, Arrays.asList("Java", "Android", "Spring JPA", "Spring IOC")),
            new JobPost(3, "Python developer", "", 1, Arrays.asList("Java", "Android", "Spring JPA", "Spring IOC")),
            new JobPost(4, "Flutter developer", "", 1, Arrays.asList("Java", "Android", "Spring JPA", "Spring IOC")),
            new JobPost(5, "Web Designer", "", 2, Arrays.asList("Java", "Android", "Spring JPA", "Spring IOC"))

    ));

    public List<JobPost> getAllJobs() {
        return jobs;
    }

    public List<JobPost> getJobByName(String name) {
        ArrayList<JobPost> matchJobs = new ArrayList<>();
        for (JobPost post : jobs) {
            if (post.getPostProfile().contains(name)) {
                matchJobs.add(post);
            }
        }
        return matchJobs;
    }

    public JobPost getJobById(int id) {
        for (JobPost post : jobs) {
            if (post.getPostId() == id) {
                return post;
            }
        }
        return null;
    }

    public void addJob(JobPost jobPost) {
        jobs.add(0, jobPost);
    }

    public  void uploadPost(MultipartFile file) throws IOException {
        InputStream stream = file.getInputStream();
        System.out.println(Paths.get("static"));

        Files.copy(stream, Path.of(Paths.get("F:\\spring boot\\restapidemo\\src\\main\\resources\\static\\" + file.getOriginalFilename()).toUri()),
                StandardCopyOption.REPLACE_EXISTING);
    }

}
