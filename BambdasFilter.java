//以下排除内容都为半匹配，即包含以下任意内容都会被过滤掉

//需要排除的域名
String[] domainExclude = {"ibytedapm.com", "byteimg.com", "bytegoofy.com", 
    					  "googleapis", "feelgood.cn", "mozilla.com"};
//需要排除的请求路径
String[] pathExclude = {"WebSocket"};
//需要排除的HTTP方法
String[] methodExclude = {"OPTIONS", "HEAD"};
//需要排除的请求体内容
String[] bodyExclude = {""};
String host = requestResponse.request().httpService().host();
String path = requestResponse.request().path();
String method = requestResponse.request().method();
String body = requestResponse.request().body().toString();
return Arrays.stream(domainExclude).noneMatch(it -> host.contains(it))
	   && Arrays.stream(pathExclude).noneMatch(it -> path.contains(it))
	   && Arrays.stream(methodExclude).noneMatch(it -> method.contains(it))
       && Arrays.stream(bodyExclude).filter(it -> it != null && it.length() != 0).noneMatch(it -> body.contains(it));
