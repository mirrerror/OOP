package managers;

import entities.Repository;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class RepositoryManager {

    private List<Repository> activeRepositories;

    public void registerRepository(File directory, Repository repository) {
        if(!directory.isDirectory()) {
            System.out.println("The application tried to register a repository using a file which is not a directory.");
            return;
        }

        for(Repository repo : activeRepositories)
            if(repo.getDirectory().getPath().equals(directory.getPath())) {
                System.out.println("This directory is already registered as a repository.");
                return;
            }

        activeRepositories.add(repository);
        System.out.println("Successfully registered a repository.");
    }

    public void saveRepositories() {
        for(Repository repository : activeRepositories) {

        }
    }

    public List<Repository> getActiveRepositories() {
        return Collections.unmodifiableList(activeRepositories);
    }
}
