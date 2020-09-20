<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
    >
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        mmusername = user.getUsername()
        isAdmin = user.getIsAdmin()
        isManag = user.getIsManag()
        >
</#if>