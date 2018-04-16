<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微信服务列表</title>
</head>
<body>
    <div>
        <#list platforms as x>
            ${x.id!};
            ${x.name!};
            ${x.appId!};
            ${x.appSecret!};
            ${x.accessToken!};
            ${x.createdAt?string("yyyy-MM-dd HH:mm:ss")!};
            ${x.createdby!};
        </#list>
    </div>
</body>
<footer>
    <script type="text/javascript">
        var p = ${platforms};
        console.log(p);
    </script>
</footer>
</html>