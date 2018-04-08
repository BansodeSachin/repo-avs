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
            <li><a href="users">Users</a></li>
            <li><a href="allLeads">All Leads</a></li>
            <li><a href="leadInfo">Lead Information</a></li>
            <li class="active"><a href="addLeadForm">Add New Lead</a></li>
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
            <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Add New Lead<small>Manage Site Pages</small></h1>
          </div>
        </div>
      </div>
    </header>

    <section id="breadcrumb">
      <div class="container">
        <ol class="breadcrumb">
          <li><a href="index">Dashboard</a></li>
          <li class="active">Add New Lead</li>
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
                <h3 class="panel-title">Update Lead Information</h3>
              </div>
              <div class="panel-body">
                <div class="row">
		         <div class="col-md-12">
		           <form:form id="updateLeadForm" modelAttribute="theLead" action="updateLead" class="well">
		           		<!-- Need to associate this data with lead id -->
						<form:hidden path="leadId"/>
		           	  <div class="form-group">
	                   <label>Agent Name</label>
	                   <form:input type="text" path="agentName" class="form-control" placeholder="Enter Agent Name"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Center Code</label>
	                   <form:input type="text" path="center_code" class="form-control" placeholder="Enter Center Code"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Lead Status</label>
	                   <form:input type="text" path="leadStatus" class="form-control" placeholder="Enter Lead Status"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Medicare ID</label>
	                   <form:input type="text" path="medId" class="form-control" placeholder="Enter Medicare ID"/>
	                 </div>
	                 <div class="form-group">
	                   <label>PPO Company & ID</label>
	                   <form:input type="text" path="ppoId" class="form-control" placeholder="Enter PPO Company & ID"/>
	                 </div>
	                 <div class="form-group">
			          <label>Insurance Type</label>
			          <select class="form-control">
			          	<option value="">Choose</option>
			            <option value="Medicare">Medicare</option>
			            <option value="PPO">PPO</option>
			            <option value="HMO">HMO</option>
			          </select>
			        </div> 
	                 <div class="form-group">
	                   <label>First Name</label>
	                   <form:input type="text" path="custFname" class="form-control" placeholder="Enter First Name"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Last Name</label>
	                   <form:input type="text" path="custLname" class="form-control" placeholder="Enter Last Name"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Phone Number</label>
	                   <form:input type="text" path="custPhone" class="form-control" placeholder="Enter Phone Number"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Eligibility Check</label>
	                   <form:input type="text" path="eligibilityCheck" class="form-control" placeholder="Enter Eligibility Check"/>
	                 </div>
	                 <div class="form-group">
	                    <label>Date of Birth</label>
	                    <form:input type="date" path="custDOB" class="form-control"/>
	                </div>
	                <div class="form-group">
	                    <label>Braces Needed</label>
	                    <div class="checkbox">
                            <label>
                                <form:checkbox path="brcBack"/>Back
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcRightShoulder"/>Right Shoulder
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcLeftShoulder" value="Left Shoulder"/>Left Shoulder
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcRightAnkle" value="Right Ankle"/>Right Ankle
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcLeftAnkle" value="Left Ankle"/>Left Ankle
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcRightWrist" value="Right Wrist"/>Right Wrist
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcLeftWrist" value="Left Wrist"/>Left Wrist
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcRightElbow" value="Right Elbow"/>Right Elbow
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcLeftElbow" value="Left Elbow"/>Left Elbow
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcRightKnee" value="Right Knee"/>Right Knee
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcLeftKnee" value="Left Knee"/>Left Knee
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcIsCGX" value="CGX"/>CGX
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <form:checkbox path="brcIsPainCream" value="Pain Cream"/>Pain Cream
                            </label>
                        </div>
	                </div>
	                <div class="form-group">
	                    <label>Is there any family history of Cancer?</label>
	                    <div>
	                        <div class="row">
	                            <div class="col-sm-4">
	                                <label class="radio-inline">
	                                    <input type="radio" value="Yes">Yes
	                                </label>
	                            </div>
	                            <div class="col-sm-4">
	                                <label class="radio-inline">
	                                    <input type="radio" value="No">No
	                                </label>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                 <div class="form-group">
	                   <label>Cancer history and relation with patient?</label>
	                   <form:input type="text" path="cancerHistRelation" class="form-control" placeholder="Enter Cancer history and relation with patient?"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Age of Patient when it was diagnosed?</label>
	                   <form:input type="text" path="cancerHistAge" class="form-control" placeholder="Enter Age of Patient when it was diagnosed?"/>
	                 </div>
	                 <div class="form-group">
	                    <label>Gender</label>
	                    <div>
	                        <div class="row">
	                            <div class="col-sm-4">
	                                <label class="radio-inline">
	                                    <input type="radio" value="Female">Female
	                                </label>
	                            </div>
	                            <div class="col-sm-4">
	                                <label class="radio-inline">
	                                    <input type="radio" value="Male">Male
	                                </label>
	                            </div>
	                            <div class="col-sm-4">
	                                <label class="radio-inline">
	                                    <input type="radio" value="Unknown">Unknown
	                                </label>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                 <div class="form-group">
	                   <label>Weight</label>
	                   <form:input type="text" path="custWeight" class="form-control" placeholder="Enter Weight"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Height</label>
	                   <form:input type="text" path="custHeight" class="form-control" placeholder="Enter Height"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Have you received a brace within the last 5 years? *</label>
	                   <form:input type="text" path="pastBracesReceived" class="form-control" placeholder="Enter Have you received a brace within the last 5 years?"/>
	                 </div>
	                 <div class="form-group">
	                   <label>If yes, which brace?</label>
	                   <form:input type="text" path="pastBracesDesc" class="form-control" placeholder="Enter If yes, which brace?"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Street address</label>
	                   <form:input type="text" path="custStreetAddress" class="form-control" placeholder="Enter Street address"/>
	                 </div>
	                 <div class="form-group">
	                   <label>City</label>
	                   <form:input type="text" path="custCity" class="form-control" placeholder="Enter City"/>
	                 </div>
	                 <div class="form-group">
	                   <label>State</label>
	                   <form:input type="text" path="custState" class="form-control" placeholder="Enter State"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Zip code/Post code</label>
	                   <form:input type="text" path="custZipCode" class="form-control" placeholder="Enter Zip code/Post code"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Best time to call</label>
	                   <form:input type="text" path="custBestTimeToCall" class="form-control" placeholder="Enter Best time to call"/>
	                 </div>
	                 <div class="form-group">
	                   <label>Additional Notes</label>
	                   <form:input type="text" path="additionalNote" class="form-control" placeholder="Enter Additional Notes"/>
	                 </div>
	                 <button type="submit" class="btn btn-primary btn-block">Update Lead Information</button>
	             </form:form>
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
