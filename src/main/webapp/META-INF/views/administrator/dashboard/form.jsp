<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:message code="administrator.dashboard.form.title.company" />

<acme:form-integer code="administrator.dashboard.form.totalAnnounencents" path="totalAnnouncements"/>
<acme:form-integer code="administrator.dashboard.form.totalInvestor" path="totalInvestor"/>
<acme:form-integer code="administrator.dashboard.form.totalCompany" path="totalCompany"/>
<acme:form-double code="administrator.dashboard.form.minimumRequest" path="minimumRequest"/>
<acme:form-double code="administrator.dashboard.form.maximumRequest" path="maximumRequest"/>
<acme:form-double code="administrator.dashboard.form.averageRequest" path="averageRequest"/>
<acme:form-double code="administrator.dashboard.form.desviationRequest" path="desviationRequest"/>
<acme:form-double code="administrator.dashboard.form.minimumOffers" path="minimumOffers"/>
<acme:form-double code="administrator.dashboard.form.maximumOffers" path="maximumOffers"/>
<acme:form-double code="administrator.dashboard.form.averageOffers" path="averageOffers"/>
<acme:form-double code="administrator.dashboard.form.desviationOffers" path="desviationOffers"/>
<acme:form-double code="administrator.dashboard.form.averageJobsPerEmployer" path="averageJobsPerEmployer"/>
<acme:form-double code="administrator.dashboard.form.averageApplicationPerWorker" path="averageApplicationPerWorker"/>
<acme:form-double code="administrator.dashboard.form.averageNumberOfApplicationPerEmployer"
	path="averageNumberOfApplicationPerEmployer" />

<div>
	<canvas id="canvas1"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					<jstl:forEach var="items" items="${labels}">
					<jstl:out value="\"${items}\"" escapeXml="false"/>,
					</jstl:forEach>
				],
				datasets : [
					{
						label: "Company Records",
						data : [
							<jstl:forEach var="numeC" items="${numC}">
							<jstl:out value="\"${numeC}\"" escapeXml="false"/>,
							</jstl:forEach>
						],backgroundColor: [
							<jstl:forEach var="numeC" items="${numC}">
							<jstl:out value="'#FF6384'" escapeXml="false"/>,
							</jstl:forEach>
			            ]	
						
					},{
						label: "Investor Records",
						data : [
							<jstl:forEach var="numeI" items="${numI}">
							<jstl:out value="\"${numeI}\"" escapeXml="false"/>,
							</jstl:forEach>
						],backgroundColor: [
							<jstl:forEach var="numeI" items="${numI}">
							<jstl:out value="'#8463FF'" escapeXml="false"/>,
							</jstl:forEach>
			            ]		
					}
				]		
		};
		
		var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 10.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvas1");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
	
<acme:message code="administrator.dashboard.form.title.applications" />

<div>
	<canvas id="canvas2"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					"PENDING", "ACCEPTED", "REJECTED"
				],
				datasets : [
					{
						label: "Aplications",
						data : [
							<jstl:out value = "${ratioOfPendingApplications}" />,
							<jstl:out value = "${ratioOfAcceptedApplications}" />,
							<jstl:out value = "${ratioOfRejectedApplications}" />
						],backgroundColor: [
							"#7FFF00", "#7FFF00", "#7FFF00"
			            ]	
						
					}
				]		
		};
		
		var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 10.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvas2");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:message code="administrator.dashboard.form.title.jobs" />

<div>
	<canvas id="canvas3"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					"DRAFT", "PUBLISHED"
				],
				datasets : [
					{
						label: "Jobs",
						data : [
							<jstl:out value = "${ratioOfDraftJobs}" />,
							<jstl:out value = "${ratioOfPublishedJobs}" />
						],backgroundColor: [
							"#9932CC", "#9932CC"
			            ]	
						
					}
				]		
		};
		
		var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 10.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvas3");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:message code="administrator.dashboard.form.title.applications4LastWeeks" />

<div>
	<canvas id="canvas4"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: [
					"PENDING", "ACCEPTED", "REJECTED"
				],
				datasets : [
					{
						label: "Aplications",
						data : [
							<jstl:out value = "${ratioOfPendingApplicationsInLast4Weeks}" />,
							<jstl:out value = "${ratioOfAcceptedApplicationsInLast4Weeks}" />,
							<jstl:out value = "${ratioOfRejectedApplicationsInLast4Weeks}" />
						],backgroundColor: [
							"#7FFF00", "#7FFF00", "#7FFF00"
			            ]	
						
					}
				]		
		};
		
		var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 10.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvas4");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
	
	
</script>


