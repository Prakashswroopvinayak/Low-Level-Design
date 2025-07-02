package LowLevelDesign.complaintSystem.repositories;

import LowLevelDesign.complaintSystem.entity.Issue;
import LowLevelDesign.complaintSystem.repositories.repoInterfaces.IssueRepository;

import java.util.*;

public class InMemoryIssueRepository implements IssueRepository {
    private final Map<String, Issue> issues = new HashMap<>();
    private final Map<String, List<Issue>> userMap = new HashMap<>();

    public void save(Issue issue) {
        issues.put(issue.getId(), issue);
        userMap.computeIfAbsent(issue.getCustomerEmail(), k -> new ArrayList<>()).add(issue);
    }

    public Optional<Issue> findById(String id) {
        return Optional.ofNullable(issues.get(id));
    }

    public List<Issue> findByCustomerEmail(String email) {
        return userMap.getOrDefault(email, new ArrayList<>());
    }

    public List<Issue> findByType(String type) {
        return issues.values().stream().filter(i -> i.getType().equals(type)).toList();
    }
}
