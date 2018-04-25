/**
 * 
 */
package com.home.societeservice.entities;

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
public class Societe {

	@Id
	@GeneratedValue
	private Long id;
	private String rc;

	public Societe(String rc) {
		super();
		this.rc = rc;
	}

	public Societe() {
		super();
	}

}
