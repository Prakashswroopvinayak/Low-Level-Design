package LowLevelDesign.complaintSystem.repositories;

import LowLevelDesign.complaintSystem.entity.Agent;
import LowLevelDesign.complaintSystem.repositories.repoInterfaces.AgentRepository;

import java.util.*;

public class InMemoryAgentRepository implements AgentRepository {
    private final Map<String, Agent> agents = new HashMap<>();

    public void save(Agent agent) {
        agents.put(agent.getId(), agent);
    }

    public List<Agent> findAll() {
        return new ArrayList<>(agents.values());
    }

    public Optional<Agent> findById(String id) {
        return Optional.ofNullable(agents.get(id));
    }
}
