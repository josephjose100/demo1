package com.example.demo1.entities;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinColumn;

//import lombok.Data;
//
//import jakarta.persistence.*;
//import java.util.Set;
//
//@Data
//@Entity
//@Table(name = "users", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"username"}),
//        @UniqueConstraint(columnNames = {"email"})
//})
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String name;
//    private String username;
//    private String email;
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;
//}

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users",
	indexes = {@Index(name = "users_idx1_email", columnList = "email", unique = true)})
@Getter
@Setter
@ToString(callSuper = true, exclude = {"password", "roles", "tempPassword"})
public class User extends BaseEntity {

	@Column(length = 100, nullable = false)
	private String firstName;

	@Column(length = 100, nullable = false)
	private String lastName;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 15)
	private String phone;

	@Column(length = 200)
	private String password;

	@Column(columnDefinition = "varchar(30) default 'US/Eastern'")
	private String timeZone;

	@Column
	private String forgotPasswordToken;

	@Column
	private ZonedDateTime forgotPasswordLinkInitialTime;

	@Column
	private ZonedDateTime lastLoginTime;

	@Transient
	private String tempPassword;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users__roles", joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "users__clients", joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "client_id"))
	private Set<Client> clients = new HashSet<>();

//	@OneToMany(mappedBy = "user")
//	private Set<UserDevice> devices = new HashSet<>();

	@Column
	private ZonedDateTime lastActiveTime;

	@Column(name = "is_ad_user", nullable = false, columnDefinition = "boolean default false")
	private Boolean isADUser = false;
	
	private ZonedDateTime idleSince;
	
	private Boolean isOnOverTime;
	
	private Boolean isIdleClear;

}
