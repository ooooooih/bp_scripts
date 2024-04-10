//以下排除内容都为半匹配，即包含以下任意内容都会被过滤掉

//需要排除的域名
String[] domainExclude = {
    //浏览器 
	"firefoxchina", "mozilla",
    //bytecode
	"ibytedapm.com", "byteimg.com", "bytegoofy.com", "googleapis", 
	"feelgood.cn", "bytescm.com", "bytetos.com", "yhgfb-cn-static.com", 
	"bydauto", "ecombdstatic", "feishucdn", "snssdk", "zijieapi"
};

//需要排除的请求路径或文件后缀
String[] pathExclude = {"WebSocket", ".js", ".css", ".png"};

//需要排除的HTTP方法
String[] methodExclude = {"OPTIONS", "HEAD"};

//需要排除的响应类型，包括图片、字体文件、二进制、CSS、脚本文件
MimeType[] mimetypeExclude = {
    MimeType.APPLICATION_UNKNOWN, MimeType.UNRECOGNIZED,
    MimeType.FONT_WOFF2, MimeType.FONT_WOFF, 
    MimeType.VIDEO, MimeType.SOUND,  
    MimeType.IMAGE_TIFF, MimeType.IMAGE_BMP,  MimeType.IMAGE_PNG, MimeType.IMAGE_GIF, MimeType.IMAGE_JPEG, MimeType.IMAGE_UNKNOWN, 
    MimeType.CSS
    };

//需要排除的请求体内容
String[] bodyExclude = {""};


String host = requestResponse.request().httpService().host();
String path = requestResponse.request().path();
String method = requestResponse.request().method();
String body = requestResponse.request().body().toString();
var mimeType = requestResponse.mimeType();
return Arrays.stream(domainExclude).noneMatch(it -> host.contains(it))
	   && Arrays.stream(pathExclude).noneMatch(it -> path.contains(it))
	   && Arrays.stream(methodExclude).noneMatch(it -> method.contains(it))
	   && Arrays.stream(mimetypeExclude).noneMatch(it -> mimeType == it)
       && Arrays.stream(bodyExclude).filter(it -> it != null && it.length() != 0).noneMatch(it -> body.contains(it));
