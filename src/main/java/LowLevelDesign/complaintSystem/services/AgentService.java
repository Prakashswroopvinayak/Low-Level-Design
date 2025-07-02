package LowLevelDesign.complaintSystem.services;

import LowLevelDesign.complaintSystem.entity.Agent;
import LowLevelDesign.complaintSystem.entity.Issue;
import LowLevelDesign.complaintSystem.repositories.repoInterfaces.AgentRepository;
import LowLevelDesign.complaintSystem.strategy.AssignmentStrategy;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AgentService {
    private final AgentRepository agentRepo;
    private final AtomicInteger counter = new AtomicInteger(1);

    public AgentService(AgentRepository repo) {
        this.agentRepo = repo;
    }

    public Agent addAgent(String email, String name, List<String> types) {
        String id = "A" + counter.getAndIncrement();
        Agent agent = new Agent(id, email, name, types);
        agentRepo.save(agent);
        System.out.println(">>> Agent " + id + " created");
        return agent;
    }

    public void assignIssue(Issue issue, AssignmentStrategy strategy) {
        List<Agent> agents = agentRepo.findAll();
        Optional<Agent> availableAgent = strategy.findAvailableAgent(issue.getType(), agents);

        if (availableAgent.isPresent()) {
            Agent agent = availableAgent.get();
            agent.assignIssue(issue);
            System.out.println(">>> Issue " + issue.getId() + " assigned to agent " + agent.getId());
        } else {
            agents.stream().filter(a -> a.canHandle(issue.getType())).findFirst().ifPresent(agent -> {
                issue.setAssignedAgentId(agent.getId());
                agent.getWaitingQueue().add(issue);

                System.out.println(">>> Issue " + issue.getId() + " added to waitlist of Agent " + agent.getId());
            });
        }
    }

    public void resolveIssue(Issue issue) {
        agentRepo.findById(issue.getAssignedAgentId()).ifPresent(agent -> {
            agent.resolveCurrentIssue();
            if (!agent.getWaitingQueue().isEmpty()) {
                Issue next = agent.getWaitingQueue().poll();
                agent.assignIssue(next); // ✅ this assigns and adds to workHistory
                System.out.println(">>> Issue " + next.getId() + " assigned to agent " + agent.getId() + " from waitlist");
            }
        });
    }


    public void viewWorkHistory() {
        agentRepo.findAll().forEach(agent -> {
            System.out.print(agent.getId() + " -> {");
            List<Issue> work = agent.getWorkHistory();
            for (int i = 0; i < work.size(); i++) {
                System.out.print(work.get(i).getId());
                if (i < work.size() - 1) System.out.print(", ");
            }
            System.out.println("}");
        });
    }
}
