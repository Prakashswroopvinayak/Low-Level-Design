package LowLevelDesign.complaintSystem.entity;

import java.util.*;


/*
*    Id Long
*    varchar email
*    Varchar name
*
*
*
* Expertise
*   id
*   AgentId
*   expertiseType
*
*
* waitingQueue
*  Id,
*   IssueId
*   AgentId
*   priority
* */
public class Agent {
    private final String id;
    private final String email;
    private final String name;
    private final Set<String> expertise;
    private final List<Issue> workHistory = new ArrayList<>();
    private final Queue<Issue> waitingQueue = new LinkedList<>();
    private Issue currentIssue;

    public Agent(String id, String email, String name, List<String> issueTypes) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.expertise = new HashSet<>(issueTypes);
    }

    public boolean isFree() { return currentIssue == null; }
    public boolean canHandle(String issueType) { return expertise.contains(issueType); }

    public void assignIssue(Issue issue) {
        this.currentIssue = issue;
        if (!workHistory.contains(issue)) {
            this.workHistory.add(issue);
        }
        issue.setAssignedAgentId(this.id);
    }

    public void resolveCurrentIssue() {
        this.currentIssue = null;
    }

    public Queue<Issue> getWaitingQueue() { return waitingQueue; }
    public Issue getCurrentIssue() { return currentIssue; }
    public List<Issue> getWorkHistory() { return workHistory; }
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public Set<String> getExpertise() { return expertise; }

    @Override
    public String toString() {
        return id + " (" + name + ")";
    }
}
