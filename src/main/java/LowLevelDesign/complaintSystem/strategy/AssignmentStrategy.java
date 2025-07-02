package LowLevelDesign.complaintSystem.strategy;

import LowLevelDesign.complaintSystem.entity.Agent;

import java.util.List;
import java.util.Optional;

public interface AssignmentStrategy {
    Optional<Agent> findAvailableAgent(String issueType, List<Agent> agents);
}