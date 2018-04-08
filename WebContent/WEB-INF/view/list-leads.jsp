<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
		<title>List Leads</title>
		<!-- Reference our style sheet -->
		<link type="text/css"
				rel="stylesheet"
				href="${pageContext.request.contextPath}/resources/css/style.css" />
	</head>
	
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Management</h2>
			</div>
		</div>
	
		<div id="container">
			<div id="content">
				<!-- Put new button: Add Employee -->
				<input type="button" value="Add New Lead"
						onclick="window.location.href='showFormForAdd'; return false;"
						class="add-button" />
			
				<table>
					<tr>
						<th>Lead ID</th>
						<th>Lead Status</th>
						<th>Customer First Name</th>
					</tr>
					<!-- Loop over and print our customers -->
					<c:forEach var="tempLead" items="${theLeads}">
					
						<!-- Construct an update link with employee id -->
						<c:url var="updateLink" value="showFormForUpdate">
							<c:param name="leadId" value="${tempLead.leadId}"/>
						</c:url>
						
						<!-- Construct an delete link with customer id -->
						<c:url var="deleteLink" value="delete">
							<c:param name="leadId" value="${tempLead.leadId}"/>
						</c:url>
						
						<tr>
							<td> ${tempLead.leadId} </td>
							<td> ${tempLead.leadStatus} </td>
							<td> ${tempLead.custFname} </td>
							<td>
								<!-- Display the update link -->
								<a href="${updateLink}">Update</a>
								|
								<a href="${deleteLink}"
									onclick="if(!(confirm('Are you sure you want to delete this employee?'))) return false">Delete</a>
							</td>
						</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>
	
	</body>

</html>