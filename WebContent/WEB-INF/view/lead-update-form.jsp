<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>

	<head>
		<title>Save Employees</title>
		<!-- Reference our style sheet -->
		<link type="text/css"
				rel="stylesheet"
				href="${pageContext.request.contextPath}/resources/css/style.css" />
				
		<link type="text/css"
				rel="stylesheet"
				href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
	</head>
	
	<body>
		
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
			<h3>update Lead</h3>
			<form:form action="updateLead" modelAttribute="theLead" method="POST">
			
				<!-- Need to associate this data with lead id -->
				<form:hidden path="leadId"/>
				
				
				<table>
					<tbody>
						<tr>
							<td><label>Lead Status:</label></td>
							<td><form:input path="leadStatus" /></td>
						</tr>
						<tr>
							<td><label>Customer First Name:</label></td>
							<td><form:input path="custFname" /></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Save" class="save" /></td>
						</tr>
					</tbody>
				</table>
			</form:form>
			
			<div style="clear; both;"></div>
			
			<p>
				<a href="${pageContext.request.contextPath}/employee/list">Back to List</a>
			</p>
			
		</div>
		
	</body>

</html>