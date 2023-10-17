package md.mirrerror.managers;

import md.mirrerror.entities.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoryManager {

    private final List<Repository> activeRepositories = new ArrayList<>();

    public Repository registerRepository(String repositoryName) {
        File directory = new File(repositoryName);

        if(!directory.exists()) {
            System.out.println("Couldn't create a repository with name: \"" + repositoryName + "\". The specified directory doesn't exist.");
            return null;
        }

        if(!directory.isDirectory()) {
            System.out.println("The application tried to register a repository using a file which is not a directory.");
            return null;
        }

        for(Repository repo : activeRepositories)
            if(repo.getDirectory().getPath().equals(directory.getPath())) {
                System.out.println("This directory is already registered as a repository.");
                return null;
            }

        Repository repository = new Repository(directory);

        activeRepositories.add(repository);
        System.out.println("Successfully registered a repository.");
        return repository;
    }

    public List<Repository> getActiveRepositories() {
        return Collections.unmodifiableList(activeRepositories);
    }
}
