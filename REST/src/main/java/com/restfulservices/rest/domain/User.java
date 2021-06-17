package com.restfulservices.rest.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "All details concerning user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2)
    @ApiModelProperty(notes = "name should have at least 2 chars")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birthdate cannot be in the past")
    private Date birthDate;

    @OneToMany(mappedBy = "user") //creates relationship column of user IN Post!!
    private List<Post> posts;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public List<Post> getPosts() {
        return posts;
    }
}
