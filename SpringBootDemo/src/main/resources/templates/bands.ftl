<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>


<div class="form-style-2">
    <div class="form-style-2-heading">
        Already in System!
    </div>
    <table>
        <tr>
            <th>Name</th>
            <th>Genre</th>
        </tr>
    <#list bandsFromServer as band>
        <tr>
            <td>${band.name}</td>
            <td>${band.genre}</td>
        </tr>
    </#list>
    </table>
</div>
</body>
</html>