<script type="text/javascript">
window.onload = function () {
	var chart1 = new CanvasJS.Chart("chartPendingPerDay", {
		title: {
			text: "Pending applications per day"
		},
		axisX:{
			stripLines:[
			{                
				value:28
			}
			],
			valueFormatString:"####"
		},
		data: [
		{
			type: "splineArea",            
			color: "rgba(83, 223, 128, .6)",
			dataPoints: [
				{ x: 0, y: <jstl:out value =  "${dayPending.get(0)}"/> }, 
				{ x: 1, y: <jstl:out value =  "${dayPending.get(1)}"/> },
				{ x: 2, y: <jstl:out value =  "${dayPending.get(2)}"/> }, 
				{ x: 3, y: <jstl:out value =  "${dayPending.get(3)}"/> },
				{ x: 4, y: <jstl:out value =  "${dayPending.get(4)}"/> }, 
				{ x: 5, y: <jstl:out value =  "${dayPending.get(5)}"/> },
				{ x: 6, y: <jstl:out value =  "${dayPending.get(6)}"/> }, 
				{ x: 7, y: <jstl:out value =  "${dayPending.get(7)}"/> },
				{ x: 8, y: <jstl:out value =  "${dayPending.get(8)}"/> }, 
				{ x: 9, y: <jstl:out value =  "${dayPending.get(9)}"/> },
				{ x: 10, y: <jstl:out value =  "${dayPending.get(10)}"/> }, 
				{ x: 11, y: <jstl:out value =  "${dayPending.get(11)}"/> },
				{ x: 12, y: <jstl:out value =  "${dayPending.get(12)}"/> }, 
				{ x: 13, y: <jstl:out value =  "${dayPending.get(13)}"/> },
				{ x: 14, y: <jstl:out value =  "${dayPending.get(14)}"/> }, 
				{ x: 15, y: <jstl:out value =  "${dayPending.get(15)}"/> },
				{ x: 16, y: <jstl:out value =  "${dayPending.get(16)}"/> }, 
				{ x: 17, y: <jstl:out value =  "${dayPending.get(17)}"/> },
				{ x: 18, y: <jstl:out value =  "${dayPending.get(18)}"/> }, 
				{ x: 19, y: <jstl:out value =  "${dayPending.get(19)}"/> },
				{ x: 20, y: <jstl:out value =  "${dayPending.get(20)}"/> }, 
				{ x: 21, y: <jstl:out value =  "${dayPending.get(21)}"/> },
				{ x: 22, y: <jstl:out value =  "${dayPending.get(22)}"/> }, 
				{ x: 23, y: <jstl:out value =  "${dayPending.get(23)}"/> },
				{ x: 24, y: <jstl:out value =  "${dayPending.get(24)}"/> }, 
				{ x: 25, y: <jstl:out value =  "${dayPending.get(25)}"/> },
				{ x: 26, y: <jstl:out value =  "${dayPending.get(26)}"/> }, 
				{ x: 27, y: <jstl:out value =  "${dayPending.get(27)}"/> },
				{ x: 28, y: <jstl:out value =  "${dayPending.get(28)}"/> }, 
			]
		}
		]        
	});
	chart1.render();
	
	var chart2 = new CanvasJS.Chart("chartAcceptedPerDay", {
		title: {
			text: "Accepted applications per day"
		},
		axisX:{
			stripLines:[
			{                
				value:28
			}
			],
			valueFormatString:"####"
		},
		data: [
		{
			type: "splineArea",            
			color: "rgba(83, 223, 128, .6)",
			dataPoints: [
				{ x: 0, y: <jstl:out value =  "${dayAccepted.get(0)}"/> }, 
				{ x: 1, y: <jstl:out value =  "${dayAccepted.get(1)}"/> },
				{ x: 2, y: <jstl:out value =  "${dayAccepted.get(2)}"/> }, 
				{ x: 3, y: <jstl:out value =  "${dayAccepted.get(3)}"/> },
				{ x: 4, y: <jstl:out value =  "${dayAccepted.get(4)}"/> }, 
				{ x: 5, y: <jstl:out value =  "${dayAccepted.get(5)}"/> },
				{ x: 6, y: <jstl:out value =  "${dayAccepted.get(6)}"/> }, 
				{ x: 7, y: <jstl:out value =  "${dayAccepted.get(7)}"/> },
				{ x: 8, y: <jstl:out value =  "${dayAccepted.get(8)}"/> }, 
				{ x: 9, y: <jstl:out value =  "${dayAccepted.get(9)}"/> },
				{ x: 10, y: <jstl:out value =  "${dayAccepted.get(10)}"/> }, 
				{ x: 11, y: <jstl:out value =  "${dayAccepted.get(11)}"/> },
				{ x: 12, y: <jstl:out value =  "${dayAccepted.get(12)}"/> }, 
				{ x: 13, y: <jstl:out value =  "${dayAccepted.get(13)}"/> },
				{ x: 14, y: <jstl:out value =  "${dayAccepted.get(14)}"/> }, 
				{ x: 15, y: <jstl:out value =  "${dayAccepted.get(15)}"/> },
				{ x: 16, y: <jstl:out value =  "${dayAccepted.get(16)}"/> }, 
				{ x: 17, y: <jstl:out value =  "${dayAccepted.get(17)}"/> },
				{ x: 18, y: <jstl:out value =  "${dayAccepted.get(18)}"/> }, 
				{ x: 19, y: <jstl:out value =  "${dayAccepted.get(19)}"/> },
				{ x: 20, y: <jstl:out value =  "${dayAccepted.get(20)}"/> }, 
				{ x: 21, y: <jstl:out value =  "${dayAccepted.get(21)}"/> },
				{ x: 22, y: <jstl:out value =  "${dayAccepted.get(22)}"/> }, 
				{ x: 23, y: <jstl:out value =  "${dayAccepted.get(23)}"/> },
				{ x: 24, y: <jstl:out value =  "${dayAccepted.get(24)}"/> }, 
				{ x: 25, y: <jstl:out value =  "${dayAccepted.get(25)}"/> },
				{ x: 26, y: <jstl:out value =  "${dayAccepted.get(26)}"/> }, 
				{ x: 27, y: <jstl:out value =  "${dayAccepted.get(27)}"/> },
				{ x: 28, y: <jstl:out value =  "${dayAccepted.get(28)}"/> }, 
			]
		}
		]        
	});
	chart2.render();
	
	var chart3 = new CanvasJS.Chart("chartRejectedPerDay", {
		title: {
			text: "Rejected applications per day"
		},
		axisX:{
			stripLines:[
			{                
				value:28
			}
			],
			valueFormatString:"####"
		},
		data: [
		{
			type: "splineArea",            
			color: "rgba(83, 223, 128, .6)",
			dataPoints: [
				{ x: 0, y: <jstl:out value =  "${dayRejected.get(0)}"/> }, 
				{ x: 1, y: <jstl:out value =  "${dayRejected.get(1)}"/> },
				{ x: 2, y: <jstl:out value =  "${dayRejected.get(2)}"/> }, 
				{ x: 3, y: <jstl:out value =  "${dayRejected.get(3)}"/> },
				{ x: 4, y: <jstl:out value =  "${dayRejected.get(4)}"/> }, 
				{ x: 5, y: <jstl:out value =  "${dayRejected.get(5)}"/> },
				{ x: 6, y: <jstl:out value =  "${dayRejected.get(6)}"/> }, 
				{ x: 7, y: <jstl:out value =  "${dayRejected.get(7)}"/> },
				{ x: 8, y: <jstl:out value =  "${dayRejected.get(8)}"/> }, 
				{ x: 9, y: <jstl:out value =  "${dayRejected.get(9)}"/> },
				{ x: 10, y: <jstl:out value =  "${dayRejected.get(10)}"/> }, 
				{ x: 11, y: <jstl:out value =  "${dayRejected.get(11)}"/> },
				{ x: 12, y: <jstl:out value =  "${dayRejected.get(12)}"/> }, 
				{ x: 13, y: <jstl:out value =  "${dayRejected.get(13)}"/> },
				{ x: 14, y: <jstl:out value =  "${dayRejected.get(14)}"/> }, 
				{ x: 15, y: <jstl:out value =  "${dayRejected.get(15)}"/> },
				{ x: 16, y: <jstl:out value =  "${dayRejected.get(16)}"/> }, 
				{ x: 17, y: <jstl:out value =  "${dayRejected.get(17)}"/> },
				{ x: 18, y: <jstl:out value =  "${dayRejected.get(18)}"/> }, 
				{ x: 19, y: <jstl:out value =  "${dayRejected.get(19)}"/> },
				{ x: 20, y: <jstl:out value =  "${dayRejected.get(20)}"/> }, 
				{ x: 21, y: <jstl:out value =  "${dayRejected.get(21)}"/> },
				{ x: 22, y: <jstl:out value =  "${dayRejected.get(22)}"/> }, 
				{ x: 23, y: <jstl:out value =  "${dayRejected.get(23)}"/> },
				{ x: 24, y: <jstl:out value =  "${dayRejected.get(24)}"/> }, 
				{ x: 25, y: <jstl:out value =  "${dayRejected.get(25)}"/> },
				{ x: 26, y: <jstl:out value =  "${dayRejected.get(26)}"/> }, 
				{ x: 27, y: <jstl:out value =  "${dayRejected.get(27)}"/> },
				{ x: 28, y: <jstl:out value =  "${dayRejected.get(28)}"/> }, 
			]
		}
		]        
	});
	chart3.render();
  }
</script>


<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<div id="chartPendingPerDay" style="height: 300px; width: 100%;"></div>
<div id="chartAcceptedPerDay" style="height: 300px; width: 100%;"></div>
<div id="chartRejectedPerDay" style="height: 300px; width: 100%;"></div>
