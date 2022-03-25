/*
 * Entity  Class for Account
 */
package com.amdocs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ApiModel(description = "Details about Account")
@Data
//@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "accountId")
@Document(collection = "account")
public class Account {

	
	/*
	 *  @GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * @Id
	 * 
	 * @GeneratedValue(generator = "sequence-generator")
	 * 
	 * @GenericGenerator(name = "sequence-generator", strategy =
	 * "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
	 * 
	 * @Parameter(name = "sequence_name", value = "account_sequence"),
	 * 
	 * @Parameter(name = "initial_value", value = "1000000000"),
	 * 
	 * @Parameter(name = "increment_size", value = "1") })
	 * 
	 * @Column(length = 10)
	 */
	@Id
	private long accountId;

	@NotNull
	@Size(min = 3, max = 40, message = "Branch name Should have at least 3 characters")

	private String branchName;

	@NotNull
	@Size(min = 4, max = 40, message = "Savings-S | Current-C")
	private String accountType;

	@NotNull

	@Min(0)
	private double accountBalance;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JsonBackReference

	private User user;

	public Account() {

	}

}
