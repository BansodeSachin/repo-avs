<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
		<title>List Customers</title>
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
				<input type="button" value="Add Employee"
						onclick="window.location.href='showFormForAdd'; return false;"
						class="add-button" />
			
				<!-- Add our html table here -->
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
					<!-- Loop over and print our customers -->
					<c:forEach var="tempEmployee" items="${employees}">
					
						<!-- Construct an update link with employee id -->
						<c:url var="updateLink" value="showFormForUpdate">
							<c:param name="employeeId" value="${tempEmployee.id}"/>
						</c:url>
						
						<!-- Construct an delete link with customer id -->
						<c:url var="deleteLink" value="delete">
							<c:param name="employeeId" value="${tempEmployee.id}"/>
						</c:url>
						
						<tr>
							<td> ${tempEmployee.firstName} </td>
							<td> ${tempEmployee.lastName} </td>
							<td> ${tempEmployee.email} </td>
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