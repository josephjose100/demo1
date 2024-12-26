package com.example.demo1.entities;

//import lombok.Getter;
//import lombok.Setter;
//
//import jakarta.persistence.*;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "roles")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(length = 60)
//    private String name;
//}


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles",
    uniqueConstraints = @UniqueConstraint(name = "roles_uk1", columnNames = "role_name"))
@Getter
@Setter
@ToString(callSuper = true, exclude = "users")
public class Role extends BaseEntity {

  @Column(name = "role_name", length = 50, nullable = false)
  private String roleName;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

}