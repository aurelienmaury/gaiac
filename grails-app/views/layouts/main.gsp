<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="Gaïac" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<g:layoutHead />

<r:require module="core" />

<r:layoutResources />
</head>
<body>
  <div class="topbar">
    <div class="fill">
      <div class="container">
        <h3>
          <g:link class="brand" controller='home'><g:message code="app.brand" /></g:link>
        </h3>

        <sec:ifLoggedIn>
          <ul class="nav">
            <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_BASIC">

              <li id="topbar-search" ${activeTopbarSearch?'class="active"':''}>
                <g:link controller='gaiacFile'><g:message code="short.browse.label" /></g:link>
              </li>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_ADMIN">
              <li id="topbar-members" ${activeTopbarMembers?'class="active"':''}><g:link controller='member'>Members</g:link>
              </li>
            </sec:ifAnyGranted>
          </ul>
        
          <div class="pull-right">
            <ul class="nav secondary-nav">
              
              <li><sec:username /><g:link style="display:inline" controller="logout">Logout</g:link></li> 
            </ul>
          </div>
        </sec:ifLoggedIn>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="content">
      <g:layoutBody />
      <div class="footer" role="contentinfo"></div>
    </div>
  </div>
  
  <r:layoutResources />
</body>
</html>
