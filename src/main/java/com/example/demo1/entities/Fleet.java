package com.example.demo1.entities;

import java.util.HashSet;
import java.util.Set;

//import javax.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.example.demo1.entities.Job.FleetStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "fleets",
    indexes = {@Index(name = "fleets_idx1_unit_number", columnList = "unit_number", unique = true)})
@Getter
@Setter
@ToString(callSuper = true)
public class Fleet extends BaseEntity {

  @Column(length = 100)
  private String carrier;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Type type;

  @Column(name = "unit_number", length = 30, nullable = false)
  private String unitNumber;

  @Column
  @Lob
  private String remarks;

  @Column
  private String owner;

  @Column(name = "is_hot_trailer")
  private Boolean isHotTrailer;

  @Enumerated(EnumType.STRING)
  @Column
  private FleetStatus fleetStatus;

  @ManyToOne
  @JoinColumn(name = "spot_id")
  private Spot spot;

  @ManyToMany(mappedBy = "fleets")
  private Set<Client> clients = new HashSet<>();
  
//  @ManyToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "carrier_id")
//  private Carriers carriers;

  public enum Type {
    TRUCK, TRAILER, CONTAINER;
  }
  
//  public void setFleetStatus(FleetStatus fleetStatus) {
//	    
//	    // If the status is "FULL", treat it as "LOADED"
//	    if (fleetStatus == FleetStatus.FULL) {
//	      this.fleetStatus = FleetStatus.LOADED;
//	    } else {
//	      this.fleetStatus = fleetStatus;
//	    }
//	  }

	 
  public FleetStatus getFleetStatus() {
	 return fleetStatus;
  }

}
