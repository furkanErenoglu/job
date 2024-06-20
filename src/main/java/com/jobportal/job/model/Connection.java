package com.jobportal.job.model;

import com.jobportal.job.enums.ConnectionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ConnectionStatus status;
    @Column(name = "request_date")
    private Timestamp requestDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.archisacademy.jobportal.model.User user;
    @ManyToOne
    @JoinColumn(name = "requested_user_id")
    private com.archisacademy.jobportal.model.User requestedUser;

}