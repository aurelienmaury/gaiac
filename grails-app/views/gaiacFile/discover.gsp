<head>
    <meta name="layout" content="main">
</head>

<body>
<div class="content">

    <div class="row">
        <div class="span16">

            <h1>Discover</h1>

            <flash:all/>

            <g:form action="discover">

                <fieldset class="form">
                    <div class="clearfix">

                        <label for="pathToDiscover">
                            <g:message code="discover.path.label"
                                       default="Server directory"/>
                        </label>

                        <div class="input">
                            <g:textField name="pathToDiscover"/>
                        </div>

                    </div>
                </fieldset>

                <div class="actions">
                    <g:submitButton name="create"
                                    value="${message(code: 'short.discover.label', default:'Discover')}"
                                    class="btn primary"/>
                </div>

            </g:form>
        </div>
    </div>
</div>
</body>