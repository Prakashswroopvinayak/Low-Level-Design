package LowLevelDesign.complaintSystem.services;

import LowLevelDesign.complaintSystem.entity.Issue;
import LowLevelDesign.complaintSystem.enums.IssueStatus;
import LowLevelDesign.complaintSystem.repositories.repoInterfaces.IssueRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IssueService {
    private final IssueRepository issueRepo;
    private final AtomicInteger counter = new AtomicInteger(1);

    public IssueService(IssueRepository repo) {
        this.issueRepo = repo;
    }

    public Issue createIssue(String txId, String type, String subject, String desc, String email) {
        String id = "I" + counter.getAndIncrement();
        Issue issue = new Issue(id, txId, type, subject, desc, email);
        issueRepo.save(issue);
        System.out.println(">>> Issue " + id + " created against transaction \"" + txId + "\"");
        return issue;
    }

    public void updateIssue(String id, IssueStatus status, String resolution) {
        issueRepo.findById(id).ifPresent(issue -> {
            issue.setStatus(status);
            issue.setResolution(resolution);
            System.out.println(">>> " + id + " status updated to " + status);
        });
    }

    public void resolveIssue(String id, AgentService agentService, String resolution) {

        issueRepo.findById(id).ifPresent(issue -> {
//            System.out.println("print " +issue.toString());
            if (issue.getAssignedAgentId() == null) {
                System.out.println(">>> Cannot resolve issue not assigned to any agent: " + id);
                return;
            }
            issue.setStatus(IssueStatus.RESOLVED);
            issue.setResolution(resolution);
            agentService.resolveIssue(issue);
            System.out.println(">>> " + id + " issue marked resolved");
        });
    }


    public List<Issue> getIssuesByEmail(String email) {
        return issueRepo.findByCustomerEmail(email);
    }

    public List<Issue> getIssuesByType(String type) {
        return issueRepo.findByType(type);
    }
}
