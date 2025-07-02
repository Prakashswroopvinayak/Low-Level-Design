package LowLevelDesign.complaintSystem.repositories.repoInterfaces;

import LowLevelDesign.complaintSystem.entity.Agent;

import java.util.List;
import java.util.Optional;

public interface AgentRepository {
    void save(Agent agent);
    List<Agent> findAll();
    Optional<Agent> findById(String id);
}