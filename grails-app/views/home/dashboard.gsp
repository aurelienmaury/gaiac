<html>
<head>
  <meta name="layout" content="main">
</head>
<body>
  <div id="list-gaiacFile" class="content" role="main">
    

    <div class="row">
      <div class="span16">
          <flash:all/>
        <div class="hero-unit" style="text-align:center">
          <h1>Welcome</h1>
          <p>
          <g:form action="search" controller="gaiacFile">
            <fieldset class="form">
              <label for="query" class="btnPreField">
                <g:actionSubmit class="btn primary" style="display:inline" action="search" value="${message(code: 'default.button.search.label', default: 'Search')}" />
              </label>
              <div class="input">
                <g:textField name="query" value="${query}" class="span11" id="searchField"/>
              </div>    
            </div>
            </fieldset>
          </g:form>
          </p>
        </div>
      </div>
    </div>
        
    <div class="row">
      <div class="span5">
        <h2>Top 10</h2>
        <p>
        Etiam porta sem malesuada magna mollis euismod. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.
        </p>
      </div>
      <div class="span6">
        <h2>Top 10</h2>
        <p>
        Etiam porta sem malesuada magna mollis euismod. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.
        </p>
      </div>
      <div class="span5">
        <h2>Top 10</h2>
        <p>
        Etiam porta sem malesuada magna mollis euismod. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.
        </p>
      </div>
    </div>
    
    

        
        
    
  </div>
</body>
</html>
