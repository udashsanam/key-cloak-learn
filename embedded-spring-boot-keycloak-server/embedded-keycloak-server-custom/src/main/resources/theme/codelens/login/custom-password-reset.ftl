<#-- This is the custom password reset challenge form -->
<!DOCTYPE html>
<html>
<head>
    <title>Password Reset Challenge</title>
</head>
<body>
<h1>Password Reset Challenge</h1>
<#if errors?? && errors?size > 0>
    <div class="error">
        <#list errors as error>
            <p>${error}</p>
        </#list>
    </div>
</#if>

<#if errorMessage??>
    <div style="color: red">
            <p>${errorMessage}</p>
    </div>
</#if>

<form action="${url.loginAction}" method="POST">
    <label>${challengeQuestion}</label>
    <input type="text" name="challengeAnswer" />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
