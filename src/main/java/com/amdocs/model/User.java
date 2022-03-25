/*
 * Entity  Class for User
 */

package com.amdocs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(description = "Details about User")
@Data
//@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
//@Document(collection = "user")
public class User {

	@Id
	/*
	 * @GeneratedValue(generator = "sequence-generator")
	 * 
	 * @GenericGenerator(name = "sequence-generator", strategy =
	 * "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
	 * 
	 * @Parameter(name = "sequence_name", value = "user_sequence"),
	 * 
	 * @Parameter(name = "initial_value", value = "10000"), @Parameter(name =
	 * "increment_size", value = "1") })
	 * 
	 * @Column(length = 5)
	 */
	private long userId;

	@NotNull
	@Size(min = 2, max = 40, message = "Name cant be  left empty.It Should have at least 2 characters")

	private String name;
	@NotNull
	@Size(max = 200, message = "Please write email in proper format")
	@Email
	private String emailId;
	@Column(length = 10)
	@NotNull
	@Size(min = 10, message = "Please write 10 digit mobile number")
	private String mobileNumber;
	@Size(min = 10, message = "Please write 10 digit secondary  mobile number")
	private String secondaryMobile;

	@NotNull
	/*
	 * @JsonFormat(pattern = "dd/MM/yyyy")
	 * 
	 * @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "dd/MM/yyyy")
	 */
	private String dateOfBirth;
	// @NotNull
	// @Size(min = 1 ,message= "Please write 10 digit secondary mobile number")

	private char gender;

	/*
	 * @Enumerated(EnumType.STRING) private Gender gender; public enum Gender { M, F
	 * }
	 */
//@JsonIgnore
//@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")

	private List<Account> accounts = new ArrayList<>();

}
