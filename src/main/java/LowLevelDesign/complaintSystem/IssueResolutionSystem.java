package LowLevelDesign.complaintSystem;

import LowLevelDesign.complaintSystem.entity.Issue;
import LowLevelDesign.complaintSystem.enums.IssueStatus;
import LowLevelDesign.complaintSystem.repositories.InMemoryAgentRepository;
import LowLevelDesign.complaintSystem.repositories.InMemoryIssueRepository;
import LowLevelDesign.complaintSystem.services.AgentService;
import LowLevelDesign.complaintSystem.services.IssueService;
import LowLevelDesign.complaintSystem.strategy.SimpleAssignmentStrategy;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
public class IssueResolutionSystem {
    public static void main(String[] args) {
        InMemoryIssueRepository issueRepo = new InMemoryIssueRepository();
        InMemoryAgentRepository agentRepo = new InMemoryAgentRepository();

        IssueService issueService = new IssueService(issueRepo);
        AgentService agentService = new AgentService(agentRepo);
        SimpleAssignmentStrategy assignmentStrategy = new SimpleAssignmentStrategy();

        Issue i1 = issueService.createIssue("T1", "Payment Related", "Payment Failed", "My payment failed", "user1@test.com");
        Issue i2 = issueService.createIssue("T2", "Mutual Fund Related", "Purchase Failed", "MF not purchased", "user2@test.com");
        Issue i3 = issueService.createIssue("T3", "Payment Related", "Payment Failed", "Payment stuck", "user2@test.com");
        Issue i4 = issueService.createIssue("T4", "Payment Related", "Payment Failed", "Payment stuck", "user2@test.com");
        Issue i5 = issueService.createIssue("T5", "Mutual Fund Related", "Purchase Failed", "stock not purchased", "user5@test.com");

        agentService.addAgent("agent1@test.com", "Agent 1", List.of("Payment Related", "Gold Related"));
        agentService.addAgent("agent2@test.com", "Agent 2", List.of("Mutual Fund Related"));

        agentService.assignIssue(i1, assignmentStrategy);
        agentService.assignIssue(i2, assignmentStrategy);
        agentService.assignIssue(i3, assignmentStrategy);
        agentService.assignIssue(i4, assignmentStrategy);
        agentService.assignIssue(i5, assignmentStrategy);

        issueService.updateIssue("I3", IssueStatus.IN_PROGRESS, "Waiting for confirmation");
        issueService.resolveIssue("I3", agentService, "Will be reversed soon");

        issueService.updateIssue("I4", IssueStatus.IN_PROGRESS, "Waiting for confirmation");
        issueService.resolveIssue("I4", agentService, "Will be reversed soon");

        issueService.updateIssue("I5", IssueStatus.IN_PROGRESS, "Waiting for confirmation");
        issueService.resolveIssue("I5", agentService, "Will be reversed soon");
        System.out.println("\n--- Agent Work History ---");
        agentService.viewWorkHistory();
    }
}
