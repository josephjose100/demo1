package com.example.demo1.entities;

import static jakarta.persistence.GenerationType.IDENTITY;
import java.time.ZonedDateTime;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(exclude = {"createdBy", "lastModifiedBy"})
@EqualsAndHashCode(
    exclude = {"isActive", "createdDate", "lastModifiedDate", "createdBy", "lastModifiedBy"})
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "uuid", unique = true, nullable = false)
  private String uuid = UUID.randomUUID().toString();

  @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
  private Boolean isActive = true;

  // Audit Columns
  @CreatedDate
  private ZonedDateTime createdDate;

  @LastModifiedDate
  private ZonedDateTime lastModifiedDate;

  @CreatedBy
  @ManyToOne
  @JoinColumn(name = "created_by")
  private User createdBy;

  @LastModifiedBy
  @ManyToOne
  @JoinColumn(name = "last_modified_by")
  private User lastModifiedBy;

}
