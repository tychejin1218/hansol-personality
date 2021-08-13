package com.eduhansol.app.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private Admin admin;

    @ManyToOne
    @JoinColumn
    private NormPersonality normPersonality;

    @Column(nullable = false)
    @NotBlank(message = "채용 그룹명을 입력하세요.")
    private String name;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    @CreatedDate
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd'HH:mm")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime lastModifiedDateTime;

    @Column(nullable = false)
    private boolean isDelete;

    public String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

    public int getId() {
        return id;
    }

    public NormPersonality getNormPersonality() {
        return normPersonality;
    }

    public void setNormPersonality(NormPersonality normPersonality) {
        this.normPersonality = normPersonality;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
}
