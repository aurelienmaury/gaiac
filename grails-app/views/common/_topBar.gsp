<div class="topbar" data-dropdown="dropdown">
  <div class="fill">
    <div class="container">

      <h3>
        <g:link class="brand" controller='home'><g:message code="app.brand"/></g:link>
      </h3>

      <sec:ifLoggedIn>

        <ul class="nav">

          <sec:ifLoggedIn>
            <li><g:link controller='gaiacFile'><g:message code="short.browse.label"/></g:link></li>
            <li><g:link controller='ul'>Upload</g:link></li>
          </sec:ifLoggedIn>

          <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_SUPERVISOR">
            <li class="dropdown" ${activeTopbarAdmin ? 'class="active"' : ''}>

              <a href="#" class="dropdown-toggle">Manage</a>

              <ul class="dropdown-menu">
                <li><g:link controller='category'>Categories</g:link></li>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                  <li><g:link controller='member'>Members</g:link></li>
                  <li class="divider"></li>
                  <li><g:link controller='gaiacFile' action='discover'>Discover</g:link></li>
                </sec:ifAnyGranted>
              </ul>

            </li>
          </sec:ifAnyGranted>

        </ul>

        <div class="pull-right">
          <ul class="nav secondary-nav">
            <li class="dropdown">

              <a href="#" class="dropdown-toggle"><sec:username/></a>

              <ul class="dropdown-menu">
                <li><g:link controller="logout">Logout</g:link></li>
              </ul>

            </li>
          </ul>
        </div>

      </sec:ifLoggedIn>
    </div>
  </div>
</div>
