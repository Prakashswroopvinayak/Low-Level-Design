package LowLevelDesign.complaintSystem.entity;

import LowLevelDesign.complaintSystem.enums.IssueStatus;

public class Issue {
    private final String id;
    private final String transactionId;
    private final String type;
    private final String subject;
    private final String description;
    private final String customerEmail;

    private IssueStatus status;
    private String resolution;
    private String assignedAgentId;

    public Issue(String id, String transactionId, String type, String subject, String description, String customerEmail) {
        this.id = id;
        this.transactionId = transactionId;
        this.type = type;
        this.subject = subject;
        this.description = description;
        this.customerEmail = customerEmail;
        this.status = IssueStatus.OPEN;
    }

    // Getters and Setters

    public String getId() { return id; }
    public String getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public String getSubject() { return subject; }
    public String getDescription() { return description; }
    public String getCustomerEmail() { return customerEmail; }
    public IssueStatus getStatus() { return status; }
    public void setStatus(IssueStatus status) { this.status = status; }
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
    public String getAssignedAgentId() { return assignedAgentId; }
    public void setAssignedAgentId(String assignedAgentId) { this.assignedAgentId = assignedAgentId; }

    @Override
    public String toString() {
        return id + " {\"" + transactionId + "\", \"" + type + "\", \"" + subject + "\", \"" + description + "\", \"" + customerEmail + "\", \"" + status + "\"}";
    }
}
