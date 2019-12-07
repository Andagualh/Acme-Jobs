
package acme.forms.dashboard;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	String[]					labels;
	String[]					numC;
	String[]					numI;
	Integer						totalAnnouncements;

	Integer						totalInvestor;

	Integer						totalCompany;

	Double						minimumRequest;

	Double						maximumRequest;

	Double						averageRequest;

	Double						desviationRequest;

	Double						minimumOffers;

	Double						maximumOffers;

	Double						averageOffers;

	Double						desviationOffers;

	//Lesson 04

	Double						averageJobsPerEmployer;

	Double						averageApplicationPerWorker;

}
