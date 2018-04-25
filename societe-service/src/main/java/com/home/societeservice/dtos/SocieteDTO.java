/**
 * 
 */
package com.home.societeservice.dtos;

import java.util.List;

/**
 * @author Home
 *
 */

public class SocieteDTO {

	private Long id;
	private String rc;
	private List<Long> actionnaires;

	public SocieteDTO(String rc) {
		super();
		this.rc = rc;
	}

	public SocieteDTO() {
		super();
	}

}
