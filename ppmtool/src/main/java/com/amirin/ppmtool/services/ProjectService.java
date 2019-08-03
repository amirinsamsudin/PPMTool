package com.amirin.ppmtool.services;

import com.amirin.ppmtool.domain.Project;
import com.amirin.ppmtool.exceptions.ProjectIdException;
import com.amirin.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try{
            return projectRepository.save(project);
        }catch (Exception e){
            throw  new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw  new ProjectIdException("Project ID " + projectId + " does not exists");
        }

        return project;
    }
}
