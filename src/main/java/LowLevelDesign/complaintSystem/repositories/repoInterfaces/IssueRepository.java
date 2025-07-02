package LowLevelDesign.complaintSystem.repositories.repoInterfaces;

import LowLevelDesign.complaintSystem.entity.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueRepository {
    void save(Issue issue);
    Optional<Issue> findById(String id);
    List<Issue> findByCustomerEmail(String email);
    List<Issue> findByType(String type);
}