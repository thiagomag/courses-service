package br.com.thiagomagdalena.coursesservice.persistance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity<ID> implements Entity<ID> {

    @JsonIgnore
    @CreatedDate
    protected LocalDateTime createdAt;
    @JsonIgnore
    @CreatedBy
    protected Long createdById;
    @JsonIgnore
    @LastModifiedDate
    protected LocalDateTime updatedAt;
    @JsonIgnore
    @LastModifiedBy
    protected Long updatedById;
    @JsonIgnore
    protected LocalDateTime deletedTmsp;

    public void delete() {
        setDeletedTmsp(LocalDateTime.now());
    }

}
