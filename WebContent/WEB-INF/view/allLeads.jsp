<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Area | All Leads</title>
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
            <li class="active"><a href="allLeads">All Leads</a></li>
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
            <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> All Leads<small>Manage Site Pages</small></h1>
          </div>
        </div>
      </div>
    </header>

    <section id="breadcrumb">
      <div class="container">
        <ol class="breadcrumb">
          <li><a href="index">Dashboard</a></li>
          <li class="active">All Leads</li>
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
                <h3 class="panel-title">All Leads</h3>
              </div>
              <div class="panel-body">
                <div class="row">
                      <div class="col-md-12">
                          <input class="form-control" type="text" placeholder="Filter Users...">
                      </div>
                </div>
                <br>
                <table class="table table-striped table-hover">
                     <tr>
                       <th>Lead ID</th>
                       <th>Lead Status</th>
                       <th></th>
                     </tr>
                     
                     <!-- Loop over and print our customers -->
					<c:forEach var="tempLead" items="${theLeads}">
				
					<!-- Construct an update link with employee id -->
					<c:url var="updateLink" value="leadUpdateById">
						<c:param name="leadId" value="${tempLead.leadId}"/>
					</c:url>
					
					<!-- Construct an delete link with customer id -->
					<c:url var="deleteLink" value="leadDeleteById">
						<c:param name="leadId" value="${tempLead.leadId}"/>
					</c:url>
					
					<tr>
						<td> ${tempLead.leadId} </td>
						<td> ${tempLead.leadStatus} </td>
						<td>
							<!-- Display the update link -->
							<a class="btn btn-default" href="${updateLink}">Edit</a>
							|
							<a class="btn btn-danger" href="${deleteLink}"
								onclick="if(!(confirm('Are you sure you want to delete this Lead?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
                     
                   </table>
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
