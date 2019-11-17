<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

hello,${name}

Id: <span>${product.id}</span>
name: <span>${product.name}</span>
birthday: <span>${product.birthday?date}</span>
birthday: <span>${product.birthday?time}</span>
birthday: <span>${product.birthday?datetime}</span>

<table>
    <tr>
        <td>ID</td>
        <td>NAME</td>
        <td>Birthday</td>
    </tr>
    <#list list as p>
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td>${p.birthday?date}</td>
    </#list>

    <#if (age >40)>
    大叔级
<#elseif (age>30)>
    老腊肉
<#else >
    小鲜肉
    </#if>

    ${msg!""}

</table>

</body>
</html>