package LowLevelDesign.complaintSystem.strategy;

import LowLevelDesign.complaintSystem.entity.Agent;

import java.util.List;
import java.util.Optional;

public class SimpleAssignmentStrategy implements AssignmentStrategy {
    public Optional<Agent> findAvailableAgent(String issueType, List<Agent> agents) {
        return agents.stream()
                .filter(a -> a.isFree() && a.canHandle(issueType))
                .findFirst();
    }
}