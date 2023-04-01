package com.prudentgaming.betting.model;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/** 
	* This class is added to ensure uniformity of response object across all the REST Api responses
	* Status represent the HTTPStatus
	* We can add further fields to represent business level messages or to add data in the response
*/
public class RestApiResponse {

	private int status;
	private String message;
}