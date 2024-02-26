package com.sunbackend.Helper;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketAssign {
    private Long id; //ticket id
    private Long assigneeId; // assign id whom ticket to assign

    public TicketAssign(Long id, Long assigneeId) {
        this.id=id;
        this.assigneeId=assigneeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
}
