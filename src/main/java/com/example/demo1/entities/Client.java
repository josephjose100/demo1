package com.example.demo1.entities;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clients",
    indexes = {
        @Index(name = "clients_idx1_client_name", columnList = "client_name", unique = true)})
@Getter
@Setter
@ToString(callSuper = true)
public class Client extends BaseEntity {

  @Column(name = "client_name", length = 100, nullable = false)
  private String clientName;

  @Column(length = 200, nullable = false)
  private String street;

  @Column(length = 50, nullable = false)
  private String city;

  @Column(length = 2, nullable = false)
  private String state;

  @Column(length = 15, nullable = false)
  private String zip;

  @Column(length = 3, nullable = false)
  private String country;

  @Column(length = 100, nullable = false)
  private String contactPerson;

  @Column(length = 100, nullable = false)
  private String contactEmail;

  @Column(length = 20)
  private String contactPhone;

  @Column
  @Lob
  private String remarks;

  @ManyToMany(mappedBy = "clients")
  private Set<User> users;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "clients__fleets", joinColumns = @JoinColumn(name = "client_id"),
      inverseJoinColumns = @JoinColumn(name = "fleet_id"))
  private Set<Fleet> fleets = new HashSet<>();
 
  private Boolean bol;
  
  private Boolean dvir;
  
  private Boolean accountDeactivation;
  
  @Column
  private String timeZone;
  
  private Boolean trailerAudit;
  
  private float overTime;
  
  private Boolean bucketSystem;
}
