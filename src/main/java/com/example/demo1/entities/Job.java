package com.example.demo1.entities;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.example.demo1.entities.Job.FleetStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@ToString(callSuper = true)
public class Job extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "assigned_to_user_id")
  private User assignedTo;

  @ManyToOne
  @JoinColumn(name = "fleet_id")
  private Fleet fleet;

  @Column
  @Enumerated(EnumType.STRING)
  private FleetStatus fleetStatus;

  @Column
  @Lob
  private String description;

  // pickup

  @ManyToOne
  @JoinColumn(name = "pickup_location_id", nullable = false)
  private Location pickupLocation;

  @ManyToOne
  @JoinColumn(name = "pickup_spot_id", nullable = false)
  private Spot pickupSpot;

  @Column
  private ZonedDateTime pickupDateTime;

  @Column
  @Lob
  private String pickupNotes;


  // drop

  @ManyToOne
  @JoinColumn(name = "drop_location_id")
  private Location dropLocation;

  @ManyToOne
  @JoinColumn(name = "drop_spot_id")
  private Spot dropSpot;

  @Column
  private ZonedDateTime dropDateTime;

  @Column
  @Lob
  private String dropNotes;


  @Column(length = 30, nullable = false)
  @Enumerated(EnumType.STRING)
  private Priority priority;

  @Enumerated(EnumType.STRING)
  @Column(length = 30, nullable = false)
  private Status status;
  
  @Column
  private String sequenceAsn;

  public enum Priority {
    HIGH, MEDIUM, LOW;
  }

  public enum Status {
   QUEUE, OPEN, IN_TRANSIT, COMPLETED;
  }

  public enum FleetStatus {
    EMPTY, FULL;
  }
  
  public enum Bucket {
	  BUCKET_DRIVER,BUCKET_SPOTTER,NIL;
  }
  
  private Boolean unsignedBol;
  
  private Boolean signedBol;
  
  @OneToMany(mappedBy = "job",fetch = FetchType.LAZY)
  private Set<Bol> bols = new HashSet<>();
  
  
  private Double temperature;
  
  private String climate;
  
  private Long queuePosition;
  
  @Enumerated(EnumType.STRING)
  @Column(length = 30)
  private Bucket bucket;

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