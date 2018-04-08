<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AVS Technologies | Account Login</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/avsstyle.css" rel="stylesheet">
    <script src="http://cdn.ckeditor.com/4.6.1/standard/ckeditor.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
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
            <li><a href="login">About Us</a></li>
            <li><a href="login">Our Services</a></li>
            <li><a href="login">Testimonial</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="#">Login</a></li>
            <li><a href="showFormForRegister">Register New User</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
    
    
    <header id="header">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center"> AVS Technologies <small>Solution For All</small></h1>
          </div>
        </div>
      </div>
    </header>
    
	<section id="main">
      <div class="container">
      	<div class="row">
          <div class="col-md-4 col-md-offset-4 text-center">
          	<h2>Agent Login</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 col-md-offset-4">  
          	<c:if test = "${validationMsg != null}">
	            <p style="color:red"><c:out value = "${validationMsg}"/><p>
	        </c:if>
            <form:form id="login" action="validateUserLogin" modelAttribute="employee" class="well">
                  <div class="form-group">
                    <label>Employee ID</label>
                    <form:input type="text" path="id" id="loginId" class="form-control" placeholder="Enter Email"/>
                  </div>
                  <div class="form-group">
                    <label>Password</label>
                    <form:input type="password" path="password" id="loginPassword" class="form-control" placeholder="Password"/>
                  </div>
                    <!-- <div class="g-recaptcha" data-sitekey="6Lcfu0wUAAAAAAnUlYOyEcchN2-tP6CesIA_vxR5"></div>
			        <div class="checkbox">
			          <label>
			            <input type="checkbox"> Check me out
			          </label>
			        </div> -->
                  <button type="submit" class="btn btn-primary btn-block">Login</button>
              </form:form>
          </div>
        </div>
      </div>
    </section>

    <footer id="footer">
      <p>Copyright AVS Technologies, &copy; 2018</p>
    </footer>

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
