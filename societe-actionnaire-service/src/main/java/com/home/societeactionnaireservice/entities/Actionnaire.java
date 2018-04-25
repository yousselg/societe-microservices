/**
 * 
 */
package com.home.societeactionnaireservice.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Home
 *
 */

@Entity
@Data
public class Actionnaire {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private List<Long> societes;

	public Actionnaire() {
		super();
	}

}
