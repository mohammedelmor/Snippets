package org.mohammed.snippets.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(schema = "snippets_db")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "snippet_seq")
    @SequenceGenerator(name = "snippet_seq", sequenceName = "snippets_db.snippet_seq", allocationSize = 1)
    private Long id;


    @Column(nullable = false)
    @NotNull
    private String title;

    @Lob
    @Column(nullable = false)
    @NotNull
    private String code;

    @Column
    @NotNull
    private String language;



    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}


