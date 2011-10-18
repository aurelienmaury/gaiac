<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="welcome.to.app"/></title>
	</head>
	<body>
		<div class="content">
			<div class="hero-unit">
				<h1><g:message code="welcome.to.app"/></h1>
				<p><g:message code="welcome.message"/></p>
			</div>

			
			<div class="page-header">
				<h2>Controllers:</h2>
      		</div>
      		
      		<div class="row">
	        	<div class="span16">
					<ul>
						<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
							<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
						</g:each>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
