package com.example.demo1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "locations",
    indexes = {@Index(name = "locations_idx1_client_id_location_name",
        columnList = "client_id, location_name", unique = true)})
@Getter
@Setter
@ToString(callSuper = true)
public class Location extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "location_name", length = 100, nullable = false)
  private String locationName;

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

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;

  @Column
  @Lob
  private String locationMapJson;

  @Column
  @Lob
  private String remarks;

  @Column(name = "is_default")
  private Boolean isDefault;

  @Column
  private String mapImagePath;
  
  @Column
  private String pieChartColor;

}