package com.prudentgaming.betting.model;

import lombok.Data;
import java.util.Date;

@Data 
/** 
	* This class represent the structure of the Json Request for the POST request to save the data.
	* If the Json Structure is not as per this class, then it will result into exception
	*
*/
public class SummaryRequest {
	private String[] gameNames;
	private Integer[] clientIds;
	private Date startDate;
	private Date endDate;
	private Integer limit;
	private Integer offset;
}