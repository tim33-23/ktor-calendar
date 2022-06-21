<!doctype html>
<html lang="en">
<head>
    <#include "../head-config.ftl"/>
</head>
<body>
<#include "../header.ftl"/>
<#assign section=""/>
<div class="container">
    <h2 align="center">
        <br>${election.nameElection}: ${election.dateBeginElection}
    </h2>
    </div>
        <div align="center" style="padding-top: 100px">
            <main class="form-signin w-500" >
                <form style="max-width: 25%; margin-bottom: 150px" action="/addEvent" method="Post">
                    <#include "formAddEvent.ftl"/>
                </form>
            </main>
        </div>
    </div>
</body>
</html>