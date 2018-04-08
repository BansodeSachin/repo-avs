<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Area | Add New Lead</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/avsstyle.css" rel="stylesheet">
    <script src="http://cdn.ckeditor.com/4.6.1/standard/ckeditor.js"></script>
  </head>
  <body>

    <nav class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">AVS Technologies</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="index">Dashboard</a></li>
            <li class="active"><a href="users">Users</a></li>
            <li><a href="allLeads">All Leads</a></li>
            <li><a href="leadInfo">Lead Information</a></li>
            <li><a href="addLeadForm">Add New Lead</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Welcome, ${session_empName}</a></li>
            <li><a href="login">Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <header id="header">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Users<small>Manage Site Pages</small></h1>
          </div>
        </div>
      </div>
    </header>

    <section id="breadcrumb">
      <div class="container">
        <ol class="breadcrumb">
          <li><a href="index">Dashboard</a></li>
          <li class="active">Users</li>
        </ol>
      </div>
    </section>

    <section id="main">
      <div class="container">
      
      	
        <div class="row">
          <div class="col-md-3">
            <div class="list-group">
              <a href="index" class="list-group-item active main-color-bg">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Dashboard
              </a>
              <a href="users" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Users <span class="badge">20</span></a>
              <a href="allLeads" class="list-group-item"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> All Leads <span class="badge">533</span></a>
              <a href="leadInfo" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Lead Information </a>
              <a href="addLeadForm" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Add New Lead </a>
            </div>

            <div class="well">
              <h4>Disk Space Used</h4>
              <div class="progress">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                      60%
              </div>
            </div>
            <h4>Bandwidth Used </h4>
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
                    40%
            </div>
          </div>
            </div>
          </div>
          <div class="col-md-9">
            <!-- Website Overview -->
            <div class="panel panel-default">
              <div class="panel-heading main-color-bg">
                <h3 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Update User Information</a>
                </h3>
              </div>
              <div class="panel-body">
              	
              	<div class="panel-group" id="accordion">
				    <div class="panel panel-default">
				      <div id="collapse1" class="panel-collapse collapse in">
				        <div class="row">
					         <div class="col-md-12">
					           <form:form id="updateUserForm" modelAttribute="employee" action="updateUserInfo" class="well">
					           		<!-- Need to associate this data with employee id -->
									<form:hidden path="id"/>
									
									<div class="form-group">
					                  <label>First Name</label>
					                  <form:input path="firstName" type="text" class="form-control" placeholder="Enter First Name" />
					                </div>
					                <div class="form-group">
					                  <label>Last Name</label>
					                  <form:input path="lastName" type="text" class="form-control" placeholder="Enter Last Name"/>
					                </div>
					                <div class="form-group">
					                  <label>Email Address</label>
					                  <form:input path="email" type="text" class="form-control" placeholder="Enter Email Address" />
					                </div>
					                <div class="form-group">
					                   <label>Gender</label>
					                   <div>
					                       <div class="row">
					                           <div class="col-sm-4">
					                               <label class="radio-inline">
					                                   <form:radiobutton path="gender" value="Female"/>Female
					                               </label>
					                           </div>
					                           <div class="col-sm-3">
					                               <label class="radio-inline">
					                                   <form:radiobutton path="gender" value="Male"/>Male
					                               </label>
					                           </div>
					                           <div class="col-sm-3">
					                               <label class="radio-inline">
					                                   <form:radiobutton path="gender" value="Unknown"/>Unknown
					                               </label>
					                           </div>
					                       </div>
					                   </div>
					               </div>
					                <button type="submit" class="btn btn-primary btn-block">Update User Information</button>
				             </form:form>
					         </div>
						    </div>
				      </div>
				    </div>
				  </div>
              
                
              </div>
              </div>
              
              
              
              
            <!-- Website Overview -->
            <div class="panel panel-default">
              <div class="panel-heading main-color-bg">
                <h3 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Update Payment Information</a>
                </h3>
              </div>
              <div class="panel-body">
              	
              	<div class="panel-group" id="accordion2">
				    <div class="panel panel-default">
				      <div id="collapse2" class="panel-collapse collapse">
				        
				        <!-- Construct an Print PDF and Print XML Link with Emp ID -->
						<c:url var="printofferpdfLink" value="printofferpdf">
							<c:param name="empIdInput" value="${employee.id}"/>
						</c:url>
						<c:url var="printofferxmlLink" value="printofferxml">
							<c:param name="empIdInput" value="${employee.id}"/>
						</c:url>
						<c:url var="printsalarypdfLink" value="printsalarypdf">
							<c:param name="empIdInput" value="${employee.id}"/>
						</c:url>
						<c:url var="printsalaryxmlLink" value="printsalaryxml">
							<c:param name="empIdInput" value="${employee.id}"/>
						</c:url>
						
						
					    <a class="btn btn btn-info" href="${printofferpdfLink}" target="_blank"> Print Offer Letter </a> | 
		                <a class="btn btn btn-info" href="${printofferxmlLink}" target="_blank"> Print XML for Offer Letter </a>
		                <br><br>
		                <form method="post" action="emailofferpdf">
		                	<input type="hidden" name="empIdInput" value="${employee.id}"/>
		                	<input type="text" name="inputEmailId">
		                	<input type="submit" class="btn btn btn-info" value="Send Offer Letter">
		                </form>
					    <br><br>
					    <a class="btn btn btn-info" href="${printsalarypdfLink}" target="_blank"> Print Salary Slip PDF </a> | 
		                <a class="btn btn btn-info" href="${printsalaryxmlLink}" target="_blank"> Print XML for Salary Slip </a>  
		                <br><br>
		                <form method="post" action="emailsalarypdf">
		                	<input type="hidden" name="empIdInput" value="${employee.id}"/>
		                	<input type="text" name="inputEmailId">
		                	<input type="submit" class="btn btn btn-info" value="Send Salary Slip">
		                </form>
					    <br><br>
					    
		                <table class="table table-striped table-hover">
		                      <tr>
		                        <th>Component Description</th>
		                        <th>Amount</th>
		                        <th></th>
		                      </tr>
		                      
		                       <!-- Loop over and print our Employees salary components -->
								<c:forEach var="empSalaryComponent" items="${theEmpSalaryComponents}">
							
								<!-- Construct an update link with Emp ID and component id -->
								<c:url var="updateEmpSalaryComponentLink" value="updateEmpSalaryComponentWithId">
									<c:param name="employeeId" value="${empSalaryComponent.id}"/>
									<c:param name="empSalaryComponentId" value="${empSalaryComponent.componentId}"/>
								</c:url>
								
								<!-- Construct an delete link with Emp ID and component id -->
								<c:url var="deleteEmpSalaryComponentLink" value="deleteEmpSalaryComponentWithId">
									<c:param name="employeeId" value="${empSalaryComponent.id}"/>
									<c:param name="empSalaryComponentId" value="${empSalaryComponent.componentId}"/>
								</c:url>
								
								<tr>
									<td> ${empSalaryComponent.componentDesc} </td>
									<td> ${empSalaryComponent.componentAmt} </td>
									<td>
										<!-- Display the update link -->
										<a class="btn btn-default" href="${updateEmpSalaryComponentLink}">Edit</a>
										|
										<a class="btn btn-danger" href="${deleteEmpSalaryComponentLink}"
											onclick="if(!(confirm('Are you sure you want to delete Salary Component for this employee?'))) return false">Delete</a>
									</td>
								</tr>
							</c:forEach>
		                 </table>
					    
					    <div class="row">
				         <div class="col-md-12">
				           <form:form id="updateEmpSalaryComponent" modelAttribute="empSalary" method="post" action="saveSalaryComponent" class="well">
				           		<!-- Need to associate this data with employee Salary Component ID -->
				           		<form:hidden path="id" value="${theEmployeeId}"/>
				           		
								<div class="form-group">
				                  <label>Component ID</label>
				                  <form:input path="componentId" type="text" class="form-control" placeholder="Enter Component ID" />
				                </div>
				                <div class="form-group">
				                  <label>Component Description</label>
				                  <form:input path="componentDesc" type="text" class="form-control" placeholder="Enter Component Description"/>
				                </div>
				                <div class="form-group">
				                  <label>Component Amount</label>
				                  <form:input path="componentAmt" type="text" class="form-control" placeholder="Enter Component Amount" />
				                </div>
				                <button type="submit" class="btn btn-default btn-block">Add New Salary Component</button>
			             </form:form>
				         </div>
					    </div>
				        
				      </div>
				    </div>
				    
				  </div>
              
                
              </div>
              </div>


          </div>
        </div>
      </div>
    </section>

    <footer id="footer">
      <p>Copyright AVS Technologies, &copy; 2018</p>
    </footer>

    <!-- Modals -->

    <!-- Add Page -->
    <div class="modal fade" id="addPage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form>
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Page</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Page Title</label>
          <input type="text" class="form-control" placeholder="Page Title">
        </div>
        <div class="form-group">
          <label>Page Body</label>
          <textarea name="editor1" class="form-control" placeholder="Page Body"></textarea>
        </div>
        <div class="checkbox">
          <label>
            <input type="checkbox"> Published
          </label>
        </div>
        <div class="form-group">
          <label>Meta Tags</label>
          <input type="text" class="form-control" placeholder="Add Some Tags...">
        </div>
        <div class="form-group">
          <label>Meta Description</label>
          <input type="text" class="form-control" placeholder="Add Meta Description...">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
    </form>
    </div>
  </div>
</div>

  <script>
     CKEDITOR.replace( 'editor1' );
 </script>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>
