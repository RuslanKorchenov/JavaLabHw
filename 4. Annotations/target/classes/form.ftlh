<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
<form method="${form.method}" action="${form.action}">
    <#list form.inputs as input>
        <input type="${input.type}" name="${input.name}" placeholder="${input.placeholder}">
    </#list>
    <input type="submit">
</form>
</body>
</html>