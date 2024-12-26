package com.example.demo1.entities;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "spots",
    indexes = {@Index(name = "spots_idx1_location_id_spot_name",
        columnList = "location_id, spot_name", unique = true)})
@Getter
@Setter
@ToString(callSuper = true)
public class Spot extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @Column(name = "spot_name", length = 100, nullable = false)
  private String spotName;

  @Column(length = 30, nullable = false)
  @Enumerated(EnumType.STRING)
  private SpotType type;

  @Enumerated(EnumType.STRING)
  @Column(length = 30, nullable = false)
  private Status status;

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;

  @ManyToOne
  @JoinColumn(name = "fleet_id")
  private Fleet fleet;

  @Column
  @Lob
  private String remarks;
  
  private ZonedDateTime lastOccupiedTime;
  
  private ZonedDateTime lastEmptiedTime;
  
  private Boolean isOccupied;

  public enum SpotType {
    DOCK, SPOT, CURB;
  }

  public enum Status {
    EMPTY, OCCUPIED, TO_BE_EMPTY, TO_BE_OCCUPIED;
  }
  
//  private String locationName;

}

